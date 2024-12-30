package gr.hua.dit.ds.ds_lab_2024.entities;

import jakarta.persistence.*;

@Table
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long ratingId;

    private Double total;
    private Integer count;


    public Rating() {
        this.total = 5.0;
        this.count = 1;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Double average() {
        return total / count;
    }


}
