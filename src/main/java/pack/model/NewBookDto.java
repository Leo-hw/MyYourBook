package pack.model;

public class NewBookDto {

	private String nb_name,nb_author,nb_inter,nb_genre,nb_comp,nb_bdate,nb_plot,nb_image, sql;
	private int nb_no,nb_stock,nb_price,nb_scount,nb_readcnt, rn, scount, smonth, plusstock;
	
	public int getNb_no() {
		return nb_no;
	}
	
	public void setNb_no(int nb_no) {
		this.nb_no = nb_no;
	}
	
	public String getNb_name() {
		return nb_name;
	}
	public void setNb_name(String nb_name) {
		this.nb_name = nb_name;
	}
	public String getNb_author() {
		return nb_author;
	}
	public void setNb_author(String nb_author) {
		this.nb_author = nb_author;
	}
	public String getNb_inter() {
		return nb_inter;
	}
	public void setNb_inter(String nb_inter) {
		this.nb_inter = nb_inter;
	}
	public String getNb_genre() {
		return nb_genre;
	}
	public void setNb_genre(String nb_genre) {
		this.nb_genre = nb_genre;
	}
	public String getNb_comp() {
		return nb_comp;
	}
	public void setNb_comp(String nb_comp) {
		this.nb_comp = nb_comp;
	}
	public String getNb_bdate() {
		return nb_bdate;
	}
	public void setNb_bdate(String nb_bdate) {
		this.nb_bdate = nb_bdate;
	}
	public String getNb_plot() {
		return nb_plot;
	}
	public void setNb_plot(String nb_plot) {
		this.nb_plot = nb_plot;
	}
	public String getNb_image() {
		return nb_image;
	}
	public void setNb_image(String nb_image) {
		this.nb_image = nb_image;
	}
	public int getNb_stock() {
		return nb_stock;
	}
	public void setNb_stock(int nb_stock) {
		this.nb_stock = nb_stock;
	}
	public int getNb_price() {
		return nb_price;
	}
	public void setNb_price(int nb_price) {
		this.nb_price = nb_price;
	}
	public int getNb_scount() {
		return nb_scount;
	}
	public void setNb_scount(int nb_scount) {
		this.nb_scount = nb_scount;
	}
	public int getNb_readcnt() {
		return nb_readcnt;
	}
	public void setNb_readcnt(int nb_readcnt) {
		this.nb_readcnt = nb_readcnt;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getScount() {
		return scount;
	}
	public void setScount(int scount) {
		this.scount = scount;
	}
	public int getSmonth() {
		return smonth;
	}
	public void setSmonth(int smonth) {
		this.smonth = smonth;
	}
	public int getPlusstock() {
		return plusstock;
	}
	public void setPlusstock(int plusstock) {
		this.plusstock = plusstock;
	}
}
