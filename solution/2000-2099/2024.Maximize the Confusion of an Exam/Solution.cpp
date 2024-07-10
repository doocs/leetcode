class Solution {
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        int n = answerKey.size();
        auto f = [&](char c) {
            int l = 0, cnt = 0;
            for (char& ch : answerKey) {
                cnt += ch == c;
                if (cnt > k) {
                    cnt -= answerKey[l++] == c;
                }
            }
            return n - l;
        };
        return max(f('T'), f('F'));
    }
};