package gr.hua.dit.ds.ds_lab_2024.service;

import gr.hua.dit.ds.ds_lab_2024.entities.Listing;
import gr.hua.dit.ds.ds_lab_2024.entities.Offer;
import gr.hua.dit.ds.ds_lab_2024.entities.Profile;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.GeographicalArea;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.PropertyType;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.Status;
import gr.hua.dit.ds.ds_lab_2024.repositories.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ListingService {

    private ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public List<Listing> getListings() {
        return listingRepository.findAll();
    }

    public List<Listing> getAcceptedListings() {
        return listingRepository.findAllByStatus(Status.ACCEPTED);
    }

    public List<Listing> getPendingListings() {
        return listingRepository.findAllByStatus(Status.PENDING);
    }

    public List<Offer> getListingOffers(Long listingId) { return listingRepository.findAllByListingId(listingId); }

    public Listing getListingById(Long listingId) { return listingRepository.findListingByListingId(listingId); }

    public User getUserByListingId(Long listingId) { return listingRepository.findUserByListingId(listingId);}

    public void saveListing(Listing listing) { listingRepository.save(listing); }

    public List<Listing> getProfileListings(Profile profile) { return listingRepository.findAllByProfile(profile); }

    public List<Listing> getFilteredListings(GeographicalArea area) {
        if ( area == null) {
            return listingRepository.findAllByStatus(Status.ACCEPTED);
        }else{

            List<Listing> listings = listingRepository.findAllByStatus(Status.ACCEPTED);
            List<Listing> filteredListings = new ArrayList<Listing>();
            for (Listing listing : listings) {
                if( listing.getArea().equals(area)) {
                    filteredListings.add(listing);
                }
            }
            return filteredListings;
        }
    }


}
