package com.policy;

import com.policy.bean.Policy;
import com.policy.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling

public class PolicyApplication {
	@Autowired
	PolicyService policyService;

	public static void main(String[] args) {
		SpringApplication.run(PolicyApplication.class, args);



	}

	@Scheduled(fixedRate = 300000) // 5 minutes = 5 * 60 * 1000 milliseconds
	public void executeTask() throws Exception {
		// Call your function or method here
		System.out.println("Executing scheduled task...");
		// Call your function here
		policyService.getJsonPolyFromTable();
	}

}
