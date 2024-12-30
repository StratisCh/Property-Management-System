package gr.hua.dit.ds.ds_lab_2024.repositories;

import gr.hua.dit.ds.ds_lab_2024.entities.Listing;
import gr.hua.dit.ds.ds_lab_2024.entities.Offer;
import gr.hua.dit.ds.ds_lab_2024.entities.Profile;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.GeographicalArea;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.PropertyType;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing,Long> {

 List<Listing> findAllByStatus(Status status);

 List<Offer> findAllByListingId(Long listingId);

 Listing findListingByListingId(Long listingId);

 User findUserByListingId(Long listingId);

 List<Listing> findAllByProfile(Profile profile);

 List<Listing> findByArea(GeographicalArea area);

 List<Listing> findByType(PropertyType type);


}
