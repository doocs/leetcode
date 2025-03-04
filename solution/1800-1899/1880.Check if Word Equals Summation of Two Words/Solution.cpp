class Solution {
public:
    bool isSumEqual(string firstWord, string secondWord, string targetWord) {
        auto f = [](string& s) -> int {
            int ans = 0;
            for (char c : s) {
                ans = ans * 10 + (c - 'a');
            }
            return ans;
        };
        return f(firstWord) + f(secondWord) == f(targetWord);
    }
};
