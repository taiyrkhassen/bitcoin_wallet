package com.wallet.app.domain.di

import org.koin.core.module.Module
import org.koin.dsl.ScopeDSL

abstract class BaseModule {

    abstract val module: Module

    protected open fun Module.addModuleDependencies() {
        // override to use
    }

    protected open fun ScopeDSL.addScopedDependencies() {
        // override to use
    }
}