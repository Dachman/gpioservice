package com.dachlab.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dachlab.exception.GpioServiceException;
import com.dachlab.service.IGpioService;
import com.dachlab.util.SimpleLoggingOpenButtonListener;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

@RestController
public class GpioRestService implements IGpioRestSercvice {

	@Autowired
	IGpioService gpioService;

	@Override
	@RequestMapping(value = "/pulsePin/{pinName}/{delay}/{duration}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> blink(@PathVariable String pinName, @PathVariable long delay, @PathVariable long duration) throws GpioServiceException {
		try {
			gpioService.blink(pinName, delay, duration);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			throw new GpioServiceException("Failed to blink pin " + pinName + ".", e);
		}
	}

	@Override
	@RequestMapping(value = "/pulsePin/{pinName}/{duration}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Boolean> pulse(@PathVariable String pinName, @PathVariable long duration) throws GpioServiceException {
		try {
			gpioService.pulse(pinName, duration);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			throw new GpioServiceException("Failed to pulse pin " + pinName + ".", e);
		}
	}

	@Override
	@RequestMapping(value = "/setPinState/{pinName}/{pinState}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Boolean> setPinState(@PathVariable String pinName, @PathVariable String pinState) throws GpioServiceException {
		try {
			gpioService.setPinState(pinName, PinState.valueOf(pinState));
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			throw new GpioServiceException("Failed to set pin " + pinName + " to state " + pinState + ".", e);
		}
	}

	@Override
	@RequestMapping(value = "/switchOnPin/{pinName}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Boolean> switchOnPin(@PathVariable String pinName) throws GpioServiceException {
		try {
			gpioService.setPinState(pinName, PinState.HIGH);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			throw new GpioServiceException("Failed to switch on pin " + pinName + ".", e);
		}
	}

	@Override
	@RequestMapping(value = "/switchOffPin/{pinName}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Boolean>  switchOffPin(@PathVariable String pinName) throws GpioServiceException {
		try {
			gpioService.setPinState(pinName, PinState.LOW);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			throw new GpioServiceException("Failed to switch off pin " + pinName + ".", e);
		}
	}

	@Override
	@RequestMapping(value = "/initInputPin/{pinNumber}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Boolean>  initInputPin(@PathVariable String pinNumber) throws GpioServiceException {
		try {
			gpioService.initDigitalInputPin(pinNumber, pinNumber, PinPullResistance.PULL_DOWN, new SimpleLoggingOpenButtonListener(gpioService));
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			throw new GpioServiceException("Failed to init " + pinNumber + ".", e);
		}
	}

}
