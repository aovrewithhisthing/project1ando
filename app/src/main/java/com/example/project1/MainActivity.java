package com.example.project1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    private static final String STATE_COUNT = "state_count";

    // Palet warna-warni saat mencapai kelipatan 5
    private final int[] mRainbowColors = {
        Color.parseColor("#E91E63"), // Pink
        Color.parseColor("#9C27B0"), // Purple
        Color.parseColor("#2196F3"), // Blue
        Color.parseColor("#009688"), // Teal
        Color.parseColor("#4CAF50"), // Green
        Color.parseColor("#FF9800"), // Orange
        Color.parseColor("#F44336")  // Red
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowCount = findViewById(R.id.show_count);

        // Restore state if available (e.g., after screen rotation)
        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(STATE_COUNT, 0);
        }
        updateCountDisplay();
    }

    /**
     * Menampilkan pesan Toast ketika tombol Toast ditekan.
     */
    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Menambah nilai hitungan dan memperbarui tampilan angka ketika tombol Count ditekan.
     */
    public void countUp(View view) {
        mCount++;
        updateCountDisplay();
    }

    /**
     * Memperbarui teks angka dan warna background sesuai kelipatan 5.
     */
    private void updateCountDisplay() {
        if (mShowCount != null) {
            mShowCount.setText(String.valueOf(mCount));

            // Jika kelipatan 5 (dan bukan 0), ganti warna background secara dinamis/bergantian
            if (mCount > 0 && mCount % 5 == 0) {
                int colorIndex = ((mCount / 5) - 1) % mRainbowColors.length;
                mShowCount.setBackgroundColor(mRainbowColors[colorIndex]);
            } else {
                // Kembalikan ke warna default (kuning)
                mShowCount.setBackgroundColor(ContextCompat.getColor(this, R.color.color_yellow));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_COUNT, mCount);
    }
}