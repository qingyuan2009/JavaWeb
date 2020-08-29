package groovy

import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.DefaultExchange
import org.xml.sax.InputSource
import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil


Message processData(Message message) {    
       
    Map properties = message.getProperties()     
    def body = message.getBody(String.class)
    def input = new XmlParser().parseText(body)
    def writer = new StringWriter()
    //def builder = new MarkupBuilder(writer)
    /*
    HashMap<String, Node> pricingResponseMap = new HashMap<String, Node>()
    input.response.root.entry.resource.each {
        pricingResponseMap["${it.id}"] = it
    }        
             
    def validItems = input.request.root.entry.resource.findAll{ item -> item.resourceType.text() == 'ChargeItemDistribution' }
    validItems.each{  item1->        
        //item1.toBeDistributedPriceComponent.replaceNode{}  // remove existing nodes             
        if (pricingResponseMap["${item1.id}"] != null) {            
            pricingResponseMap["${item1.id}"].toBeDistributedPriceComponent.each { item2 ->
                item1.append(item2);
            }            
        }
    }   
      
    input.response.replaceNode {};  //delete response   
    */
    //write into message body
    message.setBody(XmlUtil.serialize( input))
    //message.setBody(input)
    
    return message
}



