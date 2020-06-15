package show;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "Booking_table")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String showId;
	private Integer qty;
	private Integer amount;

	@PreRemove
	public void onPreRemove() {

		BookingCanceled bookingCanceled = new BookingCanceled();
		BeanUtils.copyProperties(this, bookingCanceled);
		bookingCanceled.publishAfterCommit();

	}

	@PostPersist
	public void onPostPersist() {
		
		Booked booked = new Booked();
		BeanUtils.copyProperties(this, booked);
		booked.publishAfterCommit();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
