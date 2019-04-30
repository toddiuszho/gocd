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

class PackageMaterialExtensionRepresenterTest {
  @Test
  void 'should serialize package material extension info to JSON'() {
    def actualJson = toObjectString({
      new PackageMaterialExtensionRepresenter().toJSON(it, PluginInfoMother.createPackageMaterialPluginInfo())
    })

    assertThatJson(actualJson).isEqualTo([
      type               : "package-repository",
      package_settings   : [
        configurations: [
          [
            key     : "key1",
            metadata: [
              required        : true,
              secure          : true,
              part_of_identity: true,
              "display_name"  : "Test",
              "display_order" : 0
            ]
          ]
        ],
        view          : [template: "Template"]
      ],
      repository_settings: [
        configurations: [
          [
            key     : "key1",
            metadata: [
              required        : true,
              secure          : true,
              part_of_identity: true,
              "display_name"  : "Test",
              "display_order" : 0
            ]
          ]
        ],
        view          : [template: "Template"]
      ],
    ])
  }
}
