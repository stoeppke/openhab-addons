/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.novafinedust.internal;

import static org.openhab.binding.novafinedust.internal.NovaFineDustBindingConstants.THING_TYPE_SDS011;

import java.util.Collections;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.eclipse.smarthome.io.transport.serial.SerialPortManager;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The {@link NovaFineDustHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Stefan Triller - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.novafinedust", service = ThingHandlerFactory.class)
public class NovaFineDustHandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_SDS011);

    private final SerialPortManager serialPortManager;

    @Activate
    public NovaFineDustHandlerFactory(@Reference SerialPortManager serialPortManager) {
        this.serialPortManager = serialPortManager;
    }

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_SDS011.equals(thingTypeUID)) {
            return new SDS011Handler(thing, serialPortManager);
        }

        return null;
    }
}