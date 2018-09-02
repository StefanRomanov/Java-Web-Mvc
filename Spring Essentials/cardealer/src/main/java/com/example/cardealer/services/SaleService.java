package com.example.cardealer.services;

import com.example.cardealer.entities.models.dto.SaleAddDto;
import com.example.cardealer.entities.models.views.SaleExportModel;
import com.example.cardealer.entities.models.views.SaleReviewView;

import java.util.List;

public interface SaleService {
    List<SaleExportModel> allSales();
    SaleExportModel saleById(Long id);
    List<SaleExportModel> discountedSales();
    List<SaleExportModel> discountedByConcretePercent(String percent);
    SaleReviewView saleForReview(SaleAddDto model);
    Boolean addSale(SaleAddDto model);
}
