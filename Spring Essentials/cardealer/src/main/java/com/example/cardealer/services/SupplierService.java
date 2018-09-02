package com.example.cardealer.services;

import com.example.cardealer.entities.models.dto.SupplierAddModel;
import com.example.cardealer.entities.models.views.SupplierExportModel;

import java.util.List;

public interface SupplierService {
    List<SupplierExportModel> suppliersByIsImporter(Boolean isImporter);
    List<String> getAllSupplierNames();
    SupplierAddModel getOne(Long id);
    Boolean addSupplier(SupplierAddModel model);
    List<SupplierExportModel> allSuppliers();
    void delete(Long id);
}
