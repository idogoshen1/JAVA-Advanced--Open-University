
public class Owner implements Cloneable {
	private String name;
	private int phone;
	public Owner(String name, int phone) {
		this.name = name;
		this.phone = phone;
	}
	public Owner(Owner owner) {
		this.name = owner.getName();
		this.phone = owner.getPhone();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "name: " + name + ", phone: " + phone;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Owner))
			return false;
		Owner owner = (Owner) obj;
		return name.equals(owner.getName()) &&
				(phone == owner.getPhone());
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Owner(this);
	}
}
