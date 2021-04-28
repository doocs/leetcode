class AuthenticationManager {
    private int timeToLive;
    private Map<String, Integer> tokens;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        tokens = new HashMap<>();
    }
    
    public void generate(String tokenId, int currentTime) {
        tokens.put(tokenId, currentTime + timeToLive);
    }
    
    public void renew(String tokenId, int currentTime) {
        Integer expireTime = tokens.get(tokenId);
        if (expireTime == null || expireTime <= currentTime) {
            return;
        }
        tokens.put(tokenId, currentTime + timeToLive);
    }
    
    public int countUnexpiredTokens(int currentTime) {
        int unexpiredCount = 0;
        for (Integer val : tokens.values()) {
            if (val > currentTime) {
                ++unexpiredCount;
            }
        }
        return unexpiredCount;
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */