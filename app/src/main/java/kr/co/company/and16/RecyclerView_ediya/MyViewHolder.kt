package kr.co.company.and16.RecyclerView_ediya

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_recycler_item.view.*

class MyViewHolder(itemView: View, recyclerViewInterface: MyRecyclerViewInterface): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val TAG: String = "로그"

    private val itemnameTextView = itemView.list_item_name_textView
    private val itempriceTextView = itemView.list_item_prise_textView
    private val itemexistmenuTextView = itemView.list_item_existingmenu_textView


    private var myRecyclerViewInterface: MyRecyclerViewInterface? = null

    init {
        itemView.setOnClickListener(this)
        this.myRecyclerViewInterface = recyclerViewInterface
    }

    //데이터와 뷰를 묶는다
    fun bind(myModel: MyModel){
        itemnameTextView.text = myModel.name
        itempriceTextView.text = myModel.price
        itemexistmenuTextView.text = myModel.existmenu

    }

    override fun onClick(p0: View?) {
        this.myRecyclerViewInterface?.onItemClicked(adapterPosition)
    }


}