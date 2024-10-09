class Solution {
public:
    int bagOfTokensScore(vector<int>& tokens, int power) {
        sort(tokens.begin(), tokens.end());
        int ans = 0, score = 0;
        for (int i = 0, j = tokens.size() - 1; i <= j;) {
            if (power >= tokens[i]) {
                power -= tokens[i++];
                ans = max(ans, ++score);
            } else if (score > 0) {
                power += tokens[j--];
                --score;
            } else {
                break;
            }
        }
        return ans;
    }
};