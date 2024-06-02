package com.bankinc;

import com.bankinc.controller.TransaccionController;
import com.bankinc.dto.RealizarCompraRequest;
import com.bankinc.dto.AnularTransaccionRequest;
import com.bankinc.entity.Transaccion;
import com.bankinc.service.TransaccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransaccionControllerTest {

    @Mock
    private TransaccionService transaccionService;

    @InjectMocks
    private TransaccionController transaccionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void realizarCompra() {
        // Configuración del mock
        String cardId = "1020301234567801";
        double price = 100;
        Transaccion transaccionRealizada = new Transaccion(); // Simulamos una transacción realizada

        // Creamos una solicitud de compra con el cardId y price proporcionados
        RealizarCompraRequest request = new RealizarCompraRequest();
        request.setCardId(cardId);
        request.setPrice(price);

        // Configuramos el comportamiento del mock
        when(transaccionService.realizarCompra(request)).thenReturn(transaccionRealizada);

        // Ejecución del método bajo prueba
        Transaccion resultado = transaccionController.realizarCompra(request);

        // Verificación
        assertNotNull(resultado);
        assertEquals(transaccionRealizada, resultado);
    }

    @Test
    void consultarTransaccion() {
        // Configuración del mock
        Long transactionId = 123456L;
        Transaccion transaccion = new Transaccion(); // Simulamos una transacción consultada
        when(transaccionService.consultarTransaccion(transactionId)).thenReturn(transaccion);

        // Ejecución del método bajo prueba
        Transaccion resultado = transaccionController.consultarTransaccion(transactionId);

        // Verificación
        assertNotNull(resultado);
        assertEquals(transaccion, resultado);
    }

    @Test
    void anularTransaccion() {
        // Configuración del mock
        String cardId = "1020301234567801";
        Long transactionId = 123456L;
        Transaccion transaccionAnulada = new Transaccion(); // Simulamos una transacción anulada

        // Creamos una solicitud de anulación con el cardId y transactionId proporcionados
        AnularTransaccionRequest request = new AnularTransaccionRequest();
        request.setCardId(cardId);
        request.setTransactionId(transactionId);

        // Configuramos el comportamiento del mock
        when(transaccionService.anularTransaccion(cardId, transactionId)).thenReturn(transaccionAnulada);

        // Ejecución del método bajo prueba
        Transaccion resultado = transaccionController.anularTransaccion(request);

        // Verificación
        assertNotNull(resultado);
        assertEquals(transaccionAnulada, resultado);
    }
}
