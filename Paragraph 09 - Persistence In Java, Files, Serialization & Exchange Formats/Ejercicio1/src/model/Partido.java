package model;

public class Partido {
    
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String resultado;

    public static final String LOCAL_GANA = "1";
    public static final String EMPATE = "X";
    public static final String VISITANTE_GANA = "2";

    public Partido (Equipo equipoLocal, Equipo equipoVisitante) {

        this.equipoLocal=equipoLocal;
        this.equipoVisitante=equipoVisitante;
        this.resultado="Por asignar";

    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }
    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }
    public String getResultado() {
        return resultado;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }
    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }
    public void setResultado(String resultado) {
        if (!esResultadoValido(resultado)) {
            throw new IllegalArgumentException("Resultado no válido. Debe ser '1', 'X' o '2'.");
        }
        this.resultado = resultado;
    }
    private boolean esResultadoValido(String resultado) {
        return LOCAL_GANA.equals(resultado) || EMPATE.equals(resultado) || VISITANTE_GANA.equals(resultado);
    }

    @Override
    public String toString() {
        return equipoLocal + " vs " + equipoVisitante + " - Resultado: " + resultado;
    }

    }
