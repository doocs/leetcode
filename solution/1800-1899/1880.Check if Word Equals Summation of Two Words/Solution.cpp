class Solution {
public:
    bool isSumEqual(string firstWord, string secondWord, string targetWord) {
        return convert(firstWord) + convert(secondWord) == convert(targetWord);
    }
private:
    int convert(string word) {
        int res = 0;
        for (char c : word) {
            res *= 10;
            res += (c - 'a');
        }
        return res;
    }
};