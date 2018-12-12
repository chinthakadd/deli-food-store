package com.chinthakad.delifoodstore.seedwork.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * Simple Logging Transformer that acts as a decorator for the weaving process, and logs
 * every time the weaving process occurs.
 */
public class LoggingClassFileTransformer implements ClassFileTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingClassFileTransformer.class);

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        //LOGGER.info("======================= Transformer Applied to: {}", className);
        return classfileBuffer;
    }
}
