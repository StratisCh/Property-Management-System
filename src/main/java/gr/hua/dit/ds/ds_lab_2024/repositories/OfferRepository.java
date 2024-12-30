package gr.hua.dit.ds.ds_lab_2024.repositories;

import gr.hua.dit.ds.ds_lab_2024.entities.Listing;
import gr.hua.dit.ds.ds_lab_2024.entities.Offer;
import gr.hua.dit.ds.ds_lab_2024.entities.Profile;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    Offer findOfferByOfferId(Long offerId);
    User  findUserByOfferId(Long offerId);
    List<Offer> findAllByProfile(Profile profile);
    List<Offer> findAllByListing(Listing listing);

 }
