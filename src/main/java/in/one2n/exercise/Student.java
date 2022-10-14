package in.one2n.exercise;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Student {

    private String firstname;
    private String lastname;
    private String university;
    private Double test1Score;
    private Double test2Score;
    private Double test3Score;
    private Double test4Score;


	// computed fields
    private Double finalScore;
    private Grade grade;
    
    public String getUniversity() {
    	return this.university;
    }

	public Student(String firstname, String lastname, String university) {
        // TODO: add your implementation here
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.university = university;
    }

    public Student(String firstname, String lastname, String university, Double test1Score, Double test2Score, Double test3Score, Double test4Score) {
        // TODO: add your implementation here
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.university = university;
    	this.test1Score = test1Score;
    	this.test2Score = test2Score;
    	this.test3Score = test3Score;
    	this.test4Score = test4Score;
    }

    public Double getFinalScore() {
        // TODO: add your implementation here
    	Double Score = test1Score + test2Score + test3Score + test4Score;
    	Score = Score / 4;
        return Score;
    }
    
    public void setFinalScore(Double finalScore) {
    	this.finalScore = finalScore;
    }
    

	public Grade getGrade() {
        // TODO: add your implementation here
    	if (finalScore >= 70) {
    		return Grade.A;
    	}else if (finalScore >= 50) {
    		return Grade.B;
    	}else if(finalScore >= 35) {
    		return Grade.C;
    	}
    	return Grade.F;
    }
	
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return new EqualsBuilder()
				.append(firstname,student.firstname)
				.append(lastname,student.lastname)
				.append(university,student.university)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,37)
				.append(firstname)
				.append(lastname)
				.append(university)
				.toHashCode();
	}

}

