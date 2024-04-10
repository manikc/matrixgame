package designer;

public class SymbolConfig {
	 private double rewardMultiplier;
	    private String type;
	    private Integer extra;
	    private String impact;

	    // Constructor, getters, and setters
	    public SymbolConfig() {}

	    public double getRewardMultiplier() {
	        return rewardMultiplier;
	    }

	    public void setRewardMultiplier(double rewardMultiplier) {
	        this.rewardMultiplier = rewardMultiplier;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public Integer getExtra() {
	        return extra;
	    }

	    public void setExtra(Integer extra) {
	        this.extra = extra;
	    }

	    public String getImpact() {
	        return impact;
	    }

	    public void setImpact(String impact) {
	        this.impact = impact;
	    }
	}
