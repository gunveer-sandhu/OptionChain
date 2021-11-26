package gunveer.codes.optionchain

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Request as VolleyRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import gunveer.codes.optionchain.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import com.android.volley.DefaultRetryPolicy
import com.google.gson.Gson
import gunveer.codes.optionchain.Retrofit.RetrofitApi
import gunveer.codes.optionchain.databinding.RecViewLayoutBinding
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var response: JSONObject
    private lateinit var progressDialog: ProgressDialog
    private var expiryDates: ArrayList<String> = ArrayList()

    companion object {
        private var TAG = MainActivity::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.setBackgroundDrawable(getDrawable(R.drawable.background_color))
        GlobalScope.launch {
            runOnUiThread {
                showProgressDialog()
            }
            //okhttpMethod()
            //volleyMethod()
            retrofitMethod()
        }

        binding.fabRefresh.setOnClickListener {
            GlobalScope.launch {
                runOnUiThread {
                    showProgressDialog()
                }
                //okhttpMethod()
                //volleyMethod()
                retrofitMethod()
            }
        }
    }

    private fun retrofitMethod() {

        val requestEntity = RequestEntity()

        requestEntity.apply {
            userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0"
            accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
            acceptEncoding = "gzip, deflate, br"
            acceptLanguage = "en-US,en;q=0.5"
            dnt = "1"
            host = "www.nseindia.com"
            referer = "https://www.google.com/"
            secFetchDest = "document"
            secFetchMode = "navigate"
            secFetchSite = "cross-site"
            upgradeInsecureRequests = "1"
            cookie = "AKA_A2=A; ak_bmsc=0321B82A0C46E746E93A00F796124B72~000000000000000000000000000000~YAAQr4gsMXETfNx8AQAAfKYiCw3P3mo2VHeBY5CERisenjVyUvAcPX9DfGCTq1EY4ykdy/5HIWKj4S2JW2oEqbVc6Q7uNnXnYcp48dm1LoopDBv05KfIWVvEdjIkpKoCmPjdUGQ6vjI78T88UVAQko3Pw5pzkZSGTp9DMRmav8wlv7Ud1AEbXwbKKicOQVxnRl+0EdqnfL6+DivQCkkOyvFFKnZY8SHoNA7SAMzuVBeBCNionL9d+ODbf4IDViA/V0VbwiZ6Hm0MguRFA7ujZlAdskb5qvXfrH+XlnewM0Te8Uw/IktnmkgyZ1Upnk5ewtWSVrnLqI6JvzBEnWEu7p5Cfckdns0uLRAoOX/rHpoiaWp7Bs1HKsE4oWR20w=="
        }

        var okHttpClient: OkHttpClient? = OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder().url(original.url)
                .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0"
                )
                .addHeader(
                    "Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
                )
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "en-US,en;q=0.5")
                .addHeader("DNT", "1")
                .addHeader("Host", "www.nseindia.com")
                .addHeader(
                    "Cookie",
                    "AKA_A2=A; ak_bmsc=0321B82A0C46E746E93A00F796124B72~000000000000000000000000000000~YAAQr4gsMXETfNx8AQAAfKYiCw3P3mo2VHeBY5CERisenjVyUvAcPX9DfGCTq1EY4ykdy/5HIWKj4S2JW2oEqbVc6Q7uNnXnYcp48dm1LoopDBv05KfIWVvEdjIkpKoCmPjdUGQ6vjI78T88UVAQko3Pw5pzkZSGTp9DMRmav8wlv7Ud1AEbXwbKKicOQVxnRl+0EdqnfL6+DivQCkkOyvFFKnZY8SHoNA7SAMzuVBeBCNionL9d+ODbf4IDViA/V0VbwiZ6Hm0MguRFA7ujZlAdskb5qvXfrH+XlnewM0Te8Uw/IktnmkgyZ1Upnk5ewtWSVrnLqI6JvzBEnWEu7p5Cfckdns0uLRAoOX/rHpoiaWp7Bs1HKsE4oWR20w==; bm_sv=9847F956E3573A27583E6CD312757740~DLjBuOdL0O7b6aZj7haV3wmsDhmdWPE+TjXb3l8OW15EngRrZo/E4bBn2/xD95Ftu3WqeqOLsw0nEeSctDkjG73Vodx+ij3Ety45PVT5vX+IXysnUucaFcVmMG/nRqGGbMGtXoSZXlVVbZ7Nrw6Biamkd1RSxt6t46FYypecIjs="
                )
            val request: Request = requestBuilder.build()
            return@addInterceptor chain.proceed(request)
        }.build()

        val gson = Gson()
        val dataToSend: String = gson.toJson(requestEntity)
        Log.d(TAG, "retrofitMethod: $dataToSend")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.nseindia.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val retrofitApi = retrofit.create(RetrofitApi::class.java)

        val call: Call<ResponseEntity> = retrofitApi.getResponse("NIFTY")

        call.enqueue(object : Callback<ResponseEntity> {
            override fun onResponse(
                call: Call<ResponseEntity>,
                response: Response<ResponseEntity>
            ) {
                Log.d(TAG, "onResponse: ${response.raw()}")
            }

            override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun volleyMethod() {
        val params = HashMap<String, String>()

        params.apply {
            put(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0"
            )
            put(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
            )
            put("Accept-Encoding", "gzip, deflate, br")
            put("Accept-Language", "en-US,en;q=0.5")
            put("DNT", "1")
            put("Host", "www.nseindia.com")
            put("Referer", "https://www.google.com/")
            put("Sec-Fetch-Dest", "document")
            put("Sec-Fetch-Mode", "navigate")
            put("Sec-Fetch-Site", "cross-site")
            put("Upgrade-Insecure-Requests", "1")
            put(
                "Cookie",
                "ak_bmsc=ACEB5689B9CE0C0FAD1633D9C76CBBC6~000000000000000000000000000000~YAAQr4gsMYPMBlV8AQAA7gHqsQ09XFIPeqnka6L0SsY0OI1VR689yZ525OKHdqjdjhbfZIdPbHTGvoNk+qbJv43sXjxE3rmim7SiR1J9/4CgISZemD0skFpnXwV7/MKPk4mkoheZ08GVQTeChypjKRbUDyZmOgsK3w7KocyLYpU6KyVV6ttVS+em+NrSXyFhhpbdl6lRQ2UWQoPGd8xi3Fyea/eeghq7fm9pFn41Pqw1F3fxU5UMXS/onX5MnHe85Sp53S9ZzAnpjtqIq171vKdo74v7XWVSCCwvJQJwkm8J8PzmbSCcb6PMdCSGXwxqYpFcyeXPZ+Yf+ruW+d6PitljFpmXKB3v3aPHZ2e9mzPxDU3MfDUcKajscEmy7A=="
            )
        }

        val queue = Volley.newRequestQueue(this)
        val url = "https://www.nseindia.com/api/option-chain-indices?symbol=NIFTY"

        val jsonObjectRequest =
            JsonObjectRequest(VolleyRequest.Method.GET, url, JSONObject(params as Map<*, *>),
                { response ->
                    this.response = response
                    Log.d(TAG, "volleyMethod: $response")
                    runOnUiThread {
                        if (this.expiryDates.isNotEmpty()) {
                            this.expiryDates.clear()
                        }
                        val expiryDates =
                            response.getJSONObject("records").getJSONArray("expiryDates")
                        for (i in 0 until expiryDates.length()) {
                            this.expiryDates.add(expiryDates[i].toString())
                        }
                        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
                            this,
                            android.R.layout.simple_spinner_item,
                            this.expiryDates
                        )
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.spinner.adapter = dataAdapter
                        fillData()
                        hideDialog()
                    }

                }, { error ->
                    runOnUiThread { hideDialog() }
                    Log.d(TAG, "volleyMethod: $error")
                    Snackbar.make(
                        binding.root,
                        "Some error has occurred. $error",
                        Snackbar.LENGTH_LONG
                    ).show()
                })
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            1000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(jsonObjectRequest)
    }

    private fun fillData() {
        binding.tvCalls.text =
            "Calls: " + response.getJSONObject("filtered").getJSONObject("CE").getString("totOI")
        binding.tvCallsVol.text =
            "Calls: " + response.getJSONObject("filtered").getJSONObject("CE").getString("totVol")
        binding.tvPutsVol.text =
            "Puts: " + response.getJSONObject("filtered").getJSONObject("PE").getString("totVol")
        binding.tvPuts.text =
            "Puts: " + response.getJSONObject("filtered").getJSONObject("PE").getString("totOI")

        binding.tvPCROI.text = "PCR: " + String.format(
            "%.2f",
            response.getJSONObject("filtered").getJSONObject("PE").getString("totOI").toFloat()
                    / response.getJSONObject("filtered").getJSONObject("CE").getString("totOI")
                .toFloat()
        )
        binding.tvPCRVol.text = "PCR: " + String.format(
            "%.2f",
            response.getJSONObject("filtered").getJSONObject("PE").getString("totVol").toFloat()
                    / response.getJSONObject("filtered").getJSONObject("CE").getString("totVol")
                .toFloat()
        )

        binding.apply {
            recView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = RecViewAdapter()
            }
        }
    }

    private fun showProgressDialog() {
        @Suppress("DEPRECATION")
        progressDialog = ProgressDialog(this)
        progressDialog!!.isIndeterminate = true
        progressDialog!!.setMessage("Please wait..")
        progressDialog!!.setCancelable(true)
        progressDialog!!.setCanceledOnTouchOutside(false)
        progressDialog!!.show()
    }

    private fun hideDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


//    private fun okhttpMethod() {
//        val client = OkHttpClient().newBuilder()
//            .build()
//        val request: Request = Request.Builder()
//            .url("https://www.nseindia.com/api/option-chain-indices?symbol=NIFTY")
//            .method("GET", null)
//            .addHeader(
//                "User-Agent",
//                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0"
//            )
//            .addHeader(
//                "Accept",
//                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
//            )
//            .addHeader("Accept-Encoding", "gzip, deflate, br")
//            .addHeader("Accept-Language", "en-US,en;q=0.5")
//            .addHeader("DNT", "1")
//            .addHeader("Host", "www.nseindia.com")
//            .addHeader("Referer", "https://www.google.com/")
//            .addHeader("Sec-Fetch-Dest", "document")
//            .addHeader("Sec-Fetch-Mode", "navigate")
//            .addHeader("Sec-Fetch-Site", "cross-site")
//            .addHeader("Upgrade-Insecure-Requests", "1")
//            .addHeader(
//                "Cookie",
//                "ak_bmsc=ACEB5689B9CE0C0FAD1633D9C76CBBC6~000000000000000000000000000000~YAAQr4gsMYPMBlV8AQAA7gHqsQ09XFIPeqnka6L0SsY0OI1VR689yZ525OKHdqjdjhbfZIdPbHTGvoNk+qbJv43sXjxE3rmim7SiR1J9/4CgISZemD0skFpnXwV7/MKPk4mkoheZ08GVQTeChypjKRbUDyZmOgsK3w7KocyLYpU6KyVV6ttVS+em+NrSXyFhhpbdl6lRQ2UWQoPGd8xi3Fyea/eeghq7fm9pFn41Pqw1F3fxU5UMXS/onX5MnHe85Sp53S9ZzAnpjtqIq171vKdo74v7XWVSCCwvJQJwkm8J8PzmbSCcb6PMdCSGXwxqYpFcyeXPZ+Yf+ruW+d6PitljFpmXKB3v3aPHZ2e9mzPxDU3MfDUcKajscEmy7A=="
//            )
//            .build()
//        val response = client.newCall(request).execute()
//        runOnUiThread {
//            binding.textview.text = response.body.toString()
//        }
//    }
}