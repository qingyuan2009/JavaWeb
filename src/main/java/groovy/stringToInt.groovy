package groovy

import com.sap.gateway.ip.core.customdev.util.Message;
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    
    def body = message.getBody(String.class);       
    def jsonSlurper = new JsonSlurper().parseText(body)
    
    //def input = new JsonSlurper().parseText(body)
    //def builder = new JsonBuilder(input)

    //String output = body.replaceAll("\"(\\d+)\"", "\$1");
    //message.setBody(output);
    
    
    
    def validItems = jsonSlurper.entry.resource.findAll { it.resourceType == "ChargeItemDistribution" }
    validItems.each{ item->
        item.toBeDistributedNetAmount.value = item.toBeDistributedNetAmount.value.toDouble()
        item.toBeDistributedQuantity.value = item.toBeDistributedQuantity.value.toDouble()        
        item.toBeDistributedPriceComponent.each {item2 ->            
            item2.amount.value = item2.amount.value.toDouble()
        } 
    }
   
    message.setBody(JsonOutput.toJson(jsonSlurper));

    return message;
}