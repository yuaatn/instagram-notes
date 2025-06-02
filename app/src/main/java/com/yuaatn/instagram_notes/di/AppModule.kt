package com.yuaatn.instagram_notes.di

import android.content.Context
import com.yuaatn.instagram_notes.data.FileNotebook
import com.yuaatn.instagram_notes.data.FileNotebookProxy
import com.yuaatn.instagram_notes.data.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNotesRepository(
        @ApplicationContext context: Context
    ): NotesRepository = FileNotebookProxy(
        fileNotebook = FileNotebook(context)
    )
}
