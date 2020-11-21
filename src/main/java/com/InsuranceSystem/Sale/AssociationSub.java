package com.InsuranceSystem.Sale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociationSub {

	private int insuranceID;
	private int subscribersID;
	
	public AssociationSub(){
		
	}
	
	public AssociationSub(int insuranceID, int subscribersID){
		this.insuranceID = insuranceID;
		this.subscribersID = subscribersID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssociationSub other = (AssociationSub) obj;
		if (insuranceID != other.insuranceID)
			return false;
		if (subscribersID != other.subscribersID)
			return false;
		return true;
	}

}
