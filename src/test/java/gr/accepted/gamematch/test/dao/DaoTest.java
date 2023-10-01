package gr.accepted.gamematch.test.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@DisplayName("DAO test")
@IncludeTags(value = "dao")
@SelectPackages(value = { "gr.accepted.gamematch.test.dao" })
public class DaoTest {

}
