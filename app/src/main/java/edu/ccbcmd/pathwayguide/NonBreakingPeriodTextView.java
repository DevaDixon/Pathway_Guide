package edu.ccbcmd.pathwayguide;

        import android.text.TextPaint;
        import android.text.Editable;
        import android.util.Log;

        import android.graphics.Paint;

        import android.util.AttributeSet;

        import android.content.Context;

        import android.widget.TextView;


public class NonBreakingPeriodTextView extends TextView
{

    private static final String TAG = "NonBreakingPeriodTextView";

    public NonBreakingPeriodTextView(Context context) {

        super(context);

    }

    public NonBreakingPeriodTextView(Context context, AttributeSet set) {

            super(context, set);


    }

    public void onSizeChanged(int i, int n, int n2, int n3) {

            final Editable editableText = this.getEditableText();
            if (editableText == null) {
                Log.d("tag", "non-editable text");
                return;
            }
            final int n4 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
            if (n4 == 0) {
        Log.d("tag", "zero-length text");
                return;
            }
            final TextPaint paint = this.getPaint();
            final float[] array = new float[editableText.length()];
            (paint).getTextWidths(editableText.toString(), array);
            float n5 = 0.0f;
            n2 = -1;
            i = 0;
            int n6 = 0;
            int n7 = 0;
            while (i < editableText.length()) {
                n5 += array[i];
                final char char1 = editableText.charAt(i);
                int n8;
                int n9;
                if (char1 == '\n') {
                    n = 1;
                    n8 = i;
                    n3 = n2;
                    n9 = n7;
                }
                else if (Character.isWhitespace(char1)) {
                    n3 = i;
                    n9 = n7;
                    n = n6;
                    n8 = i;
                }
                else {
                    n9 = n7;
                    n3 = n2;
                    n = n6;
                    n8 = i;
                    if (n5 > n4) {
                        n9 = n7;
                        n3 = n2;
                        n = n6;
                        n8 = i;
                        if (n2 >= 0) {
                            editableText.replace(n2, n2 + 1, "\n");
                            n9 = n7 + 1;
                            n3 = -1;
                            n = 1;
                            n8 = n2;
                        }
                    }
                }
                if ((n6 = n) != 0) {
                    n5 = 0.0f;
                    n6 = 0;
                }
                i = n8 + 1;
                n7 = n9;
                n2 = n3;
            }
            if (n7 != 0) {
                this.setText(editableText);
            }

    }
}
