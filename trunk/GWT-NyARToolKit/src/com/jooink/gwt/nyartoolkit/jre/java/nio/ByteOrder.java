package java.nio;

public class ByteOrder {
	
	enum Order { BIG_ENDIAN, LITTLE_ENDIAN }
	
	private Order order;
	
	private ByteOrder(Order o) {
		this.order = o;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ByteOrder && this.order.equals(((ByteOrder)obj).order);
	}
	
	
	public static ByteOrder BIG_ENDIAN = new ByteOrder(Order.BIG_ENDIAN);
	public static ByteOrder LITTLE_ENDIAN = new ByteOrder(Order.LITTLE_ENDIAN);
	
}
