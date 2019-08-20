package com.example.jjp.hotels.dagger;

import com.example.jjp.hotels.modules.hotel.HotelPresenter;
import com.example.jjp.hotels.modules.list.HotelsListPresenter;
import dagger.Component;
import dagger.android.AndroidInjector;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent extends AndroidInjector<App> {

    HotelsListPresenter provideHotelsListPresenter();

    HotelPresenter provideHotelPresenter();

    final class Initializer {
        private Initializer() {
        }

        static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }
}
