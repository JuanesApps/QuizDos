package icesi.i2t.quizdos.model;

public class Pregunta {

    private String enunciado;
    private String[] opciones;

    public Pregunta() {
    }

    public Pregunta(String enunciado) {
        this.enunciado = enunciado;
        this.opciones = new String[4];
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }
}
