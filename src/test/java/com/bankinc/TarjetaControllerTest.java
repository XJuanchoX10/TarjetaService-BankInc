package com.bankinc;

import com.bankinc.controller.TarjetaController;
import com.bankinc.dto.ActivarTarjetaRequest;
import com.bankinc.dto.RecargarSaldoRequest;
import com.bankinc.entity.Tarjeta;
import com.bankinc.service.TarjetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TarjetaControllerTest {

    @Mock
    private TarjetaService tarjetaService;

    @InjectMocks
    private TarjetaController tarjetaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generarNumeroDeTarjeta() {
        // Configuración del mock
        String productId = "123456";
        Tarjeta tarjetaGenerada = new Tarjeta(); // Simulamos una tarjeta generada
        when(tarjetaService.generarNumeroDeTarjeta(productId)).thenReturn(tarjetaGenerada);

        // Ejecución del método bajo prueba
        Tarjeta resultado = tarjetaController.generarNumeroDeTarjeta(productId);

        // Verificación
        assertNotNull(resultado);
        assertEquals(tarjetaGenerada, resultado);
    }

    @Test
    void activarTarjeta() {
        // Configuración del mock
        String cardId = "987654321";
        Tarjeta tarjetaActivada = new Tarjeta(); // Simulamos una tarjeta activada

        // Creamos una solicitud de activación de tarjeta con el cardId proporcionado
        ActivarTarjetaRequest request = new ActivarTarjetaRequest();
        request.setCardId(cardId);

        // Configuramos el comportamiento del mock
        when(tarjetaService.activarTarjeta(cardId)).thenReturn(tarjetaActivada);

        // Ejecución del método bajo prueba
        Tarjeta resultado = tarjetaController.activarTarjeta(request);

        // Verificación
        assertNotNull(resultado);
        assertEquals(tarjetaActivada, resultado);
    }

    @Test
    void bloquearTarjeta() {
        // Ejecución del método bajo prueba
        assertDoesNotThrow(() -> tarjetaController.bloquearTarjeta("123456789"));

        // Verificación
        verify(tarjetaService, times(1)).bloquearTarjeta("123456789");
    }

    @Test
    void recargarSaldo() {
        // Configuración del mock
        String cardId = "987654321";
        double balance = 10000;
        Tarjeta tarjetaConSaldo = new Tarjeta(); // Simulamos una tarjeta con saldo recargado

        // Creamos una solicitud de recarga de saldo con el cardId y balance proporcionados
        RecargarSaldoRequest request = new RecargarSaldoRequest();
        request.setCardId(cardId);
        request.setBalance(balance);

        // Configuramos el comportamiento del mock
        when(tarjetaService.recargarSaldo(cardId, balance)).thenReturn(tarjetaConSaldo);

        // Ejecución del método bajo prueba
        Tarjeta resultado = tarjetaController.recargarSaldo(request);

        // Verificación
        assertNotNull(resultado);
        assertEquals(tarjetaConSaldo, resultado);
    }

    @Test
    void consultarSaldo() {
        // Configuración del mock
        String cardId = "987654321";
        double saldoConsultado = 500.0;
        when(tarjetaService.consultarSaldo(cardId)).thenReturn(saldoConsultado);

        // Ejecución del método bajo prueba
        double resultado = tarjetaController.consultarSaldo(cardId);

        // Verificación
        assertEquals(saldoConsultado, resultado);
    }
}
