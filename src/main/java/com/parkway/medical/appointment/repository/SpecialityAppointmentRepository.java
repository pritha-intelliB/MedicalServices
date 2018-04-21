package com.parkway.medical.appointment.repository;


import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.parkway.medical.appointment.bo.ApptRequest;

@Repository
public interface SpecialityAppointmentRepository extends CrudRepository<ApptRequest, BigInteger> {

}
