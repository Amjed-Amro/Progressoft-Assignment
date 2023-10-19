package com.bloomberg.fxdeals;


import com.bloomberg.fxdeals.models.dto.FxDealRequestDto;
import com.bloomberg.fxdeals.services.imp.FxDealServicesImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UnitTests {
    @Autowired
    private FxDealServicesImp fxDealServicesImp;

    @Test
    void tesCheckCurrencyCode() {
        try {
            fxDealServicesImp.checkCurrencyCode("JOD");
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testCheckNotCurrencyCode() {
        try {
            fxDealServicesImp.checkCurrencyCode("AAA");
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void testAddDeal() {
        try {
            FxDealRequestDto dto = new FxDealRequestDto(101010, "USD", "JOD", "10-10-2010 10:10", 20.2);
            fxDealServicesImp.addDeal(dto);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testFindFxDeal() {
        try {
            assertNotNull(fxDealServicesImp.getFxDealById("101010"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testFindFxDealNot() {
        try {
            fxDealServicesImp.getFxDealById("1");
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
