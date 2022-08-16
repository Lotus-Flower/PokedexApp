package meehan.matthew.pokedexapp

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesSqlDriver(@ApplicationContext context: Context) = AndroidSqliteDriver(
        schema = Database.Schema,
        context = context,
        name = "pokedexDb"
    )

    @Provides
    @Singleton
    fun providesSqlDatabase(driver: AndroidSqliteDriver) = Database(
        driver = driver
    )
}
