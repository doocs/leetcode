class Solution {
public:
    bool canConstruct(string s, int k) {
        if (s.size() < k) return 0;
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        int cnt = 0;
        for (int v : counter)
            if (v % 2)
                ++cnt;
        return cnt <= k;
    }
};