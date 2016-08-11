package com.dachlab.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dachlab.service.IGpioService;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * 
 * @author dcharles Simple Open Button Listener for test purpose.
 */
public class SimpleLoggingOpenButtonListener implements GpioPinListenerDigital {

	IGpioService gpioService;
	final Logger log = LoggerFactory.getLogger(this.getClass());

	public SimpleLoggingOpenButtonListener(IGpioService gpioService) {
		this.gpioService = gpioService;
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		log.info("Button pushed ! Event fired from pin " + event.getPin().getName() + ". State is " + event.getState() + ".");
	}

}
