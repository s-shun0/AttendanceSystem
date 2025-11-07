package bean;

import java.io.Serializable;

public class User implements Serializable {

	private int id;
	private String name;
	private String email;
	private String password;
	private String job;
	private String classnum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getClassnum() {
		return classnum;
	}

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}


}
