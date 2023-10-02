package gr.accepted.gamematch.test.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@DisplayName("Service test")
@IncludeTags(value = "dao")
@SelectPackages(value = { "gr.accepted.gamematch.test.service" })
public class ServiceTest {

}
