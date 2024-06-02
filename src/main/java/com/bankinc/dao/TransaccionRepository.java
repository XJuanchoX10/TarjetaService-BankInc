package com.bankinc.dao;

import com.bankinc.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
