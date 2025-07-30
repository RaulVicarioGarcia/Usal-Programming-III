public class Rectangulo {

    private float longitud;
    private float anchura;

    public Rectangulo() {
        this.longitud = 1.0f;
        this.anchura = 1.0f;
    }

    public float getLongitud() {
        return longitud;
    }

    public float getAnchura() {
        return anchura;
    }

    public void setLongitud(float longitud) {
        if (longitud > 0.0f && longitud < 20.0f) {
            this.longitud = longitud;
        } else {
            throw new IllegalArgumentException("La longitud debe ser mayor a 0.0 y menor a 20.0.");
        }
    }

    public void setAnchura(float anchura) {
        if (anchura > 0.0f && anchura < 20.0f) {
            this.anchura = anchura;
        } else {
            throw new IllegalArgumentException("La anchura debe ser mayor a 0.0 y menor a 20.0.");
        }
    }

    public float calcularPerimetro() {
        return 2 * (longitud + anchura);
    }

    public float calcularArea() {
        return longitud * anchura;
    }
}
