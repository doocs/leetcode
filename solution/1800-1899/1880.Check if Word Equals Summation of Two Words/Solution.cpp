class Solution {
public:
    bool isSumEqual(string firstWord, string secondWord, string targetWord) {
        return transfer(firstWord) + transfer(secondWord) == transfer(targetWord);
    }
private:
    int transfer(string word) {
        int res = 0;
        for (char c : word) {
            res *= 10;
            res += (c - 'a');
        }
        return res;
    }
};