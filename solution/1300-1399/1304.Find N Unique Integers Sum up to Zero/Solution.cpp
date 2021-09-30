class Solution {
public:
    vector<int> sumZero(int n) {
        int presum = 0;
        vector<int> res;
        for (int i = 1; i < n; ++i) {
            res.push_back(i);
            presum += i;
        }
        res.push_back(-presum);
        return res;
    }
};