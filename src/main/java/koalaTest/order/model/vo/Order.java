package koalaTest.order.model.vo;

import java.util.ArrayList;

import koalaTest.cart.model.vo.Cart;

public class Order {
	private int orderNo;
	private int userNo;
	private String orderDate;
	private int orderQuan; // 총 주문 수량
	private int orderPrice; // 총 주문 금액
	private String orderWonPrice;
	private String orderStatus;
	private String shippingName;
	private String shippingPhone;
	private String shippingAddr;
	private String impUid;
	private String userName;
	
	public Order() {
		super();
	}

	public Order(int orderNo, int userNo, String orderDate, int orderQuan, int orderPrice, String orderWonPrice,
			String orderStatus, String shippingName, String shippingPhone, String shippingAddr, String impUid,
			String userName) {
		super();
		this.orderNo = orderNo;
		this.userNo = userNo;
		this.orderDate = orderDate;
		this.orderQuan = orderQuan;
		this.orderPrice = orderPrice;
		this.orderWonPrice = orderWonPrice;
		this.orderStatus = orderStatus;
		this.shippingName = shippingName;
		this.shippingPhone = shippingPhone;
		this.shippingAddr = shippingAddr;
		this.impUid = impUid;
		this.userName = userName;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderQuan() {
		return orderQuan;
	}

	public void setOrderQuan(int orderQuan) {
		this.orderQuan = orderQuan;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderWonPrice() {
		return orderWonPrice;
	}

	public void setOrderWonPrice(String orderWonPrice) {
		this.orderWonPrice = orderWonPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getShippingAddr() {
		return shippingAddr;
	}

	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}

	public String getImpUid() {
		return impUid;
	}

	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
