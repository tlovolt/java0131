package java0131;

import java.sql.Date;

public class Transaction {
	private int num;
	private String itemcode;
	private String itemname;
	private int price;
	private int cnt;
	private Date transdate;
	private String userid;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int num, String itemcode, String itemname, int price, int cnt, Date transdate, String userid) {
		super();
		this.num = num;
		this.itemcode = itemcode;
		this.itemname = itemname;
		this.price = price;
		this.cnt = cnt;
		this.transdate = transdate;
		this.userid = userid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public Date getTransdate() {
		return transdate;
	}
	public void setTransdate(Date transdate) {
		this.transdate = transdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "Transaction [num=" + num + ", itemcode=" + itemcode + ", itemname=" + itemname + ", price=" + price
				+ ", cnt=" + cnt + ", transdate=" + transdate + ", userid=" + userid + "]";
	}
	
	
}
