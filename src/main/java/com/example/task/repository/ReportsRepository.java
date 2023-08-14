package com.example.task.service.repository;

import com.example.task.model.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {

    Reports getReportsByReportID(Long reportID);


}
