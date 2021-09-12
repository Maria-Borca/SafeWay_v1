package com.example.safeway.model.Runnables;

import com.example.safeway.model.Translation;

public class TranslationRunnable implements Runnable {
    Translation translation;

    public TranslationRunnable() {
    }

    @Override
    public void run() {
        translation.init();
    }
}
