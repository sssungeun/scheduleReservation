package com.example.schedulertest.Adapter

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schedulertest.Activity.MainDetailActivity
import com.example.schedulertest.Data.GroupSelectData
import com.example.schedulertest.Data.MainListData
import com.example.schedulertest.R

class MainListRecyclerViewAdapter(val ctx: Context,val dataList: ArrayList<MainListData>): RecyclerView.Adapter<MainListRecyclerViewAdapter.Holder>(){

//    //데이터를 저장할 아이템리스트
//    val items = ArrayList<MainListData>()
//
//    //클릭 인터페이스 정의
//    interface ItemClickListener{
//        fun onClick(view: View, position: Int)
//    }
//
//    //클릭리스너 선언
//    private lateinit var itemClickListener: ItemClickListener
//
//    //클릭리스너 등록 메소드
//    fun setItemClickListener(itemClickListener: ItemClickListener){
//        this.itemClickListner = itemClickListener
//    }




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_main_list, viewGroup, false)
        return Holder(view)
    }

//     class content_RecyclerVier_ClickEvent : RecyclerView.OnItemTouchListener {
//
//        interface OnItemClickListener {
//            fun onItemClick(view: View, position: Int)
//            fun onItemLongClick(view: View, position: Int)
//        }
//
//        private var mListener: OnItemClickListener? = null
//        private var mGestureDetector: GestureDetector? = null
//
//        constructor(context: Context, rView: RecyclerView, clickListener: OnItemClickListener){
//
//            mListener = clickListener
//            mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
//                override fun onSingleTapUp(e: MotionEvent): Boolean {
//                    return true
//                }
//
//                override fun onLongPress(e: MotionEvent) {
//                    val childView = rView.findChildViewUnder(e.x, e.y)
//
//                    if (childView != null && mListener != null) {
//                        mListener!!.onItemLongClick(childView, rView.getChildPosition(childView))
//                    }
//                }
//            })
//        }
//
//        override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
//            val childView = rv!!.findChildViewUnder(e!!.getX(), e.getY())
//
//            if (childView != null && mListener != null && mGestureDetector!!.onTouchEvent(e)) {
//                mListener!!.onItemClick(childView, rv.getChildPosition(childView))
//            }
//            return false
//        }
//
//        override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
//
//        }
//
//        // 리사이클러뷰 클릭 이벤트
//        fun recyclerViewClickEvent(context: Context) {
//            content_RecyclerVier_ClickEvent.addOnItemTouchListener(content_RecyclerVier_ClickEvent(this, contentList_RecyclerView,
//                object : content_RecyclerVier_ClickEvent.OnItemClickListener {
//                    override fun onItemClick(view: View, position: Int) {
//                        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show()
//
//                    }
//
//                    override fun onItemLongClick(view: View, position: Int) {
//
//                    }
//                }))
//        }
//        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//        }
//    }
    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].listResponceImg)
            .into(holder.listResponceImg)
        holder.listDate.text = dataList[position].listDate
        holder.listTitle.text= dataList[position].listTitle
        holder.listTime.text= dataList[position].listTime
        holder.listResponcePartNumber.text = dataList[position].listResponcePartNumber.toString()
        holder.listResponceAllNumber.text = dataList[position].listResponceAllNumber.toString()
//
//        var view = holder as CustomViewHolder
//        view.pointTv!!.text = items[position].point.toString()
//        view.priceTv!!.text = items[position].price.toString()
//
//        //view에 onClickListner를 달고, 그 안에서 직접 만든 itemClickListener를 연결시킨다
//        holder.itemView.setOnClickListener {
//            itemClickListner.onClick(it, position)
//        }

//리사이클러뷰 터치시 액티비티 전환
        holder.listDate.setOnClickListener {

   
            var intent = Intent(ctx, MainDetailActivity::class.java)

            intent.putExtra("listDate", dataList[holder.adapterPosition].listDate)
            intent.putExtra("listTitle", dataList[holder.adapterPosition].listTitle)
            intent.putExtra("listTime", dataList[holder.adapterPosition].listTime)
            intent.putExtra("listResponceImg", dataList[holder.adapterPosition].listResponceImg)
            intent.putExtra("listResponcePartNumber", dataList[holder.adapterPosition].listResponcePartNumber)
            intent.putExtra("listResponceAllNumber", dataList[holder.adapterPosition].listResponceAllNumber)
            ctx.startActivity(intent)
        }
        holder.listDate.setOnClickListener {


            var intent = Intent(ctx, MainDetailActivity::class.java)

            intent.putExtra("listDate", dataList[holder.adapterPosition].listDate)
            intent.putExtra("listTitle", dataList[holder.adapterPosition].listTitle)
            intent.putExtra("listTime", dataList[holder.adapterPosition].listTime)
            intent.putExtra("listResponceImg", dataList[holder.adapterPosition].listResponceImg)
            intent.putExtra("listResponcePartNumber", dataList[holder.adapterPosition].listResponcePartNumber)
            intent.putExtra("listResponceAllNumber", dataList[holder.adapterPosition].listResponceAllNumber)
            ctx.startActivity(intent)
        }

        holder.listTitle.setOnClickListener {


            var intent = Intent(ctx, MainDetailActivity::class.java)

            intent.putExtra("listDate", dataList[holder.adapterPosition].listDate)
            intent.putExtra("listTitle", dataList[holder.adapterPosition].listTitle)
            intent.putExtra("listTime", dataList[holder.adapterPosition].listTime)
            intent.putExtra("listResponceImg", dataList[holder.adapterPosition].listResponceImg)
            intent.putExtra("listResponcePartNumber", dataList[holder.adapterPosition].listResponcePartNumber)
            intent.putExtra("listResponceAllNumber", dataList[holder.adapterPosition].listResponceAllNumber)
            ctx.startActivity(intent)
        }

        holder.listTime.setOnClickListener {


            var intent = Intent(ctx, MainDetailActivity::class.java)

            intent.putExtra("listDate", dataList[holder.adapterPosition].listDate)
            intent.putExtra("listTitle", dataList[holder.adapterPosition].listTitle)
            intent.putExtra("listTime", dataList[holder.adapterPosition].listTime)
            intent.putExtra("listResponceImg", dataList[holder.adapterPosition].listResponceImg)
            intent.putExtra("listResponcePartNumber", dataList[holder.adapterPosition].listResponcePartNumber)
            intent.putExtra("listResponceAllNumber", dataList[holder.adapterPosition].listResponceAllNumber)
            ctx.startActivity(intent)
        }
        holder.listResponceImg.setOnClickListener {


            var intent = Intent(ctx, MainDetailActivity::class.java)

            intent.putExtra("listDate", dataList[holder.adapterPosition].listDate)
            intent.putExtra("listTitle", dataList[holder.adapterPosition].listTitle)
            intent.putExtra("listTime", dataList[holder.adapterPosition].listTime)
            intent.putExtra("listResponceImg", dataList[holder.adapterPosition].listResponceImg)
            intent.putExtra("listResponcePartNumber", dataList[holder.adapterPosition].listResponcePartNumber)
            intent.putExtra("listResponceAllNumber", dataList[holder.adapterPosition].listResponceAllNumber)
            ctx.startActivity(intent)
        }



//override fun onClick(view: View?){
//    rv.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
//        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//            val child = rv.findChildViewUnder(e.x, e.y)
//            val position = rv.getChildAdapterPosition(child!!)
//
//            return false
//        }
//
//        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
//
//        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//
//        }
//    })



        }




    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       // var listContainer = itemView!!.findViewById(R.id.ll_rv_main_list) as LinearLayout
        var listDate = itemView.findViewById(R.id.tv_rv_list_date_fixed) as TextView
        var listTitle = itemView.findViewById(R.id.tv_rv_list_title_name) as TextView
        var listTime = itemView.findViewById(R.id.tv_rv_list_title_time) as TextView
        var listResponceImg = itemView.findViewById(R.id.iv_rv_list_responce_number) as ImageView
        var listResponcePartNumber = itemView.findViewById(R.id.tv_rv_list_responce_part_number) as TextView
        var listResponceAllNumber = itemView.findViewById(R.id.tv_rv_list_responce_all_number) as TextView


    }
}