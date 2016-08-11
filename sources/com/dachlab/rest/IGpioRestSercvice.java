package com.dachlab.rest;

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
	 */
	boolean blink(String pinName, long delay, long duration);

	/**
	 * Pulse a pin for a duration.
	 * 
	 * @param pinName
	 *            pin to pulse.
	 * @param duration
	 *            Duration during which the pin is on (or off).
	 */
	boolean pulse(String pinName, long duration);

	/**
	 * Set the state of a pin.
	 * 
	 * @param pinName
	 *            the pin.
	 * @param pinState
	 *            The state to set {HIGH,LOW}.
	 */
	boolean setPinState(String pinName, String pinState);

	/**
	 * Switch a pin ON.
	 * 
	 * @param pinName
	 *            the pin.
	 */
	boolean switchOnPin(String pinName);

	/**
	 * Switch a pin off.
	 * 
	 * @param pinName
	 *            the pin.
	 */
	boolean switchOffPin(String pinName);

	/**
	 * Initialize an input pin on the given pinNumber
	 * @param pinName pinNumber
	 * @return the initilized pin.
	 */
	boolean initInputPin(String pinNumber);

}