/*
 * Copyright (C) 2010-2020, Danilo Pianini and contributors
 * listed in the main project's alchemist/build.gradle.kts file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */

package it.unibo.alchemist.model.interfaces

import java.io.Serializable

/**
 * A generic obstacle.
 *
 * @param P the position type for the space in which this obstacle is placed.
 */
interface Obstacle<P : Position<P>> : Serializable {

    /**
     * @return the id for this obstacle
     */
    fun getId(): Int

    /**
     * Given a vector (starting point and end point) representing a requested
     * move, this method computes a new end point, representing a cut version of
     * the initial vector, modified in such a way that the end point is outside
     * the obstacle.
     *
     * @param start
     *          starting point of the vector
     * @param end
     *          ending point of the vector
     * @return the intersection point between the vector and the obstacle nearest
     *         to the vector's starting point.
     */
    fun next(start: P, end: P): P
}
