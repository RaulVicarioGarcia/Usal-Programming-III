public class Partido {
    
        private String equipoLocal;
        private String equipoVisitante;
        private String resultado;
    
        public Partido(String equipoLocal, String equipoVisitante) {
            this.equipoLocal = equipoLocal;
            this.equipoVisitante = equipoVisitante;
            this.resultado = " ";
        }
    
        public void setResultado(String resultado) {
            this.resultado = resultado;
        }
    
        @Override
        public String toString() {
            return String.format("| %-15s | %-15s | %-3s |", equipoLocal, equipoVisitante, resultado);
        }
    

}
