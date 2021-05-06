class Leaderboard {
    private Map<Integer, Integer> playerScores;

    public Leaderboard() {
        playerScores = new HashMap<>();
    }
    
    public void addScore(int playerId, int score) {
        playerScores.put(playerId, playerScores.getOrDefault(playerId, 0) + score);
    }
    
    public int top(int K) {
        List<Integer> scores = new ArrayList<>(playerScores.values());
        Collections.sort(scores, Collections.reverseOrder());
        int res = 0;
        for (int i = 0; i < K; ++i) {
            res += scores.get(i);
        }
        return res;
    }
    
    public void reset(int playerId) {
        playerScores.put(playerId, 0);
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */