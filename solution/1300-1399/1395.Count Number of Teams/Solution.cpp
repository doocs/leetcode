class Solution {
public:
    int numTeams(vector<int>& rating) {
        int n = rating.size(), ans = 0;
        for (int j = 1; j < n - 1; ++j) {
            int ia = 0, ib = 0, ka = 0, kb = 0;
            for (int i = 0; i < j; ++i) {
                if (rating[i] < rating[j])
                    ++ia;
                else if (rating[i] > rating[j])
                    ++ib;
            }
            for (int k = j + 1; k < n; ++k) {
                if (rating[j] < rating[k])
                    ++ka;
                else if (rating[j] > rating[k])
                    ++kb;
            }
            ans += ia * ka + ib * kb;
        }
        return ans;
    }
};