package com.example.demo.campaign;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    boolean existsByTitleAndDescriptionAndCategory(String title, String description, CampaignCategory category);
}
