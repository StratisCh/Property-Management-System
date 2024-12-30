package gr.hua.dit.ds.ds_lab_2024.entities;

import gr.hua.dit.ds.ds_lab_2024.entities.enums.GeographicalArea;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.PropertyType;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.Status;
import jakarta.persistence.*;

import java.util.List;

@Table
@Entity
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingId;


    @Column
    private String address;


    @Column
    private Double size;


    @Column
    private Double price;


    @Column
    private int bedrooms;


    @Column
    private int rooms;


    @Column
    private int bathrooms;

    @Column
    private boolean isAvailable;

    @Column
    private String description;


    @Column
    private PropertyType type;


    @Column
    private GeographicalArea area;


    @Column
    private Status status;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="profileId")
    private Profile profile;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="offerId")
    List<Offer> offers;


    //TODO MAKE CLASS IMAGELIST;


    public Listing() {
        this.status = Status.PENDING;
        this.isAvailable = true;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile owner) {
        this.profile = owner;
    }

    public GeographicalArea getArea() {
        return area;
    }

    public void setArea(GeographicalArea area) {
        this.area = area;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
