class Solution {
public:
    bool isSumEqual(string firstWord, string secondWord, string targetWord) {
        return f(firstWord) + f(secondWord) == f(targetWord);
    }

    int f(string s) {
        int res = 0;
        for (char c : s) res = res * 10 + (c - 'a');
        return res;
    }
};