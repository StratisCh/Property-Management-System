package gr.hua.dit.ds.ds_lab_2024.repositories;


import gr.hua.dit.ds.ds_lab_2024.entities.Listing;
import gr.hua.dit.ds.ds_lab_2024.entities.Offer;
import gr.hua.dit.ds.ds_lab_2024.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {


    Profile findProfileByProfileId(Long profileId);
    List<Offer> findAllOffersByProfileId(Long profileId);
    Profile findProfileByUser_UserId(Long userId);



}
