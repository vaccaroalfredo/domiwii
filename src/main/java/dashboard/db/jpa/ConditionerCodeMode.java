package dashboard.db.jpa;

import com.google.gson.annotations.SerializedName;

public enum ConditionerCodeMode {
	@SerializedName("0")
	ZERO{
		@Override
		public String toString() {
			return "0";
		}
	},
	@SerializedName("1")
	ONE{
		@Override
		public String toString() {
			return "1";
		}
	},
	@SerializedName("2")
	TWO{
		@Override
		public String toString() {
			return "2";
		}
	},
	@SerializedName("3")
	THREE{
		@Override
		public String toString() {
			return "3";
		}
	},
	@SerializedName("4")
	FOUR{
		@Override
		public String toString() {
			return "4";
		}
	},
	@SerializedName("5")
	FIVE{
		@Override
		public String toString() {
			return "5";
		}
	},
	@SerializedName("6")
	SIX{
		@Override
		public String toString() {
			return "6";
		}
	},
	@SerializedName("7")
	SEVEN{
		@Override
		public String toString() {
			return "7";
		}
	}

}
