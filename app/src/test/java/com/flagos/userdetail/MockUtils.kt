package com.flagos.userdetail

import com.flagos.userdetail.domain.*

object MockUtils {

    fun mockUserDetailItem() = UserDetailItem(
        listOf(
            Results(
                gender = "female",
                name = Name("Ms", "Birgid", "Koller"),
                login = Login(
                    "27da6dc4-32dd-4c84-8cfc-2adaac95800e",
                    "silvercat674",
                    "yeah",
                    "kYZWFssX",
                    "a4508c9515f9e90d3450af4d33c7f7e0",
                    "cade4bc11fb20a3aabeaf729fd72e7175fe7aa9c",
                    "cda82de12a18298adc91d16e05ddb4364b6526a0f6465827d69d0fee3e0b8daf"
                )
            )
        ),
        Info("b12058d21cfe8639", 1, 1, "1.4")
    )
}
