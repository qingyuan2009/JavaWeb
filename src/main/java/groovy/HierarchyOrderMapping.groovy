package groovy;

import java.util.HashMap;
import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import groovy.xml.MarkupBuilder
import groovy.xml.XmlUtil;
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.DefaultExchange
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter


Message processData(Message message) {
    // Access message body and properties
    //Reader reader = message.getBody(Reader)
	def body = message.getBody(java.lang.String) as String;
    Map properties = message.getProperties()
	
	

    // Define XML parser and builder
    //def OrderList = new XmlSlurper().parse(reader)
	def OrderList = new XmlParser().parseText(body);
    def writer = new StringWriter()
    def builder = new MarkupBuilder(writer)

    // Define target payload mapping
    builder.'ns1:NFHIR_CO_HIERARCHYORDER_POST'( 'xmlns:ns1':"urn:sap-com:document:sap:rfc:functions" ) {  
    
        def validItems = OrderList.entry.resource.parameter.findAll{ item1 -> item1.name.text() == 'topInternalOrder' }
        validItems.each{ item2 ->
            IV_TOP_ORDER(item2.valueString.text())
        };
        
        IT_ORDERLIST {
            def validItems2 = OrderList.entry.resource.parameter.findAll{ item3 -> item3.name.text() == 'subInternalOrder' }
            validItems2.each{ item4 ->
                'item' {
                    AUFNR(item4.valueString.text())                
                }
            }
        }  
    }   
    
    // Generate output
    message.setBody(writer.toString())
    return message
}
