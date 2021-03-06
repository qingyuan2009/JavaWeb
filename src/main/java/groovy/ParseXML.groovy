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
    def Order = new XmlSlurper().parse(reader)
    def writer = new StringWriter()
    def builder = new MarkupBuilder(writer)

    // Define target payload mapping
    builder.PurchaseOrder {
        'Header' {
            'ID'(Order.Header.OrderNumber)
            def inputDate = LocalDate.parse(Order.Header.Date.text(), DateTimeFormatter.ofPattern('dd-MM-yyyy'))
            'DocumentDate'(inputDate.format(DateTimeFormatter.ofPattern('yyyyMMdd')))
            'DocumentType'(properties.get('DocType'))
        }
        def validItems = Order.Item.findAll { item -> item.IsBatchParent.text() == 'true' }
        validItems.each { item ->
            'Item' {
                'ProductCode'(item.MaterialNumber)
                'Quantity'(item.Quantity)
            }
        }
    }

    // Generate output
    message.setBody(writer.toString())
    return message
}

