package com.techwings.myapplication.ui.create

import android.text.Editable
import android.text.TextWatcher

class BankCardTextWatcher: TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

    override fun afterTextChanged(s: Editable) {
        // Remove all spacing char
        var pos = 0
        while (true) {
            if (pos >= s.length) break
            if (' ' == s[pos] && (((pos + 1) % 5) != 0 || pos + 1 == s.length)) {
                s.delete(pos, pos + 1)
            } else {
                pos++
            }
        }

        pos = 4
        while (true) {
            if (pos >= s.length) break
            val item = s[pos]
            // Only if its a digit where there should be a space we insert a space
            if ("0123456789".indexOf(item) >= 0) {
                s.insert(pos, "" + ' ')
            }
            pos += 5
        }
    }
}