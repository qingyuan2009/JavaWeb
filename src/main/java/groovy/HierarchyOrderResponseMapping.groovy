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
    
    // Check if there is error
    def validItems = ResultList.ET_RETURN.item.findAll{ item -> item.TYPE.text() == 'E' }
    if (validItems.size() > 0) {
        def etErrMsg = "ET_RETURN.MESSAGE: ";
        for (int i = 0; i < ResultList.ET_RETURN[0].item.size(); i++) {
            etErrMsg = etErrMsg + ResultList.ET_RETURN[0].item[i].MESSAGE[0].text() + "; "
        }
        throw new Exception(etErrMsg);
    } else {
        // success
        builder.resource {
            'resourceType'('Parameters');
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

