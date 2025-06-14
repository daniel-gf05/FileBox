package com.daniel.filebox.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.filebox.Models.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{
}
