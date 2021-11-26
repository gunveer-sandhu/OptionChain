package gunveer.codes.optionchain

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import gunveer.codes.optionchain.Retrofit.FilteredEntity

@Keep
class ResponseEntity: Serializable {
    @SerializedName("records")
    @Expose
    var records: RecordsEntity? = null

    @SerializedName("filtered")
    @Expose
    var filtered: FilteredEntity? = null
}

