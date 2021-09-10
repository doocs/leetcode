class Solution {
public:
    int halfQuestions(vector<int>& questions) {
        vector<int> counter(1010);
        for (int e : questions) ++counter[e];
        int n = questions.size() >> 1;
        sort(counter.begin(), counter.end());
        int res = 0;
        for (int i = counter.size() - 1; i >= 0; --i)
        {
            ++res;
            if (counter[i] >= n) return res;
            n -= counter[i];
        }
        return res;
    }
};