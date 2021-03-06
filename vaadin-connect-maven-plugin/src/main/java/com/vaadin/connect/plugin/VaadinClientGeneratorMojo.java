/*
 * Copyright 2000-2019 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.connect.plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.slf4j.LoggerFactory;

import com.vaadin.connect.plugin.generator.VaadinConnectClientGenerator;

/**
 * The mojo to generate the OpenAPI v3 specification of the Vaadin Client file.
 * Uses the {@link VaadinConnectMojoBase#applicationProperties} to read the data
 * needed for the generation and generates the file in the
 * {@link VaadinConnectMojoBase#generatedFrontendDirectory} directory.
 *
 * @see <a href="https://github.com/OAI/OpenAPI-Specification">OpenAPI
 *      specification</a>
 */
@Mojo(name = "generate-vaadin-client", defaultPhase = LifecyclePhase.COMPILE)
public class VaadinClientGeneratorMojo extends VaadinConnectMojoBase {

  @Override
  public void execute() {
    Path outputFile = generatedFrontendDirectory.toPath()
        .resolve(DEFAULT_GENERATED_CONNECT_CLIENT_NAME);
    if (shouldGenerateDefaultClient()) {
      VaadinConnectClientGenerator vaadinConnectClientGenerator = new VaadinConnectClientGenerator(
          readApplicationProperties());
      vaadinConnectClientGenerator.generateVaadinConnectClientFile(outputFile);
    } else {
      deleteFile(outputFile);
    }
  }

  private void deleteFile(Path outputFile) {
    if (outputFile.toFile().exists()) {
      try {
        Files.delete(outputFile);
      } catch (IOException e) {
        String msg = String.format("Failed to delete default client %s.",
            outputFile.toString());
        LoggerFactory.getLogger(VaadinClientGeneratorMojo.class).info(msg, e);
      }
    }
  }

  private boolean shouldGenerateDefaultClient() {
    return getDefaultClientPath()
        .equals(DEFAULT_GENERATED_CONNECT_CLIENT_IMPORT_PATH);
  }
}
