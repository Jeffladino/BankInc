package com.credibanco.app.credibanco_app.services;



import com.credibanco.app.credibanco_app.Dto.TransaccionDto;
import com.credibanco.app.credibanco_app.entities.Transaccion;
import com.credibanco.app.credibanco_app.entities.enums.TransaccionEstado;

public interface ITransaccionService {

    TransaccionDto findById(Long id);
    Transaccion save(Transaccion transaccion, Long cardId);
    TransaccionDto anulTransaccionDto(Long cardId, Long idTransaccion, TransaccionEstado transaccionEstado);


}
