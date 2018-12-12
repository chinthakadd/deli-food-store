package com.chinthakad.delifoodstore.seedwork.aspect;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;

@Configuration
public class AspectConfigurer implements LoadTimeWeavingConfigurer {

    @Override
    public LoadTimeWeaver getLoadTimeWeaver() {
        InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        loadTimeWeaver.addTransformer(new LoggingClassFileTransformer());
        return loadTimeWeaver;
    }
}
