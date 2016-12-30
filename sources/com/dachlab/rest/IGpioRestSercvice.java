package com.dachlab.rest;

import org.springframework.http.ResponseEntity;

import com.dachlab.exception.GpioServiceException;

public interface IGpioRestSercvice {

	/**
	 * Blink a pin for a delay and a duration.
	 * 
	 * @param pinName
	 *            pin to blink.
	 * @param Delay
	 *            delay of the blink.
	 * @param duration
	 *            Duration during which the pin blinks.
	 * @throws GpioServiceException 
	 */
	ResponseEntity<Boolean> blink(String pinName, long delay, long duration) throws GpioServiceException;

	/**
	 * Pulse a pin for a duration.
	 * 
	 * @param pinName
	 *            pin to pulse.
	 * @param duration
	 *            Duration during which the pin is on (or off).
	 * @throws GpioServiceException 
	 */
	ResponseEntity<Boolean> pulse(String pinName, long duration) throws GpioServiceException;

	/**
	 * Set the state of a pin.
	 * 
	 * @param pinName
	 *            the pin.
	 * @param pinState
	 *            The state to set {HIGH,LOW}.
	 * @throws GpioServiceException 
	 */
	ResponseEntity<Boolean> setPinState(String pinName, String pinState) throws GpioServiceException;

	/**
	 * Switch a pin ON.
	 * 
	 * @param pinName
	 *            the pin.
	 * @throws GpioServiceException 
	 */
	ResponseEntity<Boolean> switchOnPin(String pinName) throws GpioServiceException;

	/**
	 * Switch a pin off.
	 * 
	 * @param pinName
	 *            the pin.
	 * @throws GpioServiceException 
	 */
	ResponseEntity<Boolean> switchOffPin(String pinName) throws GpioServiceException;

	/**
	 * Initialize an input pin on the given pinNumber
	 * @param pinName pinNumber
	 * @return the initilized pin.
	 * @throws GpioServiceException 
	 */
	ResponseEntity<Boolean> initInputPin(String pinNumber) throws GpioServiceException;

}