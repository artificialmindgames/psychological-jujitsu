package psychological.jujitsu.setup;

import javax.servlet.http.HttpServlet;

import berlin.game.StrategyFactory;
import berlin.game.TimeoutGameRequestHandler;
import berlin.servlet.BerlinAiServlet;
import berlin.servlet.RequestResponseMapper;
import berlin.strategy.starter.*;

public abstract class Setup {

        public static HttpServlet createServlet() {
                return null;
        }
}
