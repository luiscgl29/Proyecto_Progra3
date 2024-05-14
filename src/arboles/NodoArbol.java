package arboles;

public class NodoArbol {

    long dpi;
    String nombre;
    String departamento;
    String municipio;
    int cantidadDosis;
    String fechaPrimera;
    String fechaSegunda;
    String fechaTercera;
    String lugarVacunacion;

    NodoArbol HijoIzquierdo, HijoDerecho;

    public NodoArbol() {

    }

    public NodoArbol(String nom,long cui) {
        this.nombre = nom;
        this.dpi = cui;
        
        this.HijoIzquierdo = null;
        this.HijoDerecho = null;
    }

    public long getDpi() {
        return dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public int getCantidadDosis() {
        return cantidadDosis;
    }

    public String getFechaPrimera() {
        return fechaPrimera;
    }

    public String getFechaSegunda() {
        return fechaSegunda;
    }

    public String getFechaTercera() {
        return fechaTercera;
    }

    public String getLugarVacunacion() {
        return lugarVacunacion;
    }

    public NodoArbol getHijoIzquierdo() {
        return HijoIzquierdo;
    }

    public NodoArbol getHijoDerecho() {
        return HijoDerecho;
    }
}
