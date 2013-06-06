package web.shedule.algorithm;

public interface IConstraint {
	public int violations();
	public int getAssignDelta(VarInt x, int val);
	public void propagate(VarInt x, int val);
	public void initPropagate();
	public boolean verify();
}
