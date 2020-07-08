/*
 * Copyright (C) 2010-2020, Danilo Pianini and contributors
 * listed in the main project's alchemist/build.gradle.kts file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */

package it.unibo.alchemist.model.implementations.nodes

import it.unibo.alchemist.model.cognitiveagents.CognitiveAgent
import it.unibo.alchemist.model.cognitiveagents.impact.ImpactModel
import it.unibo.alchemist.model.cognitiveagents.impact.individual.Age
import it.unibo.alchemist.model.cognitiveagents.impact.individual.Gender
import it.unibo.alchemist.model.implementations.positions.Euclidean2DPosition
import it.unibo.alchemist.model.interfaces.CognitivePedestrian
import it.unibo.alchemist.model.interfaces.Molecule
import it.unibo.alchemist.model.interfaces.environments.EuclideanPhysics2DEnvironmentWithGraph
import it.unibo.alchemist.model.interfaces.geometry.euclidean2d.ConvexPolygon
import it.unibo.alchemist.model.interfaces.geometry.euclidean2d.Euclidean2DTransformation
import it.unibo.alchemist.model.interfaces.OrientingPedestrian
import it.unibo.alchemist.model.interfaces.PedestrianGroup2D
import org.apache.commons.math3.random.RandomGenerator

/**
 * A cognitive [OrientingPedestrian] in the Euclidean world.
 *
 * @param T the concentration type.
 * @param N the type of nodes of the navigation graph provided by the environment.
 * @param E the type of edges of the navigation graph provided by the environment.
 */
class CognitiveOrientingPedestrian2D<T, N : ConvexPolygon, E> @JvmOverloads constructor(
    environment: EuclideanPhysics2DEnvironmentWithGraph<*, T, N, E>,
    randomGenerator: RandomGenerator,
    knowledgeDegree: Double,
    group: PedestrianGroup2D<T>? = null,
    age: Age,
    gender: Gender,
    danger: Molecule? = null
) : HeterogeneousOrientingPedestrian2D<T, N, E>(
    environment,
    randomGenerator,
    knowledgeDegree = knowledgeDegree,
    group = group,
    age = age,
    gender = gender
), CognitivePedestrian<T, Euclidean2DPosition, Euclidean2DTransformation> {

    /*
     * The cognitive part of the pedestrian.
     */
    override val cognitive =
        ImpactModel(this, compliance) {
            environment.getLayer(danger)
                .map { it.getValue(environment.getPosition(this)) as Double }
                .orElse(0.0)
        }

    /**
     * Allows to specify age and gender with a string.
     */
    @JvmOverloads constructor(
        environment: EuclideanPhysics2DEnvironmentWithGraph<*, T, N, E>,
        randomGenerator: RandomGenerator,
        knowledgeDegree: Double,
        group: PedestrianGroup2D<T>? = null,
        age: String,
        gender: String,
        danger: Molecule? = null
    ) : this(
        environment,
        randomGenerator,
        knowledgeDegree,
        group,
        Age.fromString(age),
        Gender.fromString(gender),
        danger
    )

    /**
     * Allows to specify age with an int and gender with a string.
     */
    @JvmOverloads constructor(
        environment: EuclideanPhysics2DEnvironmentWithGraph<*, T, N, E>,
        randomGenerator: RandomGenerator,
        knowledgeDegree: Double,
        group: PedestrianGroup2D<T>? = null,
        age: Int,
        gender: String,
        danger: Molecule? = null
    ) : this(
        environment,
        randomGenerator,
        knowledgeDegree,
        group,
        Age.fromYears(age),
        Gender.fromString(gender),
        danger
    )

    override fun influencialPeople(): List<CognitiveAgent> =
        senses.fold(listOf()) { accumulator, sphere ->
            accumulator.union(sphere.influentialNodes().filterIsInstance<CognitiveAgent>()).toList()
        }
}
