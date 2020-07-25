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
    def ResultList = new XmlSlurper().parse(reader)
    def writer = new StringWriter()
    def builder = new MarkupBuilder(writer)

    // Define target payload mapping
    //builder.'ns1:ZNFHIR_CO_HIERARCHYORDER_POST'( 'xmlns:ns1':"urn:sap-com:document:sap:rfc:functions" ) {  
    builder.resource {
        
        'resourceType'('Parameters')
        
        def validItems = ResultList.ET_RETURN.item.findAll{ item -> item.TYPE.text() == 'S' }
        validItems.each{ item ->            
            'parameter' {
                'name'('isSuccess')
                'valueBoolean'('true')
            } 
        } 
    }   
    
    // Generate output
    message.setBody(writer.toString())
    return message
}

