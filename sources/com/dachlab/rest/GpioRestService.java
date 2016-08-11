package com.dachlab.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dachlab.service.IGpioService;
import com.dachlab.util.SimpleLoggingOpenButtonListener;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

@RestController
public class GpioRestService implements IGpioRestSercvice {

	@Autowired
	IGpioService gpioService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dachlab.rest.IGpioSercvice#blink(java.lang.String, long, long)
	 */
	@Override
	@RequestMapping("/pulsePin/{pinName}/{delay}/{duration}")
	public boolean blink(@PathVariable String pinName, @PathVariable long delay, @PathVariable long duration) {
		gpioService.blink(pinName, delay, duration);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dachlab.rest.IGpioSercvice#pulse(java.lang.String, long)
	 */
	@Override
	@RequestMapping("/pulsePin/{pinName}/{duration}")
	public @ResponseBody boolean pulse(@PathVariable String pinName, @PathVariable long duration) {
		gpioService.pulse(pinName, duration);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dachlab.rest.IGpioSercvice#setPinState(java.lang.String,
	 * com.pi4j.io.gpio.PinState)
	 */
	@Override
	@RequestMapping("/setPinState/{pinName}/{pinState}")
	public @ResponseBody boolean setPinState(@PathVariable String pinName, @PathVariable String pinState) {
		gpioService.setPinState(pinName, PinState.valueOf(pinState));
		return true;
	}

	@Override
	@RequestMapping("/switchOnPin/{pinName}")
	public @ResponseBody boolean switchOnPin(@PathVariable String pinName) {
		gpioService.setPinState(pinName, PinState.HIGH);
		return true;
	}

	@Override
	@RequestMapping("/switchOffPin/{pinName}")
	public @ResponseBody boolean switchOffPin(@PathVariable String pinName) {
		gpioService.setPinState(pinName, PinState.LOW);
		return true;
	}

	@Override
	@RequestMapping("/initInputPin/{pinNumber}")
	public @ResponseBody boolean initInputPin(@PathVariable String pinNumber) {
		gpioService.initDigitalInputPin(pinNumber, pinNumber, PinPullResistance.PULL_DOWN, new SimpleLoggingOpenButtonListener(gpioService));
		return true;
	}
	
}
