package com.example.wallavy.domain.usecase

import com.example.wallavy.domain.model.ResponseData
import com.example.wallavy.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

abstract class UseCase<PARM, TYPE> {

    abstract fun run(parm: PARM): Result<ResponseData<TYPE>>

    operator fun invoke(parm: PARM): Flow<Result<ResponseData<TYPE>>> {
        return channelFlow {
            offer(Result.Loading(true))
            offer(run(parm))
            kotlinx.coroutines.delay(2000) // 테스트용 딜레이
            offer(Result.Loading<ResponseData<TYPE>>(false))
        }
    }
}