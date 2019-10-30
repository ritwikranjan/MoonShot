package com.haroldadmin.moonshot

import android.app.Application
import androidx.work.Configuration
import com.airbnb.epoxy.Carousel
import com.haroldadmin.moonshot.di.AppComponentHolder
import com.haroldadmin.moonshot.di.DaggerAppComponent
import com.haroldadmin.moonshot.di.appComponent
import com.haroldadmin.moonshot.models.ApplicationInfo
import com.haroldadmin.moonshot.models.isFirstLaunch
import com.haroldadmin.moonshot.notifications.LaunchNotificationsManager
import com.haroldadmin.moonshot.sync.SyncManager
import com.haroldadmin.moonshotRepository.applicationInfo.ApplicationInfoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MoonShot : Application(), Configuration.Provider, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    @Inject lateinit var appInfoUseCase: ApplicationInfoUseCase
    @Inject lateinit var syncManager: SyncManager
    @Inject lateinit var notificationsManager: LaunchNotificationsManager

    override fun getWorkManagerConfiguration(): Configuration {
        val workerFactory = appComponent().workerFactory()
        return Configuration.Builder().setWorkerFactory(workerFactory).build()
    }

    override fun onCreate() {
        initDi()
        super.onCreate()
        Carousel.setDefaultGlobalSnapHelperFactory(null)
        launch {
            if (appInfoUseCase.getApplicationInfo().isFirstLaunch()) {
                syncManager.enableSync()
                notificationsManager.enable()
                appInfoUseCase.save(ApplicationInfo(isFirstLaunch = false))
            }
        }
    }

    private fun initDi() {
        val appComponent = DaggerAppComponent.factory().create(this)
        AppComponentHolder.init(appComponent)
        appComponent.inject(this)
    }
}