package bbs.sentool.izi;

public class Retrait {
	public Long id ;
	public String fileName ;
	public String numClient ;
	public String montant ;
	public int etat ;
	public int traite ;
	public String codeRetour ;
	
	public Retrait(String fileName, String numClient, String montant, int etat, String codeRetour) {
		// TODO Auto-generated constructor stub
		this.fileName = fileName ;
		this.numClient = numClient ;
		this.montant = montant ;
		this.etat = etat ;
		this.traite = 0 ;
		this.codeRetour = codeRetour ;
	}

	public Retrait() {
		// TODO Auto-generated constructor stub
		this.fileName = "" ;
		this.numClient = "" ;
		this.montant = "" ;
		this.etat = 0 ;
		this.traite = 0 ;
		this.codeRetour = "" ;
	}	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNumClient() {
		return numClient;
	}

	public void setNumClient(String numClient) {
		this.numClient = numClient;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getTraite() {
		return traite;
	}

	public void setTraite(int traite) {
		this.traite = traite;
	}

	public String getCodeRetour() {
		return codeRetour;
	}

	public void setCodeRetour(String codeRetour) {
		this.codeRetour = codeRetour;
	}
	
	public void recordOnDB(){
		
	}
	
	public void findOnDB(){		
		
	}

	public void removeFromDB(){		
		
	}

}
