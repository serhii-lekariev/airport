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

package io.spine.example.airport.tl.passengers;

import io.spine.core.Subscribe;
import io.spine.example.airport.security.PassengerBoarded;
import io.spine.example.airport.security.PassengerDeniedBoarding;
import io.spine.server.projection.Projection;

import static io.spine.example.airport.tl.passengers.BoardingStatus.BOARDED;
import static io.spine.example.airport.tl.passengers.BoardingStatus.WILL_NOT_BE_BOARDED;

final class PassengerProjection extends Projection<PassengerId, Passenger, Passenger.Builder> {

    @Subscribe
    void on(PassengerBoarded event) {
        builder()
                .setFlight(event.getFlight())
                .setStatus(BOARDED);
    }

    @Subscribe
    void on(PassengerDeniedBoarding event) {
        builder()
                .setFlight(event.getFlight())
                .setStatus(WILL_NOT_BE_BOARDED);
    }
}
