package groovy

import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.DefaultExchange
import spock.lang.Shared
import spock.lang.Specification

class OrderToPurchaseOrderSpec extends Specification {

    Message msg
    Exchange exchange
    @Shared script    

    def setup() {
        // Load Groovy Script
        GroovyShell shell = new GroovyShell()       
        script = shell.parse(new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/main/java/groovy/ParseXML.groovy'))

        CamelContext context = new DefaultCamelContext()
        exchange = new DefaultExchange(context)
        msg = new MessageImpl(exchange)
    }

    def 'Purchase Order Mapping for Document Type Z001'() {
        given:
        //--------------------------------------------------------------
        // Initialize message with body, header and property
        def body = new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/test/java/groovy/Order.xml')
        println(body.text);
        println('----------------------------------------------');
        msg.setProperty('DocType', 'Z001')
        //--------------------------------------------------------------

        exchange.getIn().setBody(body)
        msg.setBody(exchange.getIn().getBody())

        when:
        // Execute script
        script.processData(msg)

        then:
        println(msg.getBody());
        msg.getBody() == new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/test/java/groovy/OrderOutput.xml').text
        
        
    }
}