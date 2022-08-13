class Solution {
public:
    int halfQuestions(vector<int>& questions) {
        vector<int> counter(1010);
        for (int q : questions) ++counter[q];
        int n = questions.size() >> 1;
        sort(counter.begin(), counter.end());
        int ans = 0;
        for (int i = counter.size() - 1; n > 0; --i) {
            ++ans;
            n -= counter[i];
        }
        return ans;
    }
};