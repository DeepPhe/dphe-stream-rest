/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthnlp.deepphe.stream.spring.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author zhy19
 */
@Component
@PropertySource("classpath:deepphe.properties")
public class DeepPheProperties {

    @Value("${deepphe.auth.token}")
    private String authToken;

    public DeepPheProperties() {
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    
    
}
