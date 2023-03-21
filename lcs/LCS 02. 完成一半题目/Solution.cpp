class Solution {
public:
    int halfQuestions(vector<int>& questions) {
        int cnt[1001]{};
        for (int& x : questions) {
            ++cnt[x];
        }
        sort(cnt, cnt + 1001);
        int ans = 0, n = questions.size() / 2;
        for (int i = 1000; n > 0; --i) {
            ++ans;
            n -= cnt[i];
        }
        return ans;
    }
};