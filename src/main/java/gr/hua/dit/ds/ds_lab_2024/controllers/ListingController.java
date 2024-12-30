package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.*;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.GeographicalArea;
import gr.hua.dit.ds.ds_lab_2024.entities.enums.Status;
import gr.hua.dit.ds.ds_lab_2024.service.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("listings")
public class ListingController {

    private final OfferService offerService;
    private final ProfileController profileController;
    private final ProfileService profileService;
    private final UserService userService;
    private final RoleService roleService;
    private ListingService listingService;



    public ListingController(ListingService listingService, OfferService offerService, ProfileController profileController, ProfileService profileService, UserService userService, RoleService roleService) {
        this.listingService = listingService;
        this.offerService = offerService;
        this.profileController = profileController;
        this.profileService = profileService;
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("")
    public String showAcceptedListings(@RequestParam(value = "area", required = false)GeographicalArea area, Model model) {

        Profile profile = profileController.getLoggedInUserProfile();

        model.addAttribute("owner",profile);
        model.addAttribute("listings", listingService.getFilteredListings(area));

        return "listing/listings";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/pending")
    public String showPendingListings(Model model) {
        Profile profile = profileController.getLoggedInUserProfile();

        model.addAttribute("owner",profile);
        model.addAttribute("listings", listingService.getPendingListings());
        return "/listing/listings";
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("examine/{id}")
    public String showPendingListing(@PathVariable Long id, Model model){

        model.addAttribute("listing",listingService.getListingById(id));
        return "listing/listing";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("examine/{id}")
    public String changeListingStatus(@PathVariable Long id, @ModelAttribute("listing") Listing listing, Model model) {

        Listing existingListing = listingService.getListingById(id);

        existingListing.setStatus(listing.getStatus());

        listingService.saveListing(existingListing);

        Profile profile = profileController.getLoggedInUserProfile();

        model.addAttribute("owner",profile);
        model.addAttribute("listings", listingService.getPendingListings());
        return "/listing/listings";
    }


    @Secured("ROLE_OWNER")
    @GetMapping("/new")
    public String addListing(Model model) {
        Listing listing = new Listing();
        model.addAttribute("listing", listing);
        return "listing/new";
    }

    @PostMapping("/new")
    public String saveListing(@ModelAttribute("listing") Listing listing, Model model) {
        Profile profile = profileController.getLoggedInUserProfile();
        listing.setProfile(profile);
        listingService.saveListing(listing);


        model.addAttribute("owner",profile);
        model.addAttribute("listings",listingService.getProfileListings(profile));
        return "listing/listings";
    }

    @GetMapping("/profile")
    public String showProfileListings(Model model) {
        Profile profile = profileController.getLoggedInUserProfile();

        model.addAttribute("owner",profile);
        model.addAttribute("listings",listingService.getProfileListings(profile));
        return "listing/listings";
    }

    @Secured("ROLE_TENANT")
    @GetMapping("{id}/offer")
    public String makeOffer(@PathVariable Long id,Model model){
        Offer offer = new Offer();
        model.addAttribute("offer", offer);
        model.addAttribute("listing", listingService.getListingById(id));
        return "offer/offer";
    }

    @Secured("ROLE_TENANT")
    @PostMapping("{id}/offer")
    public String saveOffer(@PathVariable Long id,@ModelAttribute("offer") Offer offer, Model model) {
        Profile profile = profileController.getLoggedInUserProfile();
        offer.setProfile(profile);
        offer.setListing(listingService.getListingById(id));
        offerService.saveOffer(offer);
        model.addAttribute("offers",offerService.getProfileOffers(profile));
        return "offer/offers";
    }


    @GetMapping("/{id}")
    public String showListing(@PathVariable Long id, Model model){
        model.addAttribute("listing",listingService.getListingById(id));
        return "listing/listing";
    }

    @GetMapping("/{id}/offers")
    public String showListingOffers(@PathVariable Long id, Model model) {
        Listing listing = listingService.getListingById(id);


        model.addAttribute("offers",offerService.getListingOffers(listing));
        return "offer/offers";
    }



    @GetMapping("/offer/{id}")
    public String viewOffer(@PathVariable Long id , Model model) {


        model.addAttribute("offer", offerService.getOfferById(id));

        return "offer/examine";
    }


    @GetMapping("/offer/accept/{id}")
    public String acceptOffer(@PathVariable Long id , Model model) {

        Profile owner = profileController.getLoggedInUserProfile();
        Offer offer = offerService.getOfferById(id);

        offer.setStatus(Status.ACCEPTED);
        offerService.saveOffer(offer);

        model.addAttribute("owner",owner);
        model.addAttribute("listings",listingService.getProfileListings(owner));

        return"listing/listings";
    }

    @GetMapping("/offer/decline/{id}")
    public String declineOffer(@PathVariable Long id , Model model) {

        Profile owner = profileController.getLoggedInUserProfile();
        Offer offer = offerService.getOfferById(id);

        offer.setStatus(Status.DECLINED);
        offerService.saveOffer(offer);

        model.addAttribute("owner",owner);
        model.addAttribute("listings",listingService.getProfileListings(owner));

        return"listing/listings";
    }







}
