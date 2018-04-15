package com.example.schedulerdemo.repository;

import com.example.schedulerdemo.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, String> {
}
