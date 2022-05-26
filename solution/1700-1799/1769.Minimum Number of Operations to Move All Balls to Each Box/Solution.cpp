class Solution {
public:
    vector<int> minOperations(string boxes) {
        int n = boxes.size();
        vector<int> res(n);
        int total = 0;
        for (int i = 0; i < n; ++i) {
            if (boxes[i] == '1') {
                res[0] += i;
                ++total;
            }
        }
        int left = 0, right = total;
        for (int i = 1; i < n; ++i) {
            if (boxes[i - 1] == '1') {
                ++left;
                --right;
            }
            res[i] = res[i - 1] + left - right;
        }
        return res;
    }
};