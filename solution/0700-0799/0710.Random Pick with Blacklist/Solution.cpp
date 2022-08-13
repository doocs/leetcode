class Solution {
public:
    unordered_map<int, int> d;
    int k;

    Solution(int n, vector<int>& blacklist) {
        k = n - blacklist.size();
        int i = k;
        unordered_set<int> black(blacklist.begin(), blacklist.end());
        for (int& b : blacklist) {
            if (b < k) {
                while (black.count(i)) ++i;
                d[b] = i++;
            }
        }
    }

    int pick() {
        int x = rand() % k;
        return d.count(x) ? d[x] : x;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(n, blacklist);
 * int param_1 = obj->pick();
 */