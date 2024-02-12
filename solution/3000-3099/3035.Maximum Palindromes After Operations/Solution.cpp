class Solution {
public:
    int maxPalindromesAfterOperations(vector<string>& words) {
        int s = 0, mask = 0;
        for (const auto& w : words) {
            s += w.length();
            for (char c : w) {
                mask ^= 1 << (c - 'a');
            }
        }
        s -= __builtin_popcount(mask);
        sort(words.begin(), words.end(), [](const string& a, const string& b) { return a.length() < b.length(); });
        int ans = 0;
        for (const auto& w : words) {
            s -= w.length() / 2 * 2;
            if (s < 0) {
                break;
            }
            ++ans;
        }
        return ans;
    }
};