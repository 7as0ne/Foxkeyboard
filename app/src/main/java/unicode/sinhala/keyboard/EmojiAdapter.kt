package unicode.sinhala.keyboard

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmojiAdapter(
    private val context: Context,
    private val clickListener: KeyboardView.ClickListener,
    private val darkTheme: Boolean,
    emojis: List<String>,
    private val textSize: Int
) : RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder>() {

    private val items = ArrayList<String>(emojis)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        val tv = TextView(context)
        // Use wrap_content so items size to their content (prevents raw gaps caused by MATCH_PARENT)
        tv.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        tv.gravity = Gravity.CENTER
        tv.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
        // Convert 8dp padding to pixels for consistent spacing across densities
        val pad = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, context.resources.displayMetrics).toInt()
        tv.setPadding(pad, pad, pad, pad)
        // Remove extra font padding which can cause uneven rows for emoji glyphs
        tv.includeFontPadding = false
        tv.setTextColor(if (darkTheme) Color.WHITE else Color.BLACK)
        return EmojiViewHolder(tv)
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        val emoji = items[position]
        holder.textView.text = emoji
        holder.textView.setOnClickListener {
            // Route to the keyboard's emojiClick handler
            clickListener.emojiClick(emoji)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateEmojis(newEmojis: List<String>) {
        items.clear()
        items.addAll(newEmojis)
        notifyDataSetChanged()
    }

    class EmojiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView as TextView
    }
}
