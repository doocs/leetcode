class AuthenticationManager {
public:
    AuthenticationManager(int timeToLive) {
        t = timeToLive;
    }

    void generate(string tokenId, int currentTime) {
        d[tokenId] = currentTime + t;
    }

    void renew(string tokenId, int currentTime) {
        if (d[tokenId] <= currentTime) return;
        generate(tokenId, currentTime);
    }

    int countUnexpiredTokens(int currentTime) {
        int ans = 0;
        for (auto& [_, v] : d) ans += v > currentTime;
        return ans;
    }

private:
    int t;
    unordered_map<string, int> d;
};

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager* obj = new AuthenticationManager(timeToLive);
 * obj->generate(tokenId,currentTime);
 * obj->renew(tokenId,currentTime);
 * int param_3 = obj->countUnexpiredTokens(currentTime);
 */