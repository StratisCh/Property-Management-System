package gr.hua.dit.ds.ds_lab_2024.entities;

import gr.hua.dit.ds.ds_lab_2024.entities.enums.Rank;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.util.List;


@Table
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long profileId;

    @Size(max = 20)
    @Column
    private String firstName;

    @Size(max = 20)
    @Column
    private String lastName;

    @Size(max = 200)
    @Column
    private String description;

    @Column
    private Rank rank;

    @Column
    private Date joinDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="offerId")
    private List<Offer> offers;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="listingId")
    private List<Listing> listings;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userId")
    private User user;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="ratingId")
    private Rating rating;






    public Profile () {
        this.rank = Rank.JUNIOR_ADMIN;
        this.joinDate = new Date(System.currentTimeMillis());
        this.rating = new Rating();
    }


    public void setProfileId(Long profileId) {this.profileId = profileId;}

    public User getUser() {return user;}

    public void setUser(User user) {
        this.user = user;
    }

    public Long getProfileId() {
        return profileId;
    }



    public @Size(max = 200) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 200) String description) {
        this.description = description;
    }

    public @Size(max = 20) String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(max = 20) String lastName) {
        this.lastName = lastName;
    }

    public @Size(max = 20) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(max = 20) String firstName) {
        this.firstName = firstName;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public List<Offer> getOffers() {return offers;}

    public void setOffers(List<Offer> offers) {this.offers = offers;}

    public List<Listing> getListings() {return listings;}

    public void setListings(List<Listing> listings) {this.listings = listings;}

    public Rating getRating() {return rating;}

    public void setRating(Rating rating) {this.rating = rating;}


}
