/*
 * Copyright 2020 the original author or authors.
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

package org.gradle.api.internal.credentials;

import org.gradle.api.credentials.Credentials;
import org.gradle.api.provider.Provider;

import java.util.function.Supplier;

public interface CredentialsProviderFactory {

    <T extends Credentials> Provider<T> provideCredentials(Class<T> credentialsType, String identity);

    <T extends Credentials> Provider<T> provideCredentials(Class<T> credentialsType, Supplier<String> identity);

}
