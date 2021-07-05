package com.ahmadhamwi.medicus_task.presentation.ui.biomarkers

import androidx.lifecycle.*
import com.ahmadhamwi.medicus_task.domain.interactor.GetBiomarkerUseCase
import com.ahmadhamwi.medicus_task.domain.interactor.GetBiomarkersUseCase
import com.ahmadhamwi.medicus_task.presentation.model.BiomarkerUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiomarkersViewModel @Inject constructor(
    private val getBiomarkersUseCase: GetBiomarkersUseCase,
    private val getBiomarkerUseCase: GetBiomarkerUseCase
) : ViewModel() {

    private var _biomarkersLiveData: MutableLiveData<List<BiomarkerUI>> = MutableLiveData()
    private var _isLoadingBiomarkers: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _isErrorLoadingBiomarkers: MutableLiveData<Exception> = MutableLiveData()
    private var _biomarkerReloadedLiveData: MutableLiveData<BiomarkerUI> = MutableLiveData()

    init {
        getBiomarkers()
    }

    fun getBiomarkers() {

        if (isLoadingBiomarkers().value!!) return

        viewModelScope.launch {
            getBiomarkersUseCase.execute()
                .onStart { _isLoadingBiomarkers.value = true }
                .catch { e -> _isErrorLoadingBiomarkers.value = e as Exception }
                .map { biomarkers -> biomarkers.map { e -> BiomarkerUI.fromEntity(e) } }
                .onCompletion { _isLoadingBiomarkers.value = false }
                .collect { biomarkers ->
                    _biomarkersLiveData.value = biomarkers
                    _isErrorLoadingBiomarkers.value = null
                }
        }
    }

    fun getBiomarker(biomarkerUI: BiomarkerUI) {

        if (isLoadingBiomarkers().value!!) return //just to make sure
        if (biomarkerUI.getReloadingValue()) return //don't reload again

        viewModelScope.launch {
            getBiomarkerUseCase.execute(GetBiomarkerUseCase.GetBiomarkerUseCaseParam(biomarkerUI.id))
                .onStart { biomarkerUI.setReloading(true) }
                .catch { e -> biomarkerUI.setReloading((false)) }
                .map { biomarker -> BiomarkerUI.fromEntity(biomarker) }
                .onCompletion { _isLoadingBiomarkers.value = false }
                .collect { biomarker ->
                    _biomarkerReloadedLiveData.value = biomarker
                    _isErrorLoadingBiomarkers.value = null
                }
        }
    }

    fun retry() {
        getBiomarkers()
    }

    fun getBiomarkersLiveData(): LiveData<List<BiomarkerUI>> {
        return _biomarkersLiveData
    }

    fun isLoadingBiomarkers(): LiveData<Boolean> {
        return _isLoadingBiomarkers
    }

    fun isErrorLoadingBiomarkers(): LiveData<Exception> {
        return _isErrorLoadingBiomarkers
    }

    fun getBiomarkerReloadedLiveData(): LiveData<BiomarkerUI> {
        return _biomarkerReloadedLiveData
    }
}