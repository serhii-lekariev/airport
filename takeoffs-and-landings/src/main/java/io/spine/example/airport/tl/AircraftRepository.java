/*
 * Copyright 2020, TeamDev. All rights reserved.
 *
 * Redistribution and use in source and/or binary forms, with or without
 * modification, must retain the above copyright notice and the following
 * disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.spine.example.airport.tl;

import io.spine.example.airport.supplies.PlaneId;
import io.spine.example.airport.supplies.PreflightCheckComplete;
import io.spine.server.aggregate.AggregateRepository;
import io.spine.server.route.EventRouting;

import java.util.Set;

import static io.spine.server.route.EventRoute.withId;

final class AircraftRepository extends AggregateRepository<AircraftId, AircraftAggregate> {

    @Override
    protected void setupEventRouting(EventRouting<AircraftId> routing) {
        super.setupEventRouting(routing);
        routing.route(PreflightCheckComplete.class,
                      (message, context) -> id(message.getPlaneId()));
    }

    private static Set<AircraftId> id(PlaneId planeId) {
        return withId(AircraftId.newBuilder()
                                .setUuid(planeId.getValue())
                                .build());
    }
}
