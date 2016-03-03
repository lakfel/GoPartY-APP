package cam.grupo09.goparty.Enumerables;

/**
 * Created by Felipe on 29/02/2016.
 * Define los tipos de bebidas que se registran en el sistema
 */
public enum TipoBebida
{
    GASEOSA("Gaseosa"),
    JUGOS("Jugos"),
    COCKTAIL_CON_LICOR("Cocktail con licor"),
    COCKTAIL_SIN_LICOR("Cocktail sin licor"),
    AGUARDIENTE("Aguardiente"),
    RON("Ron"),
    WHISKY("Whisky"),
    VODKA("Vodka"),
    TEQUILA("Tequila"),
    BRANDY("Brandy"),
    GINEBRA("Ginebra"),
    CERVEZA_ARTESANAL("Cerveza artesanal"),
    CERVEZA_IMPORTADA("Ceveza importada"),
    CERVEZA_NACIONAL("Cerveza nacional"),
    VINO("Vino");

    private String valor;

    TipoBebida(String valor) {
        this.valor = valor;
    }

    @Override

    public String toString() {
        return valor;
    }
}
