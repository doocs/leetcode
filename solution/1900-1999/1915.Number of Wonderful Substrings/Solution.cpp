class Solution {
public:
    long long wonderfulSubstrings(string word) {
        vector<int> counter(1024);
        counter[0] = 1;
        long long ans = 0;
        int state = 0;
        for (char c : word) {
            state ^= (1 << (c - 'a'));
            ans += counter[state];
            for (int i = 0; i < 10; ++i) ans += counter[state ^ (1 << i)];
            ++counter[state];
        }
        return ans;
    }
};