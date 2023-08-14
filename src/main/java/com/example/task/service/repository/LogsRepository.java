package com.example.task.service.repository;

import com.example.task.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {

    Logs getLogsByLogsID(Long logsID);

}
