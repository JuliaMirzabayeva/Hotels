package com.example.jjp.hotels.dagger;

import android.app.Application;
import android.content.Context;
import com.example.jjp.hotels.api.ApiService;
import com.example.jjp.hotels.models.hotel.HotelLoader;
import com.example.jjp.hotels.models.hotel.HotelProvider;
import com.example.jjp.hotels.models.hotel.HotelRepository;
import com.example.jjp.hotels.models.list.HotelsListLoader;
import com.example.jjp.hotels.models.list.HotelsListProvider;
import com.example.jjp.hotels.models.list.HotelsListRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
class AppModule {
    private static final String API_URL = "https://raw.githubusercontent.com/iMofas/ios-android-test/master/";
    private App app;

    AppModule(App application) {
        app = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiService.class);
    }

    @Provides
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    HotelLoader provideHotelLoader(ApiService apiService) {
        return new HotelLoader(apiService);
    }

    @Provides
    @Singleton
    HotelRepository provideHotelRepository(HotelLoader hotelLoader) {
        return new HotelRepository(hotelLoader);
    }

    @Provides
    @Singleton
    HotelProvider provideHotelProvider(HotelRepository hotelRepository) {
        return new HotelProvider(hotelRepository);
    }

    @Provides
    @Singleton
    HotelsListLoader provideHotelsListLoader(ApiService apiService) {
        return new HotelsListLoader(apiService);
    }

    @Provides
    @Singleton
    HotelsListRepository provideHotelsListRepository(HotelsListLoader hotelsListLoader) {
        return new HotelsListRepository(hotelsListLoader);
    }

    @Provides
    @Singleton
    HotelsListProvider provideHotelsListProvider(HotelsListRepository hotelsListRepository) {
        return new HotelsListProvider(hotelsListRepository);
    }
}