package com.school.Cais.Repositories;

import com.school.Cais.Models.Account;
import com.school.Cais.Models.Category;
import com.school.Cais.Models.Subcategory;
import com.school.Cais.Services.SubcategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}