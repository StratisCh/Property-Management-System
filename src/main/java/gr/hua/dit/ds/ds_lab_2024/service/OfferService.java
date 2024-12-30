package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Listing;
import gr.hua.dit.ds.ds_lab_2024.entities.Offer;
import gr.hua.dit.ds.ds_lab_2024.entities.Profile;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

private OfferRepository offerRepository;

public OfferService(OfferRepository offerRepository) { this.offerRepository = offerRepository; }

    public List<Offer> getOffers() { return offerRepository.findAll(); }

    public void saveOffer(Offer offer) { offerRepository.save(offer); }

    public List<Offer> getProfileOffers(Profile profile) { return offerRepository.findAllByProfile(profile); }

    public List<Offer> getListingOffers(Listing listing) { return offerRepository.findAllByListing(listing); }

    public Offer getOfferById(Long offerId) { return offerRepository.findOfferByOfferId(offerId); }

}
