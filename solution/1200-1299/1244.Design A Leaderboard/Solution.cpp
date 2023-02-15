class Leaderboard {
public:
    Leaderboard() {
    }

    void addScore(int playerId, int score) {
        d[playerId] += score;
        int newScore = d[playerId];
        if (newScore != score) {
            rank.erase(rank.find(newScore - score));
        }
        rank.insert(newScore);
    }

    int top(int K) {
        int ans = 0;
        for (auto& x : rank) {
            ans += x;
            if (--K == 0) {
                break;
            }
        }
        return ans;
    }

    void reset(int playerId) {
        int score = d[playerId];
        d.erase(playerId);
        rank.erase(rank.find(score));
    }

private:
    unordered_map<int, int> d;
    multiset<int, greater<int>> rank;
};

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard* obj = new Leaderboard();
 * obj->addScore(playerId,score);
 * int param_2 = obj->top(K);
 * obj->reset(playerId);
 */