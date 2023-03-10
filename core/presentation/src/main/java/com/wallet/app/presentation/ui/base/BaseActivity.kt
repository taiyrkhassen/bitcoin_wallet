package com.wallet.app.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.wallet.app.domain.di.BaseModule
import com.wallet.app.presentation.extension.activityScope
import com.wallet.app.presentation.extension.subscribe
import kotlinx.coroutines.flow.onEach
import org.koin.core.scope.Scope
import timber.log.Timber
import kotlin.reflect.KClass

abstract class BaseActivity<VB : ViewBinding, UI : BaseUiState, VM : BaseUiStateViewModel<UI>>(
    scopedModule: KClass<out BaseModule>
) : AppCompatActivity() {

    val scope: Scope by activityScope(scopedModule)

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    protected abstract val viewModel: VM

    private var prevUiState: UI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
        setupUi()
        viewModel.uiState
            .onEach {
                updateUiState(prevUiState, it)
                prevUiState = it
            }
            .subscribe(lifecycleScope,
                doOnError = { Timber.e(it) }
            )
    }

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
        super.onDestroy()
        _binding = null
    }

    protected open fun setupUi() {
        // override to use
    }

    protected open fun onFragmentAttached(fragment: Fragment) {
        // override to use
    }

    protected open fun onFragmentDetached(fragment: Fragment) {
        // override to use
    }

    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            this@BaseActivity.onFragmentAttached(f)
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            this@BaseActivity.onFragmentDetached(f)
        }
    }

    /**
     * @param prevUiState use it do debounce updates if needed
     */
    protected abstract fun updateUiState(prevUiState: UI?, uiState: UI)
}