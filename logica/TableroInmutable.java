package tp.pr5.logica;

public interface TableroInmutable {
/**
 * 
 * @return alto
 */
int getFilas();
/**
 * 
 * @return ancho
 */
int getColumnas();
/**
 * 
 * @param fila fila alto
 * @param col columna ancho
 * @return ficha[fila-1][col-1]
 */
Ficha getCasilla(int fila, int col);


}