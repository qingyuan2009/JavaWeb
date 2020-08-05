package groovy

import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.DefaultExchange
import spock.lang.Shared
import spock.lang.Specification

class MergePriceTest extends Specification {

    Message msg
    Exchange exchange
    @Shared script    

    def setup() {
        // Load Groovy Script
        GroovyShell shell = new GroovyShell()       
        script = shell.parse(new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/main/java/groovy/MergePrice.groovy'))

        CamelContext context = new DefaultCamelContext()
        exchange = new DefaultExchange(context)
        msg = new MessageImpl(exchange)
    }

    def 'Generate Output'() {
        given:
        //--------------------------------------------------------------
        // Initialize message with body, header and property
        def input_data = new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/test/java/groovy/pricingInput.xml')
        //println(body.text);
        //println('----------------------------------------------');
        def komv = new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/test/java/groovy/PricingResultReplace.xml')

        //exchange.getIn().setBody(body)
        //msg.setBody(exchange.getIn().getBody())
        msg.setProperty("input_data", input_data)
        msg.setProperty("komv", komv)

        when:
        // Execute script
        script.processData(msg)

        then:
        println(msg.getBody());
        //msg.getBody() == new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/test/java/groovy/OrderOutput.xml').text
        
        
    }
}