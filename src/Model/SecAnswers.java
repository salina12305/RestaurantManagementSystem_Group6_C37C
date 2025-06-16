
package Model;

public class SecAnswers {
    private String email;
    private String answer1;
    private String answer2;
    private String answer3;
    
    public SecAnswers(String email,String answer1, String answer2, String answer3){
        
        this.email=email;
        this.answer1=answer1;
        this.answer2=answer2;
        this.answer3=answer3;       
    }
            
   
    public String getEmail(){
        return email;
    }
        public void setEmail(String email){
        this.email=email;
    }
    public String getanswer1(){
        return answer1;
    }
        public void setanswer1(String answer1){
        this.answer1=answer1;
    }
    public String getanswer2(){
        return answer2;
    }
        public void setanswer2(String answer2){
        this.answer2=answer2;
    }
    public String getanswer3(){
        return answer3;
    }
        public void setanswer3(String answer3){
        this.answer3=answer3;
    }   
}