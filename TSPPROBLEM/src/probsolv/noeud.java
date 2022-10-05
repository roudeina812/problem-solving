package probsolv;

import java.util.ArrayList;
import java.util.List;

public class noeud {

	 private char couleurpiquet;
	    private int niveau;
	    private int nombre;
	    private int distance;
	    private List<noeud> piquetVisite = new ArrayList<noeud>();

	    public noeud(char couleurpiquet, int niveau,int nombre) {
	        super();
	        this.couleurpiquet = couleurpiquet;
	        this.niveau = niveau;
	        this.nombre = nombre;
	    }

	    public int getDistance() {
	        return distance;
	    }

	    public void setDistance(int distance) {
	        this.distance = distance;
	    }

	    public int getNiveau() {
	        return niveau;
	    }

	    public void setNiveau(int niveau) {
	        this.niveau = niveau;
	    }

	    public noeud comparer (noeud np, noeud nf) {
	        float dis1=secondStage.graphe[np.getNombre()-1][nf.getNombre()-1];
	        float dis2=secondStage.graphe[np.getNombre()-1][this.nombre-1];
	        if (dis1-dis2>0) return this;
	        else return nf;
	    }

	    public int getNombre() {
	        return nombre;
	    }

	    public void setNombre(int nombre) {
	        this.nombre = nombre;
	    }

	    public int getCouleurpiquet() {
	        return couleurpiquet;
	    }

	    public void setCouleurpiquet(char piquet) {
	        this.couleurpiquet = piquet;
	    }

	    public List<noeud> getPiquetVisite() {
	        return piquetVisite;
	    }

	    public void setPiquetVisite(List<noeud> piquetVisite) {
	        this.piquetVisite = piquetVisite;
	    }

	    public String toString() {
	        return "le piquet de couleur "+ couleurpiquet
	                +" et de niveau "+ niveau;
	    }

}

