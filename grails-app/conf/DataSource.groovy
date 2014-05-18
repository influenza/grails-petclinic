def dbHost = System.getenv()['PETCLINIC_DB'] ?: "localhost"

hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
	format_sql = true
	use_sql_comments = true
}

environments {
    development {
        dataSource {
            dbCreate='update'
            url="jdbc:postgresql://${dbHost}:5432/petclinic"
            username = "grails"
            password = "super secure"
            pooled = true
            logSql = false // <-- set to true to see SQL queries being sent to DB
            driverClassName = "org.postgresql.Driver"
            dialect = net.sf.hibernate.dialect.PostgreSQLDialect
		
            properties {
                maxActive = -1
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                numTestsPerEvictionRun = 3
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = true
                validationQuery = "SELECT 1"
            }
        }
    }

    test {
        dataSource {
            dbCreate = "update"
            url = 'jdbc:postgresql://${dbHost}:5432/petclinic'
        }
    }

    production {
        dataSource {
            pooled = true
            driverClassName = "org.postgresql.Driver"
            dialect = net.sf.hibernate.dialect.PostgreSQLDialect

            dbCreate = "update"
            jndiName = "java:comp/env/petclinicDatasource"
        }
    }
}
