package gr.hua.dit.ds.ds_lab_2024.service;


import gr.hua.dit.ds.ds_lab_2024.entities.Listing;
import gr.hua.dit.ds.ds_lab_2024.entities.Offer;
import gr.hua.dit.ds.ds_lab_2024.entities.Profile;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.Rank;
import gr.hua.dit.ds.ds_lab_2024.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;
    public ProfileService(ProfileRepository profileRepository) { this.profileRepository = profileRepository; }

    public void setProfileRank(Long profileId, Rank rank) { profileRepository.findProfileByProfileId(profileId).setRank(rank);}


    public void saveProfile(Profile profile) { profileRepository.save(profile); }
    public Profile getProfile(Long profileId) { return profileRepository.findProfileByProfileId(profileId);}
    public List<Offer> getProfileOffers (Long profileId) { return profileRepository.findAllOffersByProfileId(profileId); }
    public Profile getProfileByUserId(Long userId) { return profileRepository.findProfileByUser_UserId(userId); }
}
