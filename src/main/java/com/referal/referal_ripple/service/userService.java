package com.referal.referal_ripple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.referal.referal_ripple.entity.ReferalDetail;
import com.referal.referal_ripple.entity.UserRegistration;
import com.referal.referal_ripple.repository.UserRepo;
import com.referal.referal_ripple.repository.referalRepo;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class userService {

    @Autowired
    private UserRepo userRepo;

	private static Object referalRepo;

	private com.referal.referal_ripple.repository.referalRepo ReferalRepo;

    private static final int REFERRAL_CODE_LENGTH = 6;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final SecureRandom secureRandom = new SecureRandom();

    public List<UserRegistration> getAllUsers() {
        List<UserRegistration> users = userRepo.findAll();
        for (UserRegistration user : users) {
            generateAndSetReferralCode(user);
        }
        return users;
    }

    public UserRegistration getUserById(long id) {
        Optional<UserRegistration> optUser = userRepo.findById(id);
        return optUser.orElse(null);
    }

    private void generateAndSetReferralCode(UserRegistration user) {
        // Generate referral code
        StringBuilder referralCode = new StringBuilder();
        referralCode.append(user.getId()); // You can use any user-specific information
        while (referralCode.length() < REFERRAL_CODE_LENGTH) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            referralCode.append(CHARACTERS.charAt(randomIndex));
        }
        String generatedReferralCode = referralCode.toString();
        
        user.setReferralCode(generatedReferralCode);
        userRepo.save(user); 
    }
	
    //referral Tracking
   
    @Autowired
    public  userService(referalRepo ReferalRepo) {
        this.ReferalRepo = ReferalRepo;
		this.referralRepository = null;
    }

    public static void trackReferral(ReferalDetail referalDetail) {
        Referral referral = new Referral();
        referral.setReferralCode(referalDetail.getreferralCode());
        ((ReferalDetail) referral).setFirstName(referalDetail.getFirstName());
        referral.setLastName(referalDetail.getLastName());
        referral.setRewardPoints(0);
        
        referalRepo.save(referral);
    
    if (isSuccessfulReferral(referalDetail.getreferralCode())) {
        awardReward(referalDetail.getreferralCode());
    }
    }



private boolean isSuccessfulReferral( String refferalCode) {                                                                                                                                              Object object) {
	 Referral referral =  referalRepo.findByRefferalCode(referralCode);
   
    return  referral != null && ((Object) referral).isSuccess();

}
}


private void awardRewardPoints(String referralCode) {
	Referral referral = referalRepo.findByReferalCode(referralCode);
    if (referral != null) {
        int rewardPoints = 100; 
        referral.setRewardPoints(referral.getRewardPoints() + rewardPoints);
        referalRepo.save(referral);
    }
    }

private static void awardReward(Object object) {
    
    System.out.println("Reward awarded for referral code: " + object);

	
}
 
	public static void registerUser(UserRegistration registrationForm) {
       
        User user = new User();
        user.setName(registrationForm.getFirstName());
        user.setName(registrationForm.getLastName());
        user.setEmail((registrationForm.getEmail());
        user.setPassword(registrationForm.getPassword());
        
       
        UserRepo.save(user);
	
}
 // Statistical Display
	
	private final referalRepo referralRepository;
	
	public ReferralStatistics getReferralStatistics() {
	    ReferralStatistics statistics = new ReferralStatistics();
	    
	    try {
	        statistics.setTotalReferrals(referralRepository.count());
	        statistics.setSuccessfulReferrals(referralRepository.countSuccessfulReferrals());
	        statistics.setRewardPointsEarned(referralRepository.sumRewardPoints());
	    } catch (Exception e) {
	        
	        e.printStackTrace(); 
	       
	        statistics.setTotalReferrals(0);
	        statistics.setSuccessfulReferrals(0);
	        statistics.setRewardPointsEarned(0);
	    }
	    
	    return statistics;
	}

    }


	


    



    
    
    

