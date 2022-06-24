package com.vsb.kru13.barcodetemplate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BarcodeView extends View {

    //UPC-A code

    //http://en.wikipedia.org/wiki/EAN_code
    //http://www.terryburton.co.uk/barcodewriter/generator/

    static final int[] L = {0x0D,  //000 1101
            0x19,  //001 1001
            0x13,  //001 0011
            0x3D,  //011 1101
            0x23,  //010 0011
            0x31,  //011 0001
            0x2F,  //010 1111
            0x3B,  //011 1011
            0x37,  //011 0111
            0x0B   //000 1011
    };

    static final int[] R = {0x72, //111 0010
            0x66, //110 0110
            0x6C, //110 1100
            0x42, //100 0010
            0x5C, //101 1100
            0x5E, //100 1110
            0x50, //101 0000
            0x44, //100 0100
            0x48, //100 1000
            0x74  //111 0100
    };

    static final int S = 0x05; //101
    static final int M = 0x0A; //0 1010
    static final int E = 0x05; //101

    final static int BARCODE_WIDTH =  600;
    final static int BARCODE_HEIGHT = 200;
    final static int BARCODE_LINE_WIDTH = 5;

    final static int BARCODE_LINE_PART_WIDTH = 7;
    final static int START_LINE_PART_WIDTH = 3;
    final static int MIDDLE_LINE_PART_WIDTH = 5;
    final static int END_LINE_PART_WIDTH = 3;

    // čísla čárového kódu
    int code[] = new int[12];

    // aktuální pozice
    private int x = 0;

    public BarcodeView(Context context) {
        super(context);
        setDefaults();
    }

    public BarcodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setDefaults();
    }

    public BarcodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDefaults();
    }

    public BarcodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setDefaults();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // při změně velikosti view,  w a h obsahují novou velikost
    }

    // nastaví výchozí hodnoty
    void setDefaults() {
        int copyFrom[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2};
        System.arraycopy(copyFrom, 0, code, 0, copyFrom.length);
    }

    void setCode(int[] copyFrom) {
        System.arraycopy(copyFrom, 0, code, 0, copyFrom.length);

        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint twPaint = new Paint();
        twPaint.setStyle(Paint.Style.FILL);
        twPaint.setColor(Color.WHITE);
        twPaint.setStrokeWidth(BARCODE_LINE_WIDTH);

        Paint tbPaint = new Paint();
        tbPaint.setStyle(Paint.Style.FILL);
        tbPaint.setColor(Color.BLACK);
        tbPaint.setStrokeWidth(BARCODE_LINE_WIDTH);

        Paint trPaint = new Paint();
        trPaint.setStyle(Paint.Style.FILL);
        trPaint.setColor(Color.RED);
        trPaint.setStrokeWidth(BARCODE_LINE_WIDTH);

        // vykreslí bílý obdelník do kterého se bude kreslit čárový kód
        canvas.drawRect(new Rect(0, 0, BARCODE_WIDTH, BARCODE_HEIGHT), twPaint);

        // tloušťka čáry
        tbPaint.setStrokeWidth(BARCODE_LINE_WIDTH);

        // velikost písma, antialiasing
        trPaint.setTextSize(30);
        trPaint.setAntiAlias(true);

        // vykreslit čárový kód
        x = 0;
        // start
        drawBarCodeLine(canvas, trPaint, trPaint, -1, S, START_LINE_PART_WIDTH);
        // levá část
        for(int i = 0; i < code.length / 2; i++) {
            drawBarCodeLine(canvas, tbPaint, trPaint, code[i], L[code[i]], BARCODE_LINE_PART_WIDTH);
        }
        // middle
        drawBarCodeLine(canvas, trPaint, trPaint, -1, M, MIDDLE_LINE_PART_WIDTH);
        // pravá část
        for(int i = code.length / 2; i < code.length; i++) {
            drawBarCodeLine(canvas, tbPaint, trPaint, code[i], R[code[i]], BARCODE_LINE_PART_WIDTH);
        }
        // end
        drawBarCodeLine(canvas, trPaint, trPaint, -1, E, END_LINE_PART_WIDTH);
    }

    private void drawBarCodeLine(Canvas canvas, Paint paint, Paint trPaint, int num, int numDec, int linePartWidth) {
        String numBin = Integer.toBinaryString(numDec);
        char[] numBinFull = String.format("%1$" + linePartWidth + "s", numBin).replace(' ', '0').toCharArray();

        if(num > -1) {
            canvas.drawText(String.valueOf(num), x + linePartWidth / 3 * BARCODE_LINE_WIDTH, (int)(BARCODE_HEIGHT * 1.2), trPaint);
        }

        for(char numBinPart : numBinFull) {
            if(numBinPart == '1') {
                canvas.drawRect(x, 0, x + BARCODE_LINE_WIDTH, BARCODE_HEIGHT, paint);
            }
            x += BARCODE_LINE_WIDTH;
        }
    }
}
