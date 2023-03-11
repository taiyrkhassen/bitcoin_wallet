package com.wallet.app.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.wallet.app.domain.di.BaseModule
import com.wallet.app.presentation.extension.fragmentScope
import com.wallet.app.presentation.extension.getColorFromAttr
import com.wallet.app.presentation.extension.subscribe
import kotlinx.coroutines.flow.onEach
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope
import timber.log.Timber
import kotlin.reflect.KClass

abstract class BaseUiStateFragment<VB : ViewBinding, UI : BaseUiState, VM : BaseUiStateViewModel<UI>>(
    scopedModule: KClass<out BaseModule>,
    @LayoutRes contentLayoutId: Int = 0
) : Fragment(contentLayoutId), AndroidScopeComponent {

    override val scope: Scope by fragmentScope(scopedModule)

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    protected abstract val viewModel: VM

    private var prevUiState: UI? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root.apply {
            setBackgroundColor(requireContext().getColorFromAttr(android.R.attr.windowBackground))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    protected abstract fun setupUi()

    /**
     * @param prevUiState use it do debounce updates if needed
     */
    protected abstract fun updateUiState(prevUiState: UI?, uiState: UI)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}