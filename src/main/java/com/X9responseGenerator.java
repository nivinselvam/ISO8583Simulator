/*
 * This class is to generate the X9 fep specific response
 */

package com;

public class X9responseGenerator extends BaseResponseGenerator{
	
	public X9responseGenerator(String requestPacket) {
		super(requestPacket);
	}

	@Override
	public void authorizationPendingBitfieldsUpdate() {
		
	}

	@Override
	public void financialSalesPendingBitfieldsUpdate() {
		
	}

	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {
		
	}

	@Override
	public void reversalPendingBitfieldsUpdate() {	
		
	}

	@Override
	public void reconciliationPendingBitfieldsUpdate() {
		
	}

}
