class Solution {
public:
    vector<int> pathInZigZagTree(int label) {
        int x = 1, i = 1;
        while ((x << 1) <= label) {
            x <<= 1;
            ++i;
        }
        vector<int> ans;
        for (; i > 0; --i) {
            ans.push_back(label);
            label = ((1 << (i - 1)) + (1 << i) - 1 - label) >> 1;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};