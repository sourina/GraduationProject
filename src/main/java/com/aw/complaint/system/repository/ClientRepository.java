package com.aw.complaint.system.repository;

import com.aw.complaint.system.business.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByEmailIdAndPassword(String emailId,String password);

}
