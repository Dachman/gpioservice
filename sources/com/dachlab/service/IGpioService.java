package com.dachlab.service;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinListener;

public interface IGpioService {

	/**
	 * Blink a pin for a delay and a duration.
	 * 
	 * @param pinName
	 *            pin number to blink.
	 * @param Delay
	 *            delay of the blink.
	 * @param duration
	 *            Duration during which the pin blinks.
	 */
	void blink(String pinName, long delay, long duration);

	/**
	 * Pulse a pin for a duration.
	 * 
	 * @param pinName
	 *            pin name to pulse.
	 * @param duration
	 *            Duration during which the pin is on (or off).
	 */
	void pulse(String pinName, long duration);

	/**
	 * Set the state of a pin.
	 * 
	 * @param pinName
	 *            the pin name.
	 * @param pinState
	 *            The state to set.
	 */
	void setPinState(String pinName, PinState pinState);

	/**
	 * Initialize an input GPIO pin.
	 * 
	 * @param pinNumber
	 *            The pin Number.
	 * @param pinName
	 *            A name for the pin.
	 * @param initialState
	 *            The initial state of the pin after initialization.
	 * @return A reference to the input pin initialized.
	 */
	GpioPinDigitalInput initDigitalInputPin(String pinNumber, String pinName, PinPullResistance pullResistanceState, GpioPinListener listener);

	/**
	 * Initialize an output GPIO pin.
	 * 
	 * @param pinNumber
	 *            The pin Number.
	 * @param pinName
	 *            A name for the pin.
	 * @param initialState
	 *            The initial state of the pin after initialization.
	 * @return A reference to the output pin initialized.
	 */
	GpioPinDigitalOutput initDigitalOutputPin(String pinNumber, String pinName, PinState initialState, PinState shutdownState);

	/**
	 * Pulse a pin for a duration.
	 * 
	 * @param Pin
	 *            pin to pulse.
	 * @param duration
	 *            Duration during which the pin is on (or off).
	 */
	void pulse(GpioPinDigitalOutput pin, long duration);

	/**
	 * Blink a pin for a delay and a duration.
	 * 
	 * @param Pin
	 *            pin to blink.
	 * @param Delay
	 *            delay of the blink.
	 * @param duration
	 *            Duration during which the pin blinks.
	 */
	void blink(GpioPinDigitalOutput pin, long delay, long duration);

	/**
	 * Set the state of a pin.
	 * 
	 * @param pinNumber
	 *            the pin.
	 * @param pinState
	 *            The state to set.
	 */
	void setPinState(GpioPinDigitalOutput pin, PinState pinState);

}