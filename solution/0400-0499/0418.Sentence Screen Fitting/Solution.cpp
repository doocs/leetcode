class Solution {
public:
    int wordsTyping(vector<string>& sentence, int rows, int cols) {
        string s;
        for (auto& t : sentence) {
            s += t;
            s += " ";
        }
        int m = s.size();
        int cur = 0;
        while (rows--) {
            cur += cols;
            if (s[cur % m] == ' ') {
                ++cur;
            } else {
                while (cur && s[(cur - 1) % m] != ' ') {
                    --cur;
                }
            }
        }
        return cur / m;
    }
};