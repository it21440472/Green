package com.example.mad12

import android.viewLayoutInflaster


class ownerAdaptor (private val empList: ArrayList<EmployeeModel>) :
        RecyclerView.Adapter<ownerAdaptor.ViewHolder>() {

        private lateinit var mListener: onItemClickListener

        interface onItemClickListener{
            fun onItemClick(position: Int)
        }

        fun setOnItemClickListener(clickListener: onItemClickListener){
            mListener = clickListener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item, parent, false)
            return ViewHolder(itemView, mListener)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentEmp = empList[position]
            holder.tvEmpName.text = currentEmp.empName
        }

        override fun getItemCount(): Int {
            return empList.size
        }

        class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

            val tvEmpName : TextView = itemView.findViewById(R.id.tvEmpName)

            init {
                itemView.setOnClickListener {
                    clickListener.onItemClick(adapterPosition)
                }
            }

        }

    }
}