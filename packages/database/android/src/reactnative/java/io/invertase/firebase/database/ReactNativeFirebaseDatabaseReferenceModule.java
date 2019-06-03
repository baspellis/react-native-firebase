package io.invertase.firebase.database;

/*
 * Copyright (c) 2016-present Invertase Limited & Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this library except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import java.util.Map;

import io.invertase.firebase.common.ReactNativeFirebaseModule;

import static io.invertase.firebase.common.RCTConvertFirebase.toHashMap;
import static io.invertase.firebase.database.ReactNativeFirebaseDatabaseCommon.rejectPromiseDatabaseException;

public class ReactNativeFirebaseDatabaseReferenceModule extends ReactNativeFirebaseModule {
  private static final String SERVICE_NAME = "DatabaseReference";
  private final UniversalFirebaseDatabaseReferenceModule module;

  ReactNativeFirebaseDatabaseReferenceModule(ReactApplicationContext reactContext) {
    super(reactContext, SERVICE_NAME);
    module = new UniversalFirebaseDatabaseReferenceModule(reactContext, SERVICE_NAME);
  }

  @ReactMethod
  public void set(String app, String dbURL, String path, ReadableMap props, Promise promise) {
    module.set(app, dbURL, path, toHashMap(props).get("value"))
      .addOnCompleteListener(getExecutor(), task -> {
        if (task.isSuccessful()) {
          promise.resolve(task.getResult());
        } else {
          rejectPromiseDatabaseException(promise, task.getException());
        }
      });
  }

  @ReactMethod
  public void update(String app, String dbURL, String path, ReadableMap props, Promise promise) {
    @SuppressWarnings("unchecked") Map<String, Object> values = (Map<String, Object>) toHashMap(props).get("values");

    module.update(app, dbURL, path, values)
      .addOnCompleteListener(getExecutor(), task -> {
        if (task.isSuccessful()) {
          promise.resolve(task.getResult());
        } else {
          rejectPromiseDatabaseException(promise, task.getException());
        }
      });
  }

  @ReactMethod
  public void setWithPriority(String app, String dbURL, String path, ReadableMap props, Promise promise) {
    module.setWithPriority(app, dbURL, path, toHashMap(props).get("value"), toHashMap(props).get("priority"))
      .addOnCompleteListener(getExecutor(), task -> {
        if (task.isSuccessful()) {
          promise.resolve(task.getResult());
        } else {
          rejectPromiseDatabaseException(promise, task.getException());
        }
      });
  }

  @ReactMethod
  public void remove(String app, String dbURL, String path, Promise promise) {
    module.remove(app, dbURL, path)
      .addOnCompleteListener(getExecutor(), task -> {
        if (task.isSuccessful()) {
          promise.resolve(task.getResult());
        } else {
          rejectPromiseDatabaseException(promise, task.getException());
        }
      });
  }

  @ReactMethod
  public void setPriority(String app, String dbURL, String path, ReadableMap props, Promise promise) {
    module.setPriority(app, dbURL, path, toHashMap(props).get("priority"))
      .addOnCompleteListener(getExecutor(), task -> {
        if (task.isSuccessful()) {
          promise.resolve(task.getResult());
        } else {
          rejectPromiseDatabaseException(promise, task.getException());
        }
      });
  }
}