package com.stefanini.enums;

public enum MesEnum {

    JANEIRO("Janeiro",1),
    FEVEREIRO("Fevereiro",2),
    MARCO("Março",3),ABRIL("Abril",4),
    MAIO("Maio",5),JUNHO("Junho",6),
    JULHO("Julho",7),AGOSTO("Agosto",8),
    SETEMBRO("Setembro",9),OUTUBRO("Outubro",10),
    NOVEMBRO("Novembro",11),
    DEZEMBRO("Dezembro",12);

    private String Descricao;
    private int numero;

    MesEnum(String descricao,int numero) {
        Descricao = descricao;
        this.numero = numero;
    }

    public String getDescricao() {
        return Descricao;
    }

    public int getNumero() {
        return numero;
    }

    
}
