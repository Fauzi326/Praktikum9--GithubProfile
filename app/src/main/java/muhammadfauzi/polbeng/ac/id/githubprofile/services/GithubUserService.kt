package muhammadfauzi.polbeng.ac.id.githubprofile.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Header
import muhammadfauzi.polbeng.ac.id.githubprofile.models.GithubUser

interface GithubUserService {
    @GET("users/{login}")
    fun loginUser(
        @Header("Authorization") token: String,
        @Path("login") userLogin: String
    ): Call<GithubUser>
}

