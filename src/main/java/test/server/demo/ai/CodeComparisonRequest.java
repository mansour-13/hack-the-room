package test.server.demo.ai;

public class CodeComparisonRequest {
    private String code;
    private String codeChallenge;
    private String codeSolution;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeChallenge() {
        return codeChallenge;
    }

    public void setCodeChallenge(String codeChallenge) {
        this.codeChallenge = codeChallenge;
    }

    public String getCodeSolution() {
        return codeSolution;
    }

    public void setCodeSolution(String codeSolution) {
        this.codeSolution = codeSolution;
    }
}
