package gr.hua.dit.ds.ds_lab_2024.entities;

import gr.hua.dit.ds.ds_lab_2024.entities.enums.PaymentMethod;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.Status;
import jakarta.persistence.*;

@Entity
@Table
public class Offer {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long offerId;

   @Column
   private Double offerAmount;

   @Enumerated
   @Column
   private Status status;


   @Enumerated
   @Column
   private PaymentMethod paymentMethod;


   @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
           CascadeType.DETACH, CascadeType.REFRESH})
   @JoinColumn(name="profileId")
   private Profile profile;


   @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
           CascadeType.DETACH, CascadeType.REFRESH})
   @JoinColumn(name="listingId")
   private Listing listing;



   public Offer() {
      this.status = Status.PENDING;
      this.offerAmount = 0.0;
   }


   public Double getOfferAmount() {return offerAmount;}

   public void setOfferAmount(Double offerAmount) {this.offerAmount = offerAmount;}

   public Status getStatus() {
      return status;
   }

   public void setStatus( Status status) {
      this.status = status;
   }

   public PaymentMethod getPaymentMethod() {return paymentMethod;}

   public void setPaymentMethod(PaymentMethod paymentMethod) {this.paymentMethod = paymentMethod;}

   public Profile getProfile() {return profile;}

   public void setProfile(Profile profile) {this.profile = profile;}

   public Long getOfferId() {
      return offerId;
   }

   public void setOfferId(Long offerId) {this.offerId = offerId;}

   public Listing getListing() {return listing;}

   public void setListing(Listing listing) {this.listing = listing;}

}
