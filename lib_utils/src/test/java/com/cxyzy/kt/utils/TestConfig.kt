package com.cxyzy.kt.utils


object TestConfig {
    private val TEST_PATH: String
    val FILE_SEP = System.getProperty("file.separator")
    val LINE_SEP = System.getProperty("line.separator")

    init {
        var projectPath = System.getProperty("user.dir")
        projectPath?.let {
            if (!projectPath.contains("lib_utils")) {
                projectPath += FILE_SEP + "lib_utils"
            }
        }

        TEST_PATH = projectPath + FILE_SEP + "src" + FILE_SEP + "test" + FILE_SEP + "res" + FILE_SEP
    }

    internal val PATH_TEMP = TEST_PATH + "temp" + FILE_SEP

    internal val PATH_CACHE = TEST_PATH + "cache" + FILE_SEP

    internal val PATH_ENCRYPT = TEST_PATH + "encrypt" + FILE_SEP

    internal val PATH_FILE = TEST_PATH + "file" + FILE_SEP

    internal val PATH_ZIP = TEST_PATH + "zip" + FILE_SEP


}
