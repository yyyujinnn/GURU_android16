package kr.co.company.and16.RecyclerView_ediya

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.company.and16.Custom.GongchaCustomList
import kr.co.company.and16.Custom.StarbucksCustomList
import kr.co.company.and16.EdiyaCustomList
import kr.co.company.and16.R

class MyRecyclerAdapter(myRecyclerViewInterface: MyRecyclerViewInterface): RecyclerView.Adapter<MyViewHolder>() {

    private var modelList = ArrayList<MyModel>()

    private var myRecyclerViewInterface : MyRecyclerViewInterface? = null

    init {
        this.myRecyclerViewInterface = myRecyclerViewInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_item, parent, false), this.myRecyclerViewInterface!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(this.modelList[position])
    }

    override fun getItemCount(): Int {
        return this.modelList.size
    }

    fun submitLiist(modelList: ArrayList<MyModel>){
        this.modelList = modelList
    }


}