package com.wallet.app.presentation.extension

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.getKoin
import org.koin.androidx.scope.LifecycleScopeDelegate
import org.koin.core.Koin
import org.koin.core.component.getScopeId
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.TypeQualifier
import kotlin.reflect.KClass

internal fun ComponentActivity.activityScope(scopeName: String) = activityScope(org.koin.core.qualifier.named(scopeName))

internal fun ComponentActivity.activityScope(scopeClass: KClass<*>) = activityScope(org.koin.core.qualifier.TypeQualifier(scopeClass))

internal fun ComponentActivity.activityScope(qualifier: Qualifier) =
    LifecycleScopeDelegate<Activity>(this, this.getKoin()) { koin: Koin ->
        koin.getOrCreateScope(getScopeId(), qualifier)
    }

internal fun Fragment.fragmentScope(scopeClass: KClass<*>) = fragmentScope(TypeQualifier(scopeClass))

internal fun Fragment.fragmentScope(qualifier: Qualifier) =
    LifecycleScopeDelegate<Fragment>(this, this.getKoin()) { koin: Koin ->
        koin.getOrCreateScope(getScopeId(), qualifier)
    }