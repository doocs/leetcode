class Solution {
public:
    int calculateTime(string keyboard, string word) {
        unordered_map<char, int> index;
        for (int i = 0; i < keyboard.size(); ++i) {
            index[keyboard[i]] = i;
        }
        int res = 0, t = 0;
        for (char c : word) {
            res += abs(index[c] - t);
            t = index[c];
        }
        return res;
    }
};