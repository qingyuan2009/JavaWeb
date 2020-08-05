package groovy

import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import groovy.xml.MarkupBuilder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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

    builder.mkp.xmlDeclaration(version: "1.0", encoding: "UTF-8")
    
    // Define target payload mapping
    builder.'ns2:NfhirSdPricing'( 'xmlns:ns1':"urn:sap-com:document:sap:rfc:functions" )   
    {
        
        'IsKomk'{             
            //Sales Org.           
            def validItemSalesOrg = entryList.entry.resource.parameter.find{ item -> item.name.text() == 'salesOrg' }
            'VKORG' (validItemSalesOrg.valueString)   
            //Distribution Channel
            def validItemDC = entryList.entry.resource.parameter.find{ item -> item.name.text() == 'distributionChannel' }
            'VTWEG' (validItemDC.valueString)
            //Division
            def validItemDiv = entryList.entry.resource.parameter.find{ item -> item.name.text() == 'division' }
            'SPART' (validItemDiv.valueString)
            //Sold-to-party
            def validItemSTP = entryList.entry.resource.parameter.find{ item -> item.name.text() == 'soldToParty' }
            'KUNNR' (validItemSTP.valueString) 
            //Payer
            def validItemPayer = entryList.entry.resource.parameter.find{ item -> item.name.text() == 'payer' }
            'KNRZE' (validItemPayer.valueString)
            //Application: sales/distribution
            'KAPPL' ('V')
            //Pricing procedure: Temp solution          
            'KALSM' ('ZNPA01')
            //Pricing date -- convert to ABAP Date
            def pricingDate = entryList.entry.resource.parameter.find{ item -> item.name.text() == 'pricingDate' }
            //def inputDate = LocalDate.parse(pricingDate.valueString.text(), DateTimeFormatter.ofPattern('yyyy-MM-dd'))
            //def inputDate = LocalDate.parse('2020-12-01', DateTimeFormatter.ofPattern('yyyy-MM-dd'))
            //'PRSDT' (inputDate.format(DateTimeFormatter.ofPattern('yyyyMMdd')))         
            'PRSDT' (pricingDate.valueString)
        }  
        
        
        'IT_KOMP'{
            def validItems = entryList.entry.resource.findAll{ item -> item.resourceType.text() == 'ChargeItemDistribution' }
            validItems.each{ item ->
                'ITEM' {
                    //Condition item number <-> ID
                    'KPOSN' (item.id)
                    //quantity
                    'MGLME' (item.toBeDistributedQuantity.value)
                    //unit of measure
                    'MEINS' (item.toBeDistributedQuantity.unit)  
                    //Carry out pricing
                    'PRSFD' ('X')
                    //net price
                    'NETPR' (item.toBeDistributedNetAmount.value)   
                }
            }
        }
    }
       
    // Generate output
    message.setBody(writer.toString())
    return message
}

