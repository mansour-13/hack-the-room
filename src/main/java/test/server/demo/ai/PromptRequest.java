package test.server.demo.ai;

public class PromptRequest {
  private String prompt;
  private String secret;
  private Boolean invalidateCache;
  private Integer maxTokens;

    public PromptRequest(String prompt, String secret, Boolean invalidateCache, Integer maxTokens) {
        this.prompt = prompt;
        this.secret = secret;
        this.invalidateCache = invalidateCache;
        this.maxTokens = maxTokens;
    }

    public PromptRequest() {
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Boolean getInvalidateCache() {
        return invalidateCache;
    }

    public void setInvalidateCache(Boolean invalidateCache) {
        this.invalidateCache = invalidateCache;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }
}
