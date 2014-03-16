package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1, 1);
        varasto = new Varasto(1, 2);
        varasto = new Varasto(-1, 2);
        varasto = new Varasto(-1, -1);
        varasto.toString();
    }

    @Test
    public void saldoEiYlity() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoEiPakastu() {
        varasto.lisaaVarastoon(2);
        varasto.otaVarastosta(3);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void paljonkoMahtuuToimii() {
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        varasto.lisaaVarastoon(2);
        assertEquals(8, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void TyhjastaEiVoiNyhjasta() {
        varasto.otaVarastosta(3);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void TyhjenetaanVarasto() {
        varasto.lisaaVarastoon(3);
        varasto.otaVarastosta(3);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void VoiLisataKahdesti() {
        varasto.lisaaVarastoon(3);
        varasto.lisaaVarastoon(3);
        assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void VoiOttaaKahdesti() {
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(3);
        varasto.otaVarastosta(3);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void GetteritToimii() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void ToStringToimii() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }

    @Test
    public void EiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void EiVoiOttaaNegatiivista() {
        varasto.lisaaVarastoon(2);
        varasto.otaVarastosta(-2);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);

    }
}
