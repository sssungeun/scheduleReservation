//package com.example.schedulertest.Adapter
//
//import android.content.Context
//import android.graphics.Typeface
//import android.text.style.CharacterStyle
//import android.text.style.StyleSpan
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Filter
//import android.widget.Filterable
//import android.widget.RelativeLayout
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.schedulertest.R
//import com.google.android.gms.common.api.GoogleApiClient
//import com.google.android.gms.common.api.PendingResult
//import com.google.android.gms.common.api.Status
//import com.google.android.gms.location.places.AutocompleteFilter
//import com.google.android.gms.location.places.AutocompletePrediction
//import com.google.android.gms.location.places.AutocompletePredictionBuffer
//import com.google.android.gms.location.places.Places
//import com.google.android.gms.maps.model.LatLngBounds
//
//import java.util.ArrayList
//import java.util.concurrent.TimeUnit
//
//
///**
// *
// * 주소 자동검색 어뎁터
// *
// * @author 임성진
// *
// * @version 1.0.0
// *
// * @since 2018-06-01 오후 4:59
// */
//
//class PlaceAutocompleteAdapter(
//    internal var context: Context, private val layout: Int, private val googleApiClient: GoogleApiClient,
//
//
//    private var bounds: LatLngBounds?, private val placeFilter: AutocompleteFilter
//) : RecyclerView.Adapter<PlaceAutocompleteAdapter.PlaceViewHolder>(), Filterable {
//
//    internal var listener: PlaceAutoCompleteInterface
//
//    internal var resultList: ArrayList<PlaceAutocomplete>? = null
//
//
//    interface PlaceAutoCompleteInterface {
//
//        fun onPlaceClick(mResultList: ArrayList<PlaceAutocomplete>?, position: Int)
//
//    }
//
//
//    init {
//
//        this.listener = this.context as PlaceAutoCompleteInterface
//
//
//    }
//
//
//    /**
//     *
//     * 아이템 모두 clear
//     *
//     * @author 임성진
//     *
//     * @version 1.0.0
//     *
//     * @since 2018-06-01 오후 4:59
//     */
//
//    fun clearList() {
//
//
//        if (resultList != null && resultList!!.size > 0) {
//
//            resultList!!.clear()
//
//        }
//
//
//    }
//
//
//    /**
//     *
//     * 모든 후속 쿼리에 대한 경계를 설정합니다.
//     *
//     * @author 임성진
//     *
//     * @version 1.0.0
//     *
//     * @since 2018-06-01 오후 5:00
//     */
//
//    fun setBounds(bounds: LatLngBounds) {
//
//        this.bounds = bounds
//
//    }
//
//
//    override fun getFilter(): Filter {
//
//
//        return object : Filter() {
//
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//
//
//                val results = FilterResults()
//
//
//                // 제약 조건이 주어지지 않으면 자동 완성 쿼리를 건너 뜁니다.
//
//                if (constraint != null) {
//
//
//                    //(제약) 검색 문자열에 대한 자동 완성 API를 쿼리하십시오.
//
//                    resultList = getAutocomplete(constraint)
//
//
//
//                    if (resultList != null) {
//
//
//                        //API가 성공적으로 결과를 반환했습니다.
//
//                        results.values = resultList
//
//                        results.count = resultList!!.size
//
//
//                    }
//
//
//                }
//
//
//
//                return results
//
//
//            }
//
//
//            override fun publishResults(constraint: CharSequence, results: FilterResults?) {
//
//
//                if (results != null && results.count > 0) {
//
//
//                    //API가 하나 이상의 결과를 반환하고 데이터를 업데이트합니다.
//
//                    notifyDataSetChanged()
//
//
//                } else {
//
//
//                    //API가 결과를 반환하지 않았고 데이터 세트를 무효화
//
//                    //notifyDataSetInvalidated();
//
//
//                }
//
//
//            }
//
//
//        }
//
//
//    }
//
//
//    private fun getAutocomplete(constraint: CharSequence?): ArrayList<PlaceAutocomplete>? {
//
//
//        if (googleApiClient.isConnected) {
//
//
//            // 자동 완성 API에 쿼리를 제출하고 PendingResult를 검색합니다.
//
//            // 쿼리가 완료되면 결과를 포함합니다.
//
//            val results =
//
//
//                Places.GeoDataApi
//
//
//                    .getAutocompletePredictions(
//                        googleApiClient, constraint!!.toString(),
//
//
//                        bounds, placeFilter
//                    )
//
//
//            // 이 메소드는 기본 UI 스레드에서 호출되어야합니다. API의 결과를 차단하고 최대 60 초 동안 기다립니다.
//
//            val autocompletePredictions = results
//
//
//                .await(60, TimeUnit.SECONDS)
//
//
//            // 쿼리가 성공적으로 완료되었는지 확인하고, 그렇지 않으면 null을 반환합니다.
//
//            val status = autocompletePredictions.status
//
//
//
//            if (!status.isSuccess) {
//
//
//                Log.e("", "Error getting autocomplete prediction API call: $status")
//
//
//
//                autocompletePredictions.release()
//
//
//
//                return null
//
//
//            }
//
//
//
//            Log.i(
//                "", "Query completed. Received " + autocompletePredictions.count
//
//
//                        + " predictions."
//            )
//
//
//            // 버퍼를 고정 할 수 없으므로 결과를 자체 데이터 구조에 복사합니다.
//
//            // AutocompletePrediction 객체는 API 응답 (장소 ID 및 설명)을 캡슐화합니다.
//
//
//            val iterator = autocompletePredictions.iterator()
//
//
//            val resultList = ArrayList(autocompletePredictions.count)
//
//
//
//            while (iterator.hasNext()) {
//
//
//                val prediction = iterator.next()
//
//
//                // 세부 정보를 가져 와서 새로운 PlaceAutocomplete 객체로 복사합니다.
//
//                resultList.add(
//                    PlaceAutocomplete(
//                        prediction.placeId,
//
//                        prediction.getFullText(null)
//                    )
//                )
//
//
//            }
//
//
//            // 모든 데이터가 복사되었으므로 버퍼를 해제
//
//            autocompletePredictions.release()
//
//
//
//            return resultList
//
//
//        }
//
//
//
//        Log.e("", "Google API client is not connected for autocomplete query.")
//
//
//
//        return null
//
//
//    }
//
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PlaceViewHolder {
//
//
//        val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//
//
//        val convertView = layoutInflater.inflate(this.layout, viewGroup, false)
//
//        return PlaceViewHolder(convertView)
//
//
//    }
//
//
//    override fun onBindViewHolder(mPredictionHolder: PlaceViewHolder, i: Int) {
//
//
//        mPredictionHolder.address.text = resultList!![i].description
//
//
//
//        mPredictionHolder.parentLayout.setOnClickListener { listener.onPlaceClick(resultList, i) }
//
//
//    }
//
//
//    override fun getItemCount(): Int {
//
//
//        return if (resultList != null)
//
//            resultList!!.size
//        else
//
//            0
//
//
//    }
//
//
//    fun getItem(position: Int): PlaceAutocomplete {
//
//        return resultList!![position]
//
//    }
//
//
//    /**
//     *
//     * 뷰 홀더
//     *
//     *
//     *
//     * @author 임성진
//     *
//     * @version 1.0.0
//     *
//     * @since 2018-06-01 오후 4:57
//     */
//
//
//    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//
//        var parentLayout: RelativeLayout
//
//        var address: TextView
//
//
//        init {
//
//            parentLayout = itemView.findViewById(R.id.predictedRow) as RelativeLayout
//
//            address = itemView.findViewById(R.id.tv_address) as TextView
//
//
//        }
//
//
//    }
//
//
//    /**
//     *
//     * 지역 정보 소유자 데이터 자동 완성 API 결과입니다.
//     *
//     *
//     *
//     * @author 임성진
//     *
//     * @version 1.0.0
//     *
//     * @since 2018-06-01 오후 4:58
//     */
//
//    inner class PlaceAutocomplete internal constructor(var placeId: CharSequence, var description: CharSequence) {
//
//
//        override fun toString(): String {
//
//            return description.toString()
//
//        }
//
//
//    }
//
//    companion object {
//
//        private val TAG = "PlaceAutocompleteAdapter"
//
//        private val STYLE_BOLD = StyleSpan(Typeface.BOLD)
//    }
//
//
//}
//
//
//
