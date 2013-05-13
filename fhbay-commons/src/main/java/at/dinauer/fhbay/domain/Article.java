package at.dinauer.fhbay.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "qryFindByCategoryAndPattern", query = "SELECT a FROM Article a INNER JOIN a.categories c WHERE c.id = :catId AND (lower(a.name) LIKE :pattern OR lower(a.description) LIKE :pattern)")
public class Article implements Serializable, Comparable<Article> {

	private static final long serialVersionUID = -1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(length = 5000)
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer seller;

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer buyer;

	@Column(nullable = false)
	private double initialPrice;

	private double finalPrice;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Enumerated(EnumType.STRING)
	private ArticleState state = ArticleState.OFFERED;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ArticleCategory",
		joinColumns = {@JoinColumn(name = "article_id")},
		inverseJoinColumns = {@JoinColumn(name = "category_id")})
	private List<Category> categories = new ArrayList<>();

	public Article() {
	}

	public Article(String name, String description, double initialPrice,
			Date startDate, Date endDate) {
		this.name = name;
		this.description = description;
		this.initialPrice = initialPrice;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Customer getSeller() {
		return seller;
	}

	public void setSeller(Customer seller) {
		this.seller = seller;
	}

	public Customer getBuyer() {
		return buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArticleState getState() {
		return state;
	}

	public void setState(ArticleState state) {
		this.state = state;
	}

	public String toString() {
		return String
				.format("Article (%d): name=%s, articleState=%s, initialPrice=%.2f, finalPrice=%.2f",
						getId(), getName(), 
						getState().toString(),
						getInitialPrice(), 
						getFinalPrice());
	}

	@Override
	public int compareTo(Article otherArticle) {
		return this.getId().compareTo(otherArticle.getId());
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void addCategory(Category category) {
		categories.add(category);
	}
}
