package koalaTest.order.model.vo;

import java.util.ArrayList;

public class OrderDetailData {
	private Order o;
	private ArrayList<OrderDetail> orderDetail;
	
	public OrderDetailData() {
		super();
	}

	public OrderDetailData(Order o, ArrayList<OrderDetail> orderDetail) {
		super();
		this.o = o;
		this.orderDetail = orderDetail;
	}

	public Order getO() {
		return o;
	}

	public void setO(Order o) {
		this.o = o;
	}

	public ArrayList<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(ArrayList<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}	
}
