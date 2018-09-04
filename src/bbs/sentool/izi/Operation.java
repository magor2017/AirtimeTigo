package bbs.sentool.izi;

class Operation{
	private String id;
	private String type;
	private String montant;
	private String tel;
	public Operation(){
		
	}
	public Operation(String type,String montant,String tel){
		this.id="1";
		this.type=type;
		this.montant=montant;
		this.tel=tel;	
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getMontant(){
		return montant;
	}
	public void setMontant(String montant){
		this.montant=montant;
	}
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel=tel;
	}
	public String toString(){
		return "ID : "+id+"\nMontant : "+montant+"\nTEL : "+tel;
	}
	
}