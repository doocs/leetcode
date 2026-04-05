class Solution {
public:
    int mirrorFrequency(string s) {
        unordered_map<char, int> freq;
        for (char c : s) {
            freq[c]++;
        }

        int ans = 0;
        unordered_set<char> vis;

        for (auto& [c, v] : freq) {
            char m;
            if (isalpha(c)) {
                m = 'a' + 25 - (c - 'a');
            } else {
                m = '0' + (9 - (c - '0'));
            }

            if (vis.count(m)) {
                continue;
            }
            vis.insert(c);

            int mv = freq.count(m) ? freq[m] : 0;
            ans += abs(v - mv);
        }

        return ans;
    }
};
