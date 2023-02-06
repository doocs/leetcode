class AuthenticationManager {
    private int t;
    private Map<String, Integer> d = new HashMap<>();

    public AuthenticationManager(int timeToLive) {
        t = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        d.put(tokenId, currentTime + t);
    }

    public void renew(String tokenId, int currentTime) {
        if (d.getOrDefault(tokenId, 0) <= currentTime) {
            return;
        }
        generate(tokenId, currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {
        int ans = 0;
        for (int exp : d.values()) {
            if (exp > currentTime) {
                ++ans;
            }
        }
        return ans;
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */