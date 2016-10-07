package dashboard.db.jpa;

import com.google.gson.annotations.SerializedName;

public enum ConditionerCodeMode {
	@SerializedName("HOT_VEL_1")
	HOT_VEL_1,
	@SerializedName("HOT_VEL_4")
	HOT_VEL_4,
	@SerializedName("COLD_VEL_1")
	COLD_VEL_1,
	@SerializedName("COLD_VEL_4")
	COLD_VEL_4,
	@SerializedName("DEUM_VEL_1")
	DEUM_VEL_1,
	@SerializedName("DEUM_VEL_4")
	DEUM_VEL_4,
	@SerializedName("FAN_VEL_1")
	FAN_VEL_1,
	@SerializedName("FAN_VEL_4")
	FAN_VEL_4,
	@SerializedName("STOP")
	STOP

}
