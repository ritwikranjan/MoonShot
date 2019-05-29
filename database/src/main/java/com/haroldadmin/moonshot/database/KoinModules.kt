package com.haroldadmin.moonshot.database

import androidx.room.Room
import com.haroldadmin.moonshot.database.capsule.CapsuleDao
import com.haroldadmin.moonshot.database.common.MissionSummaryDao
import com.haroldadmin.moonshot.database.core.CoreDao
import com.haroldadmin.moonshot.database.dragons.DragonsDao
import com.haroldadmin.moonshot.database.dragons.ThrustersDao
import com.haroldadmin.moonshot.database.historical_event.HistoricalEventsDao
import com.haroldadmin.moonshot.database.info.CompanyInfoDao
import com.haroldadmin.moonshot.database.landing_pad.LandingPadDao
import com.haroldadmin.moonshot.database.launch.LaunchDao
import com.haroldadmin.moonshot.database.launch.rocket.RocketSummaryDao
import com.haroldadmin.moonshot.database.launch.rocket.first_stage.FirstStageSummaryDao
import com.haroldadmin.moonshot.database.launch.rocket.second_stage.SecondStageSummaryDao
import com.haroldadmin.moonshot.database.rocket.RocketsDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<MoonShotDb> {
        Room.databaseBuilder<MoonShotDb>(
            androidContext(),
            MoonShotDb::class.java,
            "moonshot-db"
        )
            .build()
    }

    single<CapsuleDao> {
        get<MoonShotDb>().capsuleDao()
    }

    single<MissionSummaryDao> {
        get<MoonShotDb>().missionSummaryDao()
    }

    single<CoreDao> {
        get<MoonShotDb>().coreDao()
    }

    single<DragonsDao> {
        get<MoonShotDb>().dragonsDao()
    }

    single<ThrustersDao> {
        get<MoonShotDb>().thrustersDao()
    }

    single<HistoricalEventsDao> {
        get<MoonShotDb>().historicalEventsDao()
    }

    single<CompanyInfoDao> {
        get<MoonShotDb>().companyInfoDao()
    }

    single<LandingPadDao> {
        get<MoonShotDb>().landingPadDao()
    }

    single<FirstStageSummaryDao> {
        get<MoonShotDb>().firstStageSummaryDao()
    }

    single<SecondStageSummaryDao> {
        get<MoonShotDb>().secondStageSummaryDao()
    }

    single<RocketSummaryDao> {
        get<MoonShotDb>().rocketSummaryDao()
    }

    single<LaunchDao> {
        get<MoonShotDb>().launchDao()
    }

    single<RocketsDao> {
        get<MoonShotDb>().rocketsDao()
    }
}