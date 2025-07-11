package Prenotazione_Autobus;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

// Questa classe impedisce l'inserimento di testo che superi una certa lunghezza massima.
public class LengthDocumentFilter extends DocumentFilter {
    private int maxLength;

    public LengthDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (fb.getDocument().getLength() + string.length() <= maxLength) {
            super.insertString(fb, offset, string, attr);
        } else {
            // Se si tenta di inserire più caratteri del limite, inserisce solo quelli che rientrano nel limite
            String newString = string.substring(0, maxLength - fb.getDocument().getLength());
            if (newString.length() > 0) {
                super.insertString(fb, offset, newString, attr);
            }
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (fb.getDocument().getLength() - length + text.length() <= maxLength) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // Se si tenta di sostituire o incollare testo che superi il limite
            int currentLength = fb.getDocument().getLength();
            int remainingLength = maxLength - (currentLength - length);
            if (remainingLength > 0) {
                String newText = text.substring(0, remainingLength);
                super.replace(fb, offset, length, newText, attrs);
            }
        }
    }
}