package pojo;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -6916588270962493547L;
    private int id;
    private String name,password,phone,email,problem,answer;

    public User() {
    }

    public User(int id, String name, String pssword, String phone, String email, String problem, String answer) {
        this.id = id;
        this.name = name;
        this.password = pssword;
        this.phone = phone;
        this.email = email;
        this.problem = problem;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", problem='" + problem + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String pssword) {
        this.password = pssword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}


