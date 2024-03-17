package com.referal.referal_ripple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.referal.referal_ripple.entity.ReferalDetail;
import com.referal.referal_ripple.entity.UserRegistration;
import com.referal.referal_ripple.repository.UserRepo;
import com.referal.referal_ripple.service.userService;


@RestController
public class UserController<ReferralStatistics> {
	@Autowired
    private UserRepo userRepo;
	private userService UserService;
	
	@GetMapping("/")
	public String registration()
	{
		return "registration";

}
	 @PostMapping("/register")
		public String userRegistration() {
			return  "redirect:/ "; //redirect to next page
			
		}
	 
	    @PostMapping("/register")
	    public ResponseEntity<String> registerUser(@ModelAttribute UserRegistration registrationForm) {
	       
	        userService.registerUser(registrationForm);
	        
	        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
	    }

	        @Autowired
	        public UserController(userService UserService) {
	            this.referralService = null;
				this.UserService = UserService;
	        }
	        

	        @PostMapping("/referral/track")
	        public void trackReferral(@RequestBody ReferalDetail referalDetail) {
	            userService.trackReferral(referalDetail);
	        }
	        
	        //display 
	        private final userService referralService;

	        @Autowired
	        public void DashboardController(userService UserService) {
	            this.UserService = UserService;
	            
	        }
	        
	        @GetMapping("/dashboard")
	        public String showDashboard(Model model) {
	            ReferralStatistics referralStatistics = referralService.getReferralStatistics();
	            model.addAttribute("referralStatistics", referralStatistics);
	            return "dashboard";
	        }


	        }

	    


		

