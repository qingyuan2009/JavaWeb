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
import groovy.util.slurpersupport.GPathResult
import groovy.xml.StreamingMarkupBuilder

Message processData(Message message) {
    
    // If there is no match in the products/categories file, use this empty node
    def emptyCategoriesXML = "<test></test>"
    // Uses a SAX parser, less memory and overhead than a DOM parser (XmlParser)
    // parse() method returns a GPathResult, which allows you to traverse and
    // manipulate  an XML file or snippet using dot (.) notation.
    def xs = new XmlSlurper()
    // Main products file with SKU list
    def productsSKUs = xs.parse(new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/test/java/groovy/ProductSKU.xml'))
    // Products file with list of categories
    def productsCategories = xs.parse(new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/test/java/groovy/ProductCategory.xml'))
    def emptyCategories = xs.parseText(emptyCategoriesXML)
    // Output file
    File output = new File('C:/Users/i035299/Desktop/myJava/eclipse-workspace-java/JavaWeb/src/test/java/groovy/Products.xml')
    
    def writer = new StringWriter()
    //def builder = new MarkupBuilder(writer)
    
    // Store category nodes into a Map for fast retrieval later. Key is product ID.
    // Note: if you're using Talend, you can't statically type this and must use this:
    // def productsCategoriesMap = new HashMap<String, GPathResult>()
    HashMap<String, GPathResult> productsCategoriesMap = new HashMap<String, GPathResult>()
    // Loop through all the products. Note that the root category is products
    // (plural), but the GPathResult you get from XmlSlurper assumes you're already
    // in the root category. That's why it's not productsCategories.products.product.each
    productsCategories.product.each {
        // Note you must put the id in a String (Groovy style shown here)
        // in order to have a String key.
        productsCategoriesMap["${it.@id}"] = it.categories
    }
    // This allows you to use a DSL to write the file. Note that you are not
    // actually doing the work specified in the closure until you start writing it.
    new StreamingMarkupBuilder().bind {
        // mkp is a special markup namespace for use within this closure. There
        // are other methods as well, see the docs.
        mkp.xmlDeclaration(["version":"1.0", "encoding":"UTF-8"])
        // My root category
        products {
            // Loop through each product and append (insert) the categories
            // node to the product node with the same product id.
            productsSKUs.product.each {   
                it.categories.replaceNode{}             
                if (productsCategoriesMap["${it.@id}"] != null) {
                    it.appendNode (productsCategoriesMap["${it.@id}"])
                } else {
                    it.appendNode (emptyCategories)
                }
                // Note this is not System.out, it merely ensures the
                // GPathResult is printed when written.
                out << it
            }
        }
        // Here we actually write the file, executing the above closure.
        // Note how I specify the character set to match the declaration.
    //} .writeTo(output.newWriter("UTF-8"))
    } .writeTo(writer)
    
    message.setBody(writer.toString())
    return message
}

