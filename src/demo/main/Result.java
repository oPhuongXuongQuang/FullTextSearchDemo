package demo.main;

import java.io.Serializable;

public class Result implements Serializable {
	public String docName;
	public int rank;
	
	public Result(){}

	public Result(String docName, int rank) {
		super();
		this.docName = docName;
		this.rank = rank;
	}

	public Result(String docName) {
		super();
		this.docName = docName;
		this.rank = 1;
	}
	
	public void updateRank(){
		this.rank += 1;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
