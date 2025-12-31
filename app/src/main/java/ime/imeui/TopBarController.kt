package ime.imeui

import android.graphics.Color
import android.view.View
import android.widget.TextView

class TopBarController(private val suggestionContainer: View?, private val emojiButton: View?, private val darkTheme: Boolean = false) {

    private fun applyColors(tv: TextView?) {
        if (tv == null) return
        if (darkTheme) {
            tv.setTextColor(Color.WHITE)
        } else {
            tv.setTextColor(Color.BLACK)
        }
    }

    fun showNormal() {
        suggestionContainer?.visibility = View.GONE
        emojiButton?.visibility = View.VISIBLE
    }

    fun showSuggestions(suggestions: List<String>, suggestionTextViews: List<TextView>, onClick: (String) -> Unit) {
        emojiButton?.visibility = View.GONE
        suggestionContainer?.visibility = View.VISIBLE
        for (i in 0 until 3) {
            val tv = suggestionTextViews.getOrNull(i)
            val text = suggestions.getOrNull(i) ?: ""
            if (tv != null) {
                applyColors(tv)
                tv.text = text
                tv.visibility = if (text.isEmpty()) View.GONE else View.VISIBLE
                tv.setOnClickListener { onClick(text) }
            }
        }
    }
}