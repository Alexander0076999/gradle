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

package gradlebuild

import org.gradle.api.tasks.testing.Test
import gradlebuild.basics.BuildEnvironment


val propagatedEnvAllowList = listOf(
    "OS",
    "CLASSPATH",
    "ANDROID_HOME",
    "DIRNAME",
    "USERNAME",
    "USERDOMAIN",
    "REPO_MIRROR_URL",
    "APP_HOME",
    "TMPDIR",
    "TMP",
    "TEMP"
)


fun Test.configurePropagatedEnvVariables() {
    if (BuildEnvironment.isCiServer) {
        environment(System.getenv().entries.map(::sanitize).toMap())
    }
}


private
fun sanitize(entry: MutableMap.MutableEntry<String, String>): Pair<String, String> {
    return when {
        entry.key in propagatedEnvAllowList -> entry.key to entry.value
        entry.key.startsWith("CI") -> entry.key to entry.value
        entry.key.startsWith("LC_") -> entry.key to entry.value
        entry.key.startsWith("JAVA_") -> entry.key to entry.value
        entry.key.startsWith("JDK_") -> entry.key to entry.value
        entry.key.startsWith("GRADLE_") -> entry.key to entry.value
        entry.key.startsWith("GIT_") -> entry.key to entry.value
        entry.key.startsWith("PROCESSOR_") -> entry.key to entry.value
        entry.key.startsWith("TEAMCITY_") -> entry.key to entry.value
        entry.key.startsWith("BUILD_") -> entry.key to entry.value
        entry.key.startsWith("VSSDK") -> entry.key to entry.value
        entry.key.equals("Path", true) -> entry.key to entry.value
        else -> entry.key to ""
    }
}
