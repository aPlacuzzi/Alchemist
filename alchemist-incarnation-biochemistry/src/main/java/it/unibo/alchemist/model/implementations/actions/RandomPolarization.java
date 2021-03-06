/*
 * Copyright (C) 2010-2019, Danilo Pianini and contributors listed in the main project's alchemist/build.gradle file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */
package it.unibo.alchemist.model.implementations.actions;

import it.unibo.alchemist.model.interfaces.CellNode;
import it.unibo.alchemist.model.interfaces.Context;
import it.unibo.alchemist.model.interfaces.Environment;
import it.unibo.alchemist.model.interfaces.Node;
import it.unibo.alchemist.model.interfaces.Position2D;
import it.unibo.alchemist.model.interfaces.Reaction;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

/**
 *
 * @param <P> position type
 */
public final class RandomPolarization<P extends Position2D<P>> extends AbstractRandomizableAction<Double> {

    private static final long serialVersionUID = 1L;
    private final Environment<Double, P> environment;

    /**
     * @param environment the environment
     * @param node the node
     * @param random the {@link RandomGenerator}
     */
    public RandomPolarization(final Environment<Double, P> environment, final Node<Double> node, final RandomGenerator random) {
        super(node, random);
        this.environment = environment;
        if (!(node instanceof CellNode)) {
            throw new UnsupportedOperationException("Polarization can happen only in cells, required CellNode, got "
                + node.getClass());
        }
    }

    /**
     * 
     */
    @Override
    public void execute() {
        final double x = getRandomGenerator().nextFloat() - 0.5;
        final double y = getRandomGenerator().nextFloat() - 0.5;
        P randomVersor;
        if (x == 0) {
            randomVersor = environment.makePosition(0, 1);
        } else if (y == 0) {
            randomVersor = environment.makePosition(1, 0);
        } else {
            final double module = FastMath.sqrt(FastMath.pow(x, 2) + FastMath.pow(y, 2));
            if (module == 0) {
                randomVersor = environment.makePosition(0, 0);
            } else {
                randomVersor = environment.makePosition(x / module, y / module);
            }
        }
        getNode().addPolarization(randomVersor);
    }

    /**
     * 
     */
    @Override
    public Context getContext() {
        return Context.LOCAL;
    }

    @Override
    public RandomPolarization<P> cloneAction(final Node<Double> node, final Reaction<Double> reaction) {
        return new RandomPolarization<>(environment, node, getRandomGenerator());
    }

    @Override
    @SuppressWarnings("unchecked")
    public CellNode<P> getNode()  {
        return (CellNode<P>) super.getNode();
    }

}
