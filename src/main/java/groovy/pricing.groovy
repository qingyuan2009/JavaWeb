package groovy

import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import groovy.xml.MarkupBuilder
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.DefaultExchange

Message processData(Message message) {
    // Access message body and properties
    Reader reader = message.getBody(Reader)
    Map properties = message.getProperties()

    // Define XML parser and builder
    def entryList = new XmlSlurper().parse(reader)
    def writer = new StringWriter()
    def builder = new MarkupBuilder(writer)

    // Define target payload mapping
    builder.'n0:BAPI_EBDR_CREATEMULTIPLE'( 'xmlns:ns1':"urn:sap-com:document:sap:rfc:functions" )   
    {
        
        'IT_DATA'{   
            
            def validItems = entryList.entry.resource.findAll{ item -> item.resourceType.text() == 'ChargeItemDistribution' }
            validItems.each{ item ->
                'ITEM' {
                    'PRECEDINGDOCUMENT' ('pa-inv0001')
                    'PRECEDINGDOCUMENTITEM' (item.id) 
                    def validItems2 = entryList.entry.resource.parameter.findAll{ item2 -> item2.name.text() == 'salesOrg' }
                    validItems2.each{ item2 ->
                      'SALESORGANIZATION' (item2.valueString)  
                    }                 
                }          
            }
        }
        
        'IT_CONDITION_DATA' {
            def validItems = entryList.entry.resource.findAll{ item -> item.resourceType.text() == 'ChargeItemDistribution' }
            validItems.each{ item ->
                'ITEM' {
                    'PRECEDINGDOCUMENT' ('pa-inv0001')
                    'PRECEDINGDOCUMENTITEM' (item.id)
                    'CONDITIONTYPE' ('PROO')
                    'CONDITIONRATEVALUE' (item.toBeDistributedPriceComponent.amount.value)
                }              
            }
        }  
    }
       
    // Generate output
    message.setBody(writer.toString())
    return message
}

