package com.company;

public class DOMBuilder {
    private String producent;
    private String przekatna;
    private String rozdzielczosc;
    private String rodzaj_powierzchni;
    private String dotykowy;
    private String procesor;
    private String liczba_rdzeni;
    private String taktowanie;
    private String ram;
    private String pojemnosc_dysku;
    private String rodzaj_dysku;
    private String gpu;
    private String pamiec_gpu;
    private String system;
    private String naped;

    public DOMBuilder() {
    }

    public DOMBuilder(String producent, String przekatna, String rozdzielczosc, String rodzaj_powierzchni, String dotykowy, String procesor, String liczba_rdzeni, String taktowanie, String ram, String pojemnosc_dysku, String rodzaj_dysku, String gpu, String pamiec_gpu, String system, String naped) {
        this.producent = producent;
        this.przekatna = przekatna;
        this.rozdzielczosc = rozdzielczosc;
        this.rodzaj_powierzchni = rodzaj_powierzchni;
        this.dotykowy = dotykowy;
        this.procesor = procesor;
        this.liczba_rdzeni = liczba_rdzeni;
        this.taktowanie = taktowanie;
        this.ram = ram;
        this.pojemnosc_dysku = pojemnosc_dysku;
        this.rodzaj_dysku = rodzaj_dysku;
        this.gpu = gpu;
        this.pamiec_gpu = pamiec_gpu;
        this.system = system;
        this.naped = naped;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getPrzekatna() {
        return przekatna;
    }

    public void setPrzekatna(String przekatna) {
        this.przekatna = przekatna;
    }

    public String getRozdzielczosc() {
        return rozdzielczosc;
    }

    public void setRozdzielczosc(String rozdzielczosc) {
        this.rozdzielczosc = rozdzielczosc;
    }

    public String getRodzaj_powierzchni() {
        return rodzaj_powierzchni;
    }

    public void setRodzaj_powierzchni(String rodzaj_powierzchni) {
        this.rodzaj_powierzchni = rodzaj_powierzchni;
    }

    public String getDotykowy() {
        return dotykowy;
    }

    public void setDotykowy(String dotykowy) {
        this.dotykowy = dotykowy;
    }

    public String getProcesor() {
        return procesor;
    }

    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    public String getLiczba_rdzeni() {
        return liczba_rdzeni;
    }

    public void setLiczba_rdzeni(String liczba_rdzeni) {
        this.liczba_rdzeni = liczba_rdzeni;
    }

    public String getTaktowanie() {
        return taktowanie;
    }

    public void setTaktowanie(String taktowanie) {
        this.taktowanie = taktowanie;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getPojemnosc_dysku() {
        return pojemnosc_dysku;
    }

    public void setPojemnosc_dysku(String pojemnosc_dysku) {
        this.pojemnosc_dysku = pojemnosc_dysku;
    }

    public String getRodzaj_dysku() {
        return rodzaj_dysku;
    }

    public void setRodzaj_dysku(String rodzaj_dysku) {
        this.rodzaj_dysku = rodzaj_dysku;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getPamiec_gpu() {
        return pamiec_gpu;
    }

    public void setPamiec_gpu(String pamiec_gpu) {
        this.pamiec_gpu = pamiec_gpu;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getNaped() {
        return naped;
    }

    public void setNaped(String naped) {
        this.naped = naped;
    }
}
