package com.ahmadhamwi.medicus_task.infrastructure.datasource

import com.ahmadhamwi.medicus_task.infrastructure.api.util.INetworkChecker


abstract class BaseRemoteDataSource(val networkChecker: INetworkChecker) {
}