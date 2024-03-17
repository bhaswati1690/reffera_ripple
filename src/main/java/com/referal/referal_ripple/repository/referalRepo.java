package com.referal.referal_ripple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.referal.referal_ripple.entity.ReferalDetail;
@Repository
public interface referalRepo extends JpaRepository<ReferalDetail,Long> {

	Object countSuccessfulReferrals();

	Object sumRewardPoints();

	
	}


