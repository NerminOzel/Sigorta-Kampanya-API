package com.example.demo.campaign;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign createCampaign(Campaign campaign) {
        if (isDuplicate(campaign)) {
            campaign.setStatus(CampaignStatus.MUKERRER);
        } else {
            campaign.setInitialStatus();
        }
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public boolean isDuplicate(Campaign campaign) {
        return campaignRepository.existsByTitleAndDescriptionAndCategory(
                campaign.getTitle(), campaign.getDescription(), campaign.getCategory());
    }

    public Campaign changeStatus(Long id, CampaignStatus status) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.setStatus(status);
        return campaignRepository.save(campaign);
    }
}