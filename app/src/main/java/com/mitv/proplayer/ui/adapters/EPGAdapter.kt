package com.mitv.proplayer.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mitv.proplayer.R
import com.mitv.proplayer.models.EPGProgram

class EPGAdapter(
    private val programs: List<EPGProgram>,
    private val onProgramClick: (EPGProgram) -> Unit
) : RecyclerView.Adapter<EPGAdapter.EPGViewHolder>() {

    class EPGViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val programTitle: TextView = itemView.findViewById(R.id.program_title)
        val programTime: TextView = itemView.findViewById(R.id.program_time)
        val programDescription: TextView = itemView.findViewById(R.id.program_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EPGViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_epg, parent, false)
        return EPGViewHolder(view)
    }

    override fun onBindViewHolder(holder: EPGViewHolder, position: Int) {
        val program = programs[position]
        
        holder.programTitle.text = program.title
        holder.programTime.text = "20:00 - 21:00" // TODO: Format time
        holder.programDescription.text = program.description ?: "Sem descrição"
        
        holder.itemView.setOnClickListener {
            onProgramClick(program)
        }
    }

    override fun getItemCount(): Int = programs.size
}
