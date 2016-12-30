package com.dachlab.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinListener;

@Service("gpioService")
public class GpioService implements IGpioService {

	final Logger log = LoggerFactory.getLogger(this.getClass());

	/** GPIO controller. */
	final GpioController gpio = GpioFactory.getInstance();

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
	@Override
	public GpioPinDigitalOutput initDigitalOutputPin(final String pinNumber, final String pinName, final PinState initialState, final PinState shutdownState) {
		log.info("Initialize pin " + pinName + " (GPIO" + pinNumber + ").");
		final GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByName("GPIO " + pinNumber), pinName, initialState);
		gpioPin.setShutdownOptions(true, shutdownState);
		return gpioPin;
	}

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
	@Override
	public GpioPinDigitalInput initDigitalInputPin(final String pinNumber, final String pinName, final PinPullResistance pullResistanceState, final GpioPinListener listener) {
		log.info("Initialize pin " + pinName + " (GPIO " + pinNumber + ").");
		final GpioPinDigitalInput gpioPin = gpio.provisionDigitalInputPin(RaspiPin.getPinByName("GPIO " + pinNumber), pinName, pullResistanceState);
		gpioPin.addListener(listener);
		return gpioPin;
	}

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
	@Override
	public void blink(final GpioPinDigitalOutput pin, final long delay, final long duration) {
		log.info("Blink pin " + pin.getName() + ".");
		pin.blink(delay, duration);
	}

	/**
	 * Pulse a pin for a duration.
	 * 
	 * @param Pin
	 *            pin to pulse.
	 * @param duration
	 *            Duration during which the pin is on (or off).
	 */
	@Override
	public void pulse(final GpioPinDigitalOutput pin, final long duration) {
		log.info("Pulse pin " + pin.getName() + ".");
		pin.pulse(duration);
	}

	/**
	 * Set the state of a pin.
	 * 
	 * @param pinNumber
	 *            the pin.
	 * @param pinState
	 *            The state to set.
	 */
	@Override
	public void setPinState(final GpioPinDigitalOutput pin, final PinState pinState) {
		log.info("Set pin " + pin.getName() + " to state " + pinState.name() + ".");
		pin.setState(pinState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dachlab.service.IGpioService#blink(java.lang.String, long, long)
	 */
	@Override
	public void blink(final String pinName, final long delay, final long duration) {
		log.info("Blink pin " + pinName + ".");
		blink(((GpioPinDigitalOutput) getProvisionedPin(pinName)), delay, duration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dachlab.service.IGpioService#pulse(java.lang.String, long)
	 */
	@Override
	public void pulse(final String pinName, final long duration) {
		log.info("Pulse pin " + pinName + ".");
		pulse(((GpioPinDigitalOutput) getProvisionedPin(pinName)), duration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dachlab.service.IGpioService#setPinState(java.lang.String,
	 * com.pi4j.io.gpio.PinState)
	 */
	@Override
	public void setPinState(final String pinName, final PinState pinState) {
		log.info("Set pin " + pinName + " to state " + pinState.name() + ".");
		((GpioPinDigitalOutput) getProvisionedPin(pinName)).setState(pinState);

		printGPIOPinStates();
	}

	/**
	 * Get or initialize a pin. Initialized pin are GpioPinDigitalOutput type.
	 * 
	 * @param pinName
	 *            pinName or pin number if not initialized
	 * @return the pin found or the pin initialized
	 */
	private GpioPin getProvisionedPin(final String pinName) {
		GpioPin pin = null;
		final Collection<GpioPin> pinsCollection = gpio.getProvisionedPins();
		for (GpioPin gpioPin : pinsCollection) {
			if (gpioPin.getName().equals(pinName)) {
				pin = gpioPin;
				break;
			}
		}

		// Initialize if not initialized.
		try {
			if (pin == null) {
				pin = initDigitalOutputPin(pinName, pinName, PinState.LOW, PinState.LOW);
			}
		} catch (Exception e) {
			log.error("Unable to initialize the pin " + pinName + " while trying to get it.", e);
		}
		return pin;
	}

	/**
	 * Print GPIO state.
	 */
	private void printGPIOPinStates() {
		final Collection<GpioPin> pinsCollection = gpio.getProvisionedPins();
		for (GpioPin gpioPin : pinsCollection) {
			log.info(gpioPin.getName() + " : " + (gpioPin == null ? "" : ((GpioPinDigitalOutput) gpioPin).getState()));
		}
	}

	/**
	 * Shutdown any GPIO activities / threads.
	 */
	@Override
	public void shutdownGPIO() {
		log.info("Shutting down GPIO.");
		gpio.shutdown();
		log.info("GPIO activities and threads stopped.");
	}
}
