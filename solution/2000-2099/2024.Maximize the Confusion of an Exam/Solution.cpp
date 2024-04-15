class Solution {
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        auto f = [&](char c) {
            int ans = 0, cnt = 0;
            for (int i = 0, j = 0; i < answerKey.size(); ++i) {
                cnt += answerKey[i] == c;
                while (cnt > k) {
                    cnt -= answerKey[j++] == c;
                }
                ans = max(ans, i - j + 1);
            }
            return ans;
        };
        return max(f('T'), f('F'));
    }
};