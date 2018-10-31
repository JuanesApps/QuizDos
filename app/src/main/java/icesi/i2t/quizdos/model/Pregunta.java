package icesi.i2t.quizdos.model;

public class Pregunta {

    private String enunciado;
    private String opcionUno;
    private String opcionDos;
    private String opcionTres;
    private String opcionCuatro;

    public Pregunta() {
    }

    public Pregunta(String enunciado, String opcionUno, String opcionDos, String opcionTres, String opcionCuatro) {
        this.enunciado = enunciado;
        this.opcionUno = opcionUno;
        this.opcionDos = opcionDos;
        this.opcionTres = opcionTres;
        this.opcionCuatro = opcionCuatro;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getOpcionUno() {
        return opcionUno;
    }

    public void setOpcionUno(String opcionUno) {
        this.opcionUno = opcionUno;
    }

    public String getOpcionDos() {
        return opcionDos;
    }

    public void setOpcionDos(String opcionDos) {
        this.opcionDos = opcionDos;
    }

    public String getOpcionTres() {
        return opcionTres;
    }

    public void setOpcionTres(String opcionTres) {
        this.opcionTres = opcionTres;
    }

    public String getOpcionCuatro() {
        return opcionCuatro;
    }

    public void setOpcionCuatro(String opcionCuatro) {
        this.opcionCuatro = opcionCuatro;
    }
}
