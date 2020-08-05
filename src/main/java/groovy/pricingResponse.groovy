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

    //builder.mkp.xmlDeclaration(version: "1.0", encoding: "UTF-8")

    // Define target payload mapping
    builder.'root'
    {
        entryList.ET_KOMP.item.each{ itemKOMP ->
            'entry'{
                'resource' {
                    'resourceType' ('ChargeItemDistribution')
                    'id' itemKOMP.KPOSN                   
                    def validItems = entryList.ET_KOMV.item.findAll{ item -> item.KPOSN.text() == itemKOMP.KPOSN.text() }                  
                    validItems.each{ itemKOMV ->
                        'toBeDistributedPriceComponent' {
                            'type'('base')
                            'code'{
                                'coding'{
                                    'system'('http://fhir.de/CodeSystem/condition_type')
                                    'version' ('2019Q2')
                                    //'code'(itemKOMV.KSCHL.text())
                                    'code'(itemKOMV.KSCHL)
                                    
                                }
                            }
                            'amount'{
                                'value' (itemKOMV.KWERT)
                                'currency' ('EUR')
                            }
                        }
                    }
                }  
            }
        }
    }

    // Generate output
    message.setBody(writer.toString())
    return message
}

