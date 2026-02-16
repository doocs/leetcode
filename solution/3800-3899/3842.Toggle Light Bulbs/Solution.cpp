class Solution {
public:
    vector<int> toggleLightBulbs(vector<int>& bulbs) {
        vector<int> st(101, 0);
        for (int x : bulbs) {
            st[x] ^= 1;
        }
        vector<int> ans;
        for (int i = 0; i < 101; ++i) {
            if (st[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
