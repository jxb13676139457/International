package com.international.model;

public class Score {
	int scoreId;		//考试分数ID
	float listening;//听力
	float oral;		//口语
	float reading;	//阅读
	float writing;	//写作
	float score;	//总分
	InternationalStudent interStu;	//外键，参考InternationalStudent(studentId)
	Exam exm;		//外键，参考Exam(examId)
	
	String reserves1;//备用字段
	String reserves2;
	String reserves3;
	
	public Score() {
		
	}

	
	public int getScoreId() {
		return scoreId;
	}


	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}


	public float getListening() {
		return listening;
	}

	public void setListening(float listening) {
		this.listening = listening;
	}

	public float getOral() {
		return oral;
	}

	public void setOral(float oral) {
		this.oral = oral;
	}

	public float getReading() {
		return reading;
	}

	public void setReading(float reading) {
		this.reading = reading;
	}

	public float getWriting() {
		return writing;
	}

	public void setWriting(float writing) {
		this.writing = writing;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public InternationalStudent getInterStu() {
		return interStu;
	}

	public void setInterStu(InternationalStudent interStu) {
		this.interStu = interStu;
	}

	public Exam getExm() {
		return exm;
	}

	public void setExm(Exam exm) {
		this.exm = exm;
	}

	public String getReserves1() {
		return reserves1;
	}

	public void setReserves1(String reserves1) {
		this.reserves1 = reserves1;
	}

	public String getReserves2() {
		return reserves2;
	}

	public void setReserves2(String reserves2) {
		this.reserves2 = reserves2;
	}

	public String getReserves3() {
		return reserves3;
	}

	public void setReserves3(String reserves3) {
		this.reserves3 = reserves3;
	}
	
}
