package com.ugurbuga.codecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ugurbuga.codecase.common.Resource
import com.ugurbuga.codecase.domain.usecease.GetProductListUseCase
import com.ugurbuga.codecase.domain.model.ProductListUIModel
import com.ugurbuga.codecase.ui.list.ListViewModel
import com.ugurbuga.codecase.ui.list.ListViewState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()


    private lateinit var listViewModel: ListViewModel

    private val getProductListUseCase = mockk<GetProductListUseCase>()

    private val listViewStateObserver = mockk<Observer<ListViewState>>()
    private val viewEventStateSlot = slot<ListViewState>()

    private lateinit var listViewState: ListViewState


    @Before
    fun setUp() {
        listViewModel = ListViewModel(
            getProductListUseCase
        )
    }

    @Test
    fun `show empty state when list is empty`() {

        listViewModel.viewState.observeForever(listViewStateObserver)

        every { listViewStateObserver.onChanged(capture(viewEventStateSlot)) } answers {
            listViewState = viewEventStateSlot.captured
        }

        coEvery {
            getProductListUseCase(Unit)
        } returns
                flow {
                    emit(Resource.Loading)
                    emit(
                        Resource.Success(
                            ProductListUIModel(arrayListOf())
                        )
                    )
                }


        listViewModel.getProductList()

        Truth.assertThat(listViewState.isEmptyState).isTrue()

    }
}