package com.example.database.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.abstractions.CatPhoto
import com.example.database.daos.CatDao
import java.io.IOException

private const val STARTING_PAGE_INDEX = 0

class CatPagingSource(private val service: CatDao) : PagingSource<Int, CatPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatPhoto> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getCatsFromDB(params.loadSize, page * params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
