package com.MedShop.quick_com.Strategies.MedicineAdditionStrategy;

import com.MedShop.quick_com.Models.*;
import com.MedShop.quick_com.Repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckAndInsertStrategy implements MedicineAdditionStrategy {

    MedicineRepository medicine_repo;
    BatchRepository batch_repo;
    CompoundRepository compound_repo;
    CompanyRepository company_repo;
    ItemRepository item_repo;
    ShopRepository shop_repo;


    public CheckAndInsertStrategy(MedicineRepository medicine_repo, BatchRepository batch_repo, CompoundRepository compound_repo, CompanyRepository company_repo,
                                  ItemRepository item_repo, ShopRepository shop_repo) {
        this.medicine_repo = medicine_repo;
        this.batch_repo = batch_repo;
        this.compound_repo = compound_repo;
        this.company_repo = company_repo;
        this.item_repo = item_repo;
        this.shop_repo = shop_repo;
    }

    @Override
    public Item addMedicine(String medicine_name, int quantity, String unit, List<String> compound_names, String batch_number, String mfg_month, int mfg_year,
                            String exp_month, int exp_year, double price, String company_name, String lic_no, String shop_name, int stock) {
        Medicine medicine=createMedicine(medicine_name, quantity, unit);
        Batch batch=createBatch(batch_number,mfg_month,mfg_year,exp_month,exp_year,price);
        List<Compound> compounds=createCompounds(compound_names);
        Company company=createCompany(company_name,lic_no);
       batch.setMedicine(medicine);
       if(!medicine.getBatches().contains(batch))
           medicine.getBatches().add(batch);
       for(Compound compound : compounds) {
           if(!compound.getMedicines().contains(medicine))
               compound.getMedicines().add(medicine);
           if(!medicine.getCompounds().contains(compound))
               medicine.getCompounds().add(compound);
       }
       if(!company.getMedicines().contains(medicine))
           company.getMedicines().add(medicine);
       medicine.setCompany(company);
        medicine=medicine_repo.save(medicine);
        compound_repo.saveAll(compounds);
        batch=batch_repo.save(batch);
        company_repo.save(company);
        Shop shop=shop_repo.fetchShopByName(shop_name);
        Item item=createItem(shop,medicine,batch,stock);
        item=item_repo.save(item);
        return item;
    }


    private Medicine createMedicine(String medicine_name, int quantity, String unit) {
        Medicine medicine=medicine_repo.fetchByMedicineName(medicine_name);
        if(medicine==null) {
            medicine=new Medicine();
            medicine.setMedicine_name(medicine_name);
            medicine.setQuantity(quantity);
            medicine.setUnit(Unit.valueOf(unit.toUpperCase()));
        }
        return medicine;
    }


    private Batch createBatch(String batch_number, String mfg_month, int mfg_year, String exp_month, int exp_year, double price) {
        Batch batch=batch_repo.fetchByBatchNumber(batch_number);
        if(batch == null) {
            batch=new Batch();
            batch.setBatch_number(batch_number);
            batch.setMfg_date(LocalDate.of(mfg_year,getMonthNumber(mfg_month),28));
            batch.setExp_date(LocalDate.of(exp_year, getMonthNumber(exp_month), 28));
            batch.setPrice(price);
            if(LocalDate.now().isAfter(batch.getExp_date()))
                batch.setExpiry_status(ExpiryStatus.EXPIRED);
            else
                batch.setExpiry_status(ExpiryStatus.NOT_EXPIRED);
        }
        return batch;
    }


    private int getMonthNumber(String month) {
        month=month.toLowerCase();
        switch(month) {
            case "jan":
                return 1;
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "apr":
                return 4;
            case "may":
                return 5;
            case "jun":
                return 6;
            case "july":
                return 7;
            case "aug":
                return 8;
            case "sept":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dec":
                return 12;
            default:
                return -1;
        }
    }


    private List<Compound> createCompounds(List<String> compounds) {
        List<Compound> compound_list=new ArrayList<>();
        for(String compound : compounds) {
            Compound comp=compound_repo.fetchByCompoundName(compound);
            if(comp==null) {
                comp=new Compound();
                comp.setCompound_name(compound);
            }
            compound_list.add(comp);
        }
        return compound_list;
    }


    private Company createCompany(String company_name, String lic_no) {
        Company company=company_repo.fetchByCompanyName(company_name);
        if(company==null) {
            company=new Company();
            company.setCompany_name(company_name);
            company.setLic_no(lic_no);
        }
        return company;
    }


    private Item createItem(Shop shop, Medicine medicine, Batch batch, int stock) {
        Item item=item_repo.getItemInShop(shop.getId(), medicine.getId(), batch.getId());
        if(item==null) {
            item=new Item();
            item.setShop(shop);
            item.setMedicine(medicine);
            item.setBatch(batch);
            item.setQty_in_stock(stock);
        }
        else
            item.setQty_in_stock(item.getQty_in_stock()+stock);
        return item;
    }
}
