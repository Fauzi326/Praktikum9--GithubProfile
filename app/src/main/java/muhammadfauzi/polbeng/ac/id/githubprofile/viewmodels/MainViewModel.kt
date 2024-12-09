package muhammadfauzi.polbeng.ac.id.githubprofile.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import muhammadfauzi.polbeng.ac.id.githubprofile.helpers.Config
import muhammadfauzi.polbeng.ac.id.githubprofile.models.GithubUser
import muhammadfauzi.polbeng.ac.id.githubprofile.services.GithubUserService
import muhammadfauzi.polbeng.ac.id.githubprofile.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _githubUser = MutableLiveData<GithubUser>()
    val githubUser: LiveData<GithubUser> = _githubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        searchUser(Config.DEFAULT_USER_LOGIN)
    }

    fun searchUser(query: String) {
        _isLoading.value = true
        Log.d(TAG, "searchUser: start...")

        val githubUserService = ServiceBuilder.buildService(GithubUserService::class.java)
        val requestCall: Call<GithubUser> =
            githubUserService.loginUser(Config.PERSONAL_ACCESS_TOKEN, query)

        Log.d(TAG, "searchUser: Request URL = ${requestCall.request().url}")

        requestCall.enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val result = response.body()
                    Log.d(TAG, "searchUser: Result = $result")
                    _githubUser.postValue(result)
                    Log.d(TAG, "searchUser: onResponse finished successfully.")
                } else {
                    Log.d(TAG, "searchUser: onResponse failed.")
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "searchUser: onFailure = ${t.message}", t)
            }
        })
    }
}
