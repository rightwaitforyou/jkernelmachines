/*******************************************************************************
 * Copyright (c) 2016, David Picard.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************/
package net.jkernelmachines.test.classifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.jkernelmachines.classifier.DoubleSGD;
import net.jkernelmachines.type.TrainingSample;
import net.jkernelmachines.util.generators.GaussianGenerator;

import org.junit.Before;
import org.junit.Test;

/**
 * @author picard
 *
 */
public class DoubleSGDTest {

	
	List<TrainingSample<double[]>> train;
	DoubleSGD svm;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		GaussianGenerator g = new GaussianGenerator(10, 5.0f, 1.0);
		train = g.generateList(10);
		
		svm = new DoubleSGD();
	}

	/**
	 * Test method for {@link net.jkernelmachines.classifier.DoubleSGD#train(fr.lip6.jkernelmachines.TrainingSample)}.
	 */
	@Test
	public final void testTrainOfTrainingSampleOfdouble() {
		svm = new DoubleSGD();
		for(TrainingSample<double[]> t : train) {
			svm.train(t);
		}
		for(TrainingSample<double[]> t : train) {
			double v = t.label * svm.valueOf(t.sample);
			assertTrue(v > 0);
		}
	}
	
	/**
	 * Test method for {@link net.jkernelmachines.classifier.DoubleSGD#train(java.util.List)}.
	 */
	@Test
	public final void testTrainListOfTrainingSampleOfdouble() {
		svm.train(train);
		for(TrainingSample<double[]> t : train) {
			double v = t.label * svm.valueOf(t.sample);
			assertTrue(v > 0);
		}
	}

	/**
	 * Test method for {@link net.jkernelmachines.classifier.DoubleSGD#trainOnce(java.util.List)}.
	 */
	@Test
	public final void testTrainOnce() {
		svm.train(train);
		svm.trainOnce(train);
		for(TrainingSample<double[]> t : train) {
			double v = t.label * svm.valueOf(t.sample);
			assertTrue(v > 0);
		}
	}

	/**
	 * Test method for {@link net.jkernelmachines.classifier.DoubleSGD#setLoss(int)}.
	 */
	@Test
	public final void testSetLoss() {
		svm.setLoss(DoubleSGD.HINGELOSS);
		assertEquals(DoubleSGD.HINGELOSS, svm.getLoss());
		
		svm.setLoss(DoubleSGD.LOGLOSS);
		assertEquals(DoubleSGD.LOGLOSS, svm.getLoss());
		
		svm.setLoss(DoubleSGD.LOGLOSSMARGIN);
		assertEquals(DoubleSGD.LOGLOSSMARGIN, svm.getLoss());
		
		svm.setLoss(DoubleSGD.SMOOTHHINGELOSS);
		assertEquals(DoubleSGD.SMOOTHHINGELOSS, svm.getLoss());
		
		svm.setLoss(DoubleSGD.SQUAREDHINGELOSS);
		assertEquals(DoubleSGD.SQUAREDHINGELOSS, svm.getLoss());
	}

	/**
	 * Test method for {@link net.jkernelmachines.classifier.DoubleSGD#setLambda(double)}.
	 */
	@Test
	public final void testSetLambda() {
		svm.setLambda(1e-3);
		assertEquals(1e-3, svm.getLambda(), 1e-15);
	}

	/**
	 * Test method for {@link net.jkernelmachines.classifier.DoubleSGD#setHasBias(boolean)}.
	 */
	@Test
	public final void testSetHasBias() {
		svm.setHasBias(true);
		assertTrue(svm.isHasBias());
	}

	/**
	 * Test method for {@link net.jkernelmachines.classifier.DoubleSGD#setEpochs(int)}.
	 */
	@Test
	public final void testSetEpochs() {
		svm.setEpochs(10);
		assertEquals(10, svm.getEpochs());
	}

	/**
	 * Test method for {@link net.jkernelmachines.classifier.DoubleSGD#setShuffle(boolean)}.
	 */
	@Test
	public final void testSetShuffle() {
		svm.setShuffle(true);
		assertTrue(svm.isShuffle());
	}

}
