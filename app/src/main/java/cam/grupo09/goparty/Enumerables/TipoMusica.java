package cam.grupo09.goparty.Enumerables;

/**
 * Created by Felipe on 29/02/2016.
 * Clase que enumera todos los tipos de musica posibles
 */
public enum TipoMusica
{
    SALSA("Salsa"),
    MERENGUE("Merengue"),
    BACHATA("Bachata"),
    REGGAETON("Reggaeton"),
    REGGAE("Reggae"),
    VALLENATO("Vallenato"),
    RANCHERA("Ranchera"),
    POPULAR("Popular"),
    ROCK("Rock"),
    POP("Pop"),
    ELECTRONICA("Electronica"),
    HOUSE("House"),
    JAZZ("Jazz"),
    BLUES("Blues"),
    METAL("Metal"),
    HARD_ROCK("Hard rock"),
    CROSS_OVER("Cross over");

    private String valor;

    TipoMusica(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
