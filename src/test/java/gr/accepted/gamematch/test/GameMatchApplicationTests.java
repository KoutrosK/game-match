package gr.accepted.gamematch.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@DisplayName("GameMatch test")
@SelectPackages(value = { "gr.accepted.gamematch.test" })
class GameMatchApplicationTests {

}
