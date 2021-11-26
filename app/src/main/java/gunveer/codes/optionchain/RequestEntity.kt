package gunveer.codes.optionchain

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import gunveer.codes.optionchain.Retrofit.FilteredEntity
import java.io.Serializable

@Keep
class RequestEntity: Serializable {
    @SerializedName("User-Agent")
    @Expose
    var userAgent: String? = null

    @SerializedName("Accept")
    @Expose
    var accept: String? = null

    @SerializedName("Accept-Encoding")
    @Expose
    var acceptEncoding: String? = null

    @SerializedName("Accept-Language")
    @Expose
    var acceptLanguage: String? = null

    @SerializedName("DNT")
    @Expose
    var dnt: String? = null

    @SerializedName("Host")
    @Expose
    var host: String? = null

    @SerializedName("Referer")
    @Expose
    var referer: String? = null

    @SerializedName("Sec-Fetch-Dest")
    @Expose
    var secFetchDest: String? = null

    @SerializedName("Sec-Fetch-Mode")
    @Expose
    var secFetchMode: String? = null

    @SerializedName("Sec-Fetch-Site")
    @Expose
    var secFetchSite: String? = null

    @SerializedName("Upgrade-Insecure-Requests")
    @Expose
    var upgradeInsecureRequests: String? = null

    @SerializedName("Cookie")
    @Expose
    var cookie: String? = null
}