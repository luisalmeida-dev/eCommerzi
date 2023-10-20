package com.example.sales.Enum;

public enum StatesEnum {
    ACRE("AC"),
    ALAGOAS("AL"),
    AMAPA("AP"),
    AMAZONAS("AM"),
    BAHIA("BA"),
    CEARA("CE"),
    ESPIRITO_SANTO("ES"),
    GOIAS("GO"),
    MARANHAO("MA"),
    MATO_GROSSO("MT"),
    MATO_GROSSO_DO_SUL("MS"),
    MINAS_GERAIS("MG"),
    PARA("PA"),
    PARAIBA("PB"),
    PARANA("PR"),
    PERNAMBUCO("PE"),
    PIAUI("PI"),
    RIO_DE_JANEIRO("RJ"),
    RIO_GRANDE_DO_NORTE("RN"),
    RIO_GRANDE_DO_SUL("RS"),
    RONDONIA("RO"),
    SANTA_CATARINA("SC"),
    SAO_PAULO("SP"),
    SERGIPE("SE"),
    TOCANTINS("TO"),
    DISTRITO_FEDERAL("DF");

    private final String uf;

    StatesEnum(String uf) {
        this.uf = uf;
    }

    public String getUf() {
        return uf;
    }
}
