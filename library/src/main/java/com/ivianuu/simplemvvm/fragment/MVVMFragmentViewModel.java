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

package com.ivianuu.simplemvvm.fragment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public class MVVMFragmentViewModel extends AndroidViewModel {

    private final PublishSubject<Bundle> arguments = PublishSubject.create();
    private final PublishSubject<Object> cleared = PublishSubject.create();

    public MVVMFragmentViewModel(Application application) {
        super(application);
    }

    /**
     * By composing this transformer with an observable you guarantee that every observable in your view model
     * will be properly completed when the view model completes.
     */
    protected<T> ObservableTransformer<T, T> bindToLifecycle() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@io.reactivex.annotations.NonNull Observable<T> upstream) {
                return upstream.takeUntil(cleared);
            }
        };
    }

    /**
     * Call this when new arguments are available
     */
    public void arguments(@Nullable Bundle arguments) {
        if (arguments != null) {
            // rx java only likes non null values
            this.arguments.onNext(arguments);
        }
    }

    /**
     * Emits when new arguments are available
     */
    protected Observable<Bundle> arguments() {return arguments;}

    @Override
    protected void onCleared() {
        super.onCleared();
        cleared.onNext(new Object());
    }
}