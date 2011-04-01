/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.connect.support;

import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.ServiceProviderConnectionFactory;
import org.springframework.social.connect.spi.ServiceApiAdapter;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuthToken;

public class OAuth1ServiceProviderConnectionFactory<S> extends ServiceProviderConnectionFactory<S> {
	
	public OAuth1ServiceProviderConnectionFactory(String providerId, OAuth1ServiceProvider<S> serviceProvider, ServiceApiAdapter<S> serviceApiAdapter, boolean allowSignIn) {
		super(providerId, serviceProvider, serviceApiAdapter, allowSignIn);
	}

	public OAuth1Operations getOAuthOperations() {
		return getOAuth1ServiceProvider().getOAuthOperations();
	}
	
	public ServiceProviderConnection<S> createConnection(OAuthToken accessToken) {
		String providerUserId = extractProviderUserId(accessToken);
		return new OAuth1ServiceProviderConnection<S>(getProviderId(), providerUserId, getOAuth1ServiceProvider(), accessToken.getValue(), accessToken.getSecret(), getServiceApiAdapter(), isAllowSignIn());		
	}
	
	protected String extractProviderUserId(OAuthToken accessToken) {
		return null;
	}

	// internal helpers
	
	private OAuth1ServiceProvider<S> getOAuth1ServiceProvider() {
		return (OAuth1ServiceProvider<S>) getServiceProvider();
	}
	
}