object Libs {

    const val h2 = "com.h2database:h2:_"
    const val caffeine = "com.github.ben-manes.caffeine:caffeine:_"

    object Spring {
        object Boot {

            private const val groupId = "org.springframework.boot"

            const val web = "$groupId:spring-boot-starter-web"
            const val webflux = "$groupId:spring-boot-starter-webflux"
            const val dataJpa = "$groupId:spring-boot-starter-data-jpa"
            const val test = "$groupId:spring-boot-starter-test"
        }
    }
}
