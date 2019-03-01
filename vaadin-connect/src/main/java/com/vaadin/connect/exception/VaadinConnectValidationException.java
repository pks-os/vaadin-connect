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

package com.vaadin.connect.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A validation exception class that is intended to be thrown when any service
 * method receives invalid parameter(s).
 *
 * Behaves similar to the {@link VaadinConnectException} and contains additional
 * information about the validation errors.
 *
 * @see VaadinConnectException
 */
public class VaadinConnectValidationException extends VaadinConnectException {

  /**
   * A validation error data.
   */
  public static class ValidationErrorData {
    private final String parameterName;
    private final String message;

    /**
     * Creates a validation error data object.
     *
     * @param message
     *          validation error message, mandatory (cannot be {@code null} or
     *          blank)
     * @param parameterName
     *          invalid parameter name, optional (can be {@code null} or blank)
     */
    public ValidationErrorData(String message, String parameterName) {
      if (message == null || message.isEmpty()) {
        throw new IllegalArgumentException("Message cannot be null or empty");
      }
      this.parameterName = parameterName;
      this.message = message;
    }

    /**
     * Creates a validation error data object.
     *
     * @param message
     *          validation error message, mandatory (cannot be {@code null} or
     *          blank)
     */
    public ValidationErrorData(String message) {
      this(message, null);
    }

    /**
     * Gets the parameter name that caused the validation error.
     *
     * @return the parameter name, may be {@code null}
     */
    public String getParameterName() {
      return parameterName;
    }

    /**
     * Gets the validation error message.
     *
     * @return the validation error message
     */
    public String getMessage() {
      return message;
    }
  }

  private final List<ValidationErrorData> errorData;

  /**
   * Creates a validation exception from the error data.
   *
   * @param data
   *          validation error data, mandatory (cannot be {@code null})
   * @param errorData
   *          the rest of the validation data, optional
   */
  public VaadinConnectValidationException(ValidationErrorData data,
      ValidationErrorData... errorData) {
    super("Validation failed");

    List<ValidationErrorData> allErrors = new ArrayList<>(errorData.length + 1);
    allErrors.add(Objects.requireNonNull(data,
        "At least one validation error is required"));
    allErrors.addAll(Arrays.asList(errorData));
    this.errorData = Collections.unmodifiableList(allErrors);
  }

  /**
   * Gets the collection of the data on the validation errors.
   *
   * @return the error data
   */
  public List<ValidationErrorData> getErrorData() {
    return errorData;
  }

  @Override
  public Map<String, Object> getSerializationData() {
    Map<String, Object> serializationData = new HashMap<>(
        super.getSerializationData());
    serializationData.put("validationErrors", errorData);
    return serializationData;
  }
}
