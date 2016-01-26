package demo.main;

import java.io.Serializable;

public class Result implements Serializable {
	public String docName;
	public int rank;
	
	public Result(){}

	public Result(String docName) {
		super();
		this.docName = docName;
		this.rank = 1;
	}
	
	public void updateRank(){
		this.rank += 1;
	}
	
}
