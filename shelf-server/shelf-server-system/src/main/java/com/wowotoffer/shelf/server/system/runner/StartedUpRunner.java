package com.wowotoffer.shelf.server.system.runner;

import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author of
 */
@Component
@RequiredArgsConstructor
public class StartedUpRunner implements ApplicationRunner {

    private final ConfigurableApplicationContext context;
    private final Environment environment;

    @Override
    public void run(ApplicationArguments args) {
        if (context.isActive()) {
            ShelfUtil.printSystemUpBanner(environment);
        }
    }
}
