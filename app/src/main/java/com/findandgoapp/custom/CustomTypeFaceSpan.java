/*
 * This file is part of FindAndGoApp.
 *
 *   FindAndGoApp is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   FindAndGoApp is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of FindAndGoApp.
 *
 *   FindAndGoApp is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   FindAndGoApp is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.findandgoapp.custom;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

/**
 * Created by Juan F. Mateos
 * Reference:http://stackoverflow.com/questions/6612316/how-set-spannable-object-font-with-custom-font/10741161#10741161
 */
public class CustomTypeFaceSpan extends TypefaceSpan implements Parcelable {

    private final Typeface newType;

    public CustomTypeFaceSpan(String family, Typeface type) {
        super(family);
        newType = type;
    }


    public static final Creator<CustomTypeFaceSpan> CREATOR = new Creator<CustomTypeFaceSpan>() {
        @Override
        public CustomTypeFaceSpan createFromParcel(Parcel in) {
            return new CustomTypeFaceSpan(in);
        }

        @Override
        public CustomTypeFaceSpan[] newArray(int size) {
            return new CustomTypeFaceSpan[size];
        }
    };

    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeFace(ds, newType);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newType);
    }

    private static void applyCustomTypeFace(Paint paint, Typeface tf) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
        paint.setTextSize(20);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    public CustomTypeFaceSpan(String family) {
        super(family);
        newType = null;
    }

    public CustomTypeFaceSpan(Parcel src) {
        super(src);
        newType = null;
    }

    @Override
    public int getSpanTypeId() {
        return super.getSpanTypeId();
    }

    @Override
    public String getFamily() {
        return super.getFamily();
    }
}