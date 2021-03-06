package com.skanderjabouzi.nbateamviewer.di.scope

import dagger.MapKey
import kotlin.reflect.KClass
import androidx.lifecycle.ViewModel

@MustBeDocumented
@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)