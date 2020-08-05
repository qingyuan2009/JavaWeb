package groovy

import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.DefaultExchange
import groovy.util.slurpersupport.GPathResult

Message processData(Message message) {    
   
    def xs = new XmlSlurper()
    //Reader reader = message.getBody(Reader)
    Map properties = message.getProperties() 
    
    String input_data = properties.get("input_data");
    def pricingRequest = xs.parse(input_data);
      
    String komv = properties.get("komv");
    def pricingResponse = xs.parse(komv);   
   
    HashMap<String, GPathResult> pricingResponseMap = new HashMap<String, GPathResult>()    
    pricingResponse.entry.resource.each {       
        pricingResponseMap["${it.id}"] = it     
    }
    
    def validItems = pricingRequest.entry.resource.findAll{ item -> item.resourceType.text() == 'ChargeItemDistribution' }
    validItems.each{
        it.toBeDistributedPriceComponent.replaceNode{}  // remove existing nodes
        if (pricingResponseMap["${it.id}"] != null) {
            it.appendNode (pricingResponseMap["${it.id}"].toBeDistributedPriceComponent)
        }
    }
      
    //write into message body
    def result = XmlUtil.serialize(pricingRequest)
    message.setBody(result.toString());
     
    return message
}

