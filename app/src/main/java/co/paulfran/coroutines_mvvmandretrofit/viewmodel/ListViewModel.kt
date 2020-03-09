package co.paulfran.coroutines_mvvmandretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.paulfran.coroutines_mvvmandretrofit.model.CountriesService
import co.paulfran.coroutines_mvvmandretrofit.model.Country
import kotlinx.coroutines.*

class ListViewModel: ViewModel() {

    // Needed for Coroutine
    val countriesService = CountriesService.getCountriesService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        // Start job
        // Start Coroutine for Network connection
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = countriesService.getCountries()
            // response on main
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countries.value = response.body()
                    countryLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }

    }



    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        // cancel job
        job?.cancel()
    }

}