package it.unibo.alchemist.model.implementations.actions

import it.unibo.alchemist.model.implementations.actions.utils.makePosition
import it.unibo.alchemist.model.implementations.actions.utils.times
import it.unibo.alchemist.model.interfaces.Environment
import it.unibo.alchemist.model.interfaces.Pedestrian
import it.unibo.alchemist.model.interfaces.Position
import it.unibo.alchemist.model.interfaces.SteeringAction
import it.unibo.alchemist.model.interfaces.movestrategies.SpeedSelectionStrategy
import it.unibo.alchemist.model.interfaces.movestrategies.TargetSelectionStrategy

/**
 * Combination of multiple steering actions.
 *
 * @param env
 *          the environment inside which the pedestrian moves.
 * @param pedestrian
 *          the owner of this action.
 * @param actions
 *          the list of actions to combine to determine the pedestrian movement.
 * @param formula
 *          the logic according to the actions inside the list are combined.
 */
open class Blended<T, P : Position<P>> @JvmOverloads constructor(
    env: Environment<T, P>,
    pedestrian: Pedestrian<T>,
    actions: List<SteeringAction<T, P>>,
    formula: List<SteeringAction<T, P>>.() -> P = {
        val currentPosition = env.getPosition(pedestrian)
        if (size > 1) {
            with(map { it.nextPosition() to it.target().getDistanceTo(currentPosition) }) {
                val totalDistance = map { it.second }.sum()
                filter { totalDistance > 0 }.map {
                    env.makePosition(it.first * (1 - (it.second / totalDistance)))
                }.fold(currentPosition) { accumulator, position -> accumulator + position }
            }
        } else firstOrNull()?.let { currentPosition + it.nextPosition() } ?: currentPosition
    }
) : SteeringActionImpl<T, P>(
    env,
    pedestrian,
    TargetSelectionStrategy { actions.formula() },
    SpeedSelectionStrategy { pedestrian.walkingSpeed }
)