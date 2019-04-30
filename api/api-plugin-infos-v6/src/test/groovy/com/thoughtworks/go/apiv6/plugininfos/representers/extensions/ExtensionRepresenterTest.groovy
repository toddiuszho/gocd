/*
 * Copyright 2019 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thoughtworks.go.apiv6.plugininfos.representers.extensions

import com.thoughtworks.go.apiv6.plugininfos.representers.Helper.PluginInfoMother
import org.junit.jupiter.api.Test

import static com.thoughtworks.go.api.base.JsonUtils.toObjectString
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson

class ExtensionRepresenterTest {
  @Test
  void "should serialize extension with plugin settings"() {
    def actualJson = toObjectString({
      new AnalyticsPluginInfoRepresenter().toJSON(it, PluginInfoMother.createAnalyticsPluginInfo())
    })

    def expectedJSON = [
      type           : "analytics",
      plugin_settings: [
        configurations: [
          [
            key     : "key1",
            metadata: [required: true, secure: false]
          ],
          [
            key     : "key2",
            metadata: [required: true, secure: false]
          ]
        ],
        view          : [template: "Template"]
      ],
      capabilities   : [
        supported_analytics: [
          [
            type : "Type 1",
            id   : "Id 1",
            title: "Title 1"
          ]
        ]
      ]
    ]

    assertThatJson(actualJson).isEqualTo(expectedJSON)
  }

  @Test
  void "should serialize extension without plugin settings"() {
    def actualJson = toObjectString({
      new AnalyticsPluginInfoRepresenter().toJSON(it, PluginInfoMother.createAnalyticsPluginInfoWithoutPluginSettings())
    })

    def expectedJSON = [
      type        : "analytics",
      capabilities: [
        supported_analytics: [
          [
            type : "Type 1",
            id   : "Id 1",
            title: "Title 1"
          ]
        ]
      ]
    ]

    assertThatJson(actualJson).isEqualTo(expectedJSON)
  }
}
