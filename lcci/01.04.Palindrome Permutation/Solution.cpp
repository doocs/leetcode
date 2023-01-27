class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_map<char, int> cnt;
        for (auto& c : s) {
            ++cnt[c];
        }
        int sum = 0;
        for (auto& [_, v] : cnt) {
            sum += v & 1;
        }
        return sum < 2;
    }
};