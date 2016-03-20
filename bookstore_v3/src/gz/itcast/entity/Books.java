package gz.itcast.entity;

import java.util.Date;

public class Books {
	//图书基本信息
	private int id;
	private String name;
	private String covers;
	private String author;
	private String publisher;
	private Date publishtime;
	private double price;
	private double rebate;
	private String authorbrief;
	private String contentbrief;
	//图书分类（封装分类内容）
	private Types types;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCovers() {
		return covers;
	}
	public void setCovers(String covers) {
		this.covers = covers;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRebate() {
		return rebate;
	}
	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	public String getAuthorbrief() {
		return authorbrief;
	}
	public void setAuthorbrief(String authorbrief) {
		this.authorbrief = authorbrief;
	}
	public String getContentbrief() {
		return contentbrief;
	}
	public void setContentbrief(String contentbrief) {
		this.contentbrief = contentbrief;
	}
	public Types getTypes() {
		return types;
	}
	public void setTypes(Types types) {
		this.types = types;
	}
	
	@Override
	public String toString() {
		return "Books [id=" + id + ", name=" + name + ", covers=" + covers
				+ ", author=" + author + ", publisher=" + publisher
				+ ", publishtime=" + publishtime + ", price=" + price
				+ ", rebate=" + rebate + ", authorbrief=" + authorbrief
				+ ", contentbrief=" + contentbrief + ", types=" + types + "]";
	}
	
}
