class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> ans;
        int v = 1;
        for (int i = 0; i < n; ++i) {
            ans.push_back(v);
            if (v * 10 <= n)
                v *= 10;
            else {
                while (v % 10 == 9 || v + 1 > n) v /= 10;
                ++v;
            }
        }
        return ans;
    }
};