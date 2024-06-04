package com.credibanco.app.credibanco_app.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.credibanco.app.credibanco_app.entities.Transaccion;
import com.credibanco.app.credibanco_app.entities.enums.TransaccionEstado;

import jakarta.transaction.Transactional;

public interface ITransaccionRepository extends CrudRepository<Transaccion, Long> {


    
    @Transactional
    @Modifying
    @Query("UPDATE Transaccion t SET t.transaccionEstado = :transaccionEstado WHERE t.cardEntity.cardId = :idCard AND t.transaccionId = :idTransaccion ")
    void updateEstadoById(Long idCard, Long idTransaccion, TransaccionEstado transaccionEstado);

}
