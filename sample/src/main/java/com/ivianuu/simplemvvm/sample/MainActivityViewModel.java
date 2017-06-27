/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.simplemvvm.sample;

import android.content.Intent;

import com.ivianuu.simplemvvm.activity.MVVMActivityViewModel;
import com.ivianuu.simplemvvm.model.ActivityResult;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public class MainActivityViewModel extends MVVMActivityViewModel {

    public MainActivityViewModel() {
        intent()
                .compose(this.<Intent>bindToLifecycle())
                .subscribe(new Consumer<Intent>() {
                    @Override
                    public void accept(@NonNull Intent intent) throws Exception {

                    }
                });

        activityResult()
                .compose(this.<ActivityResult>bindToLifecycle())
                .subscribe(new Consumer<ActivityResult>() {
                    @Override
                    public void accept(@NonNull ActivityResult activityResult) throws Exception {

                    }
                });
    }
}
