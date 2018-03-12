package ru.skilanov.io.notes.dataInjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Класс модуль предоставляющий Application context.
 */

@Module
public class AppModule {
    Application mApplication;

    /**
     * Конструктор.
     *
     * @param mApplication Application
     */
    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    /**
     * Метод синглтон, возвращающий Application.
     *
     * @return Application
     */
    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
