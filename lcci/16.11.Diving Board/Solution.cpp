class Solution {
public:
    vector<int> divingBoard(int shorter, int longer, int k) {
        if (k == 0) return {};
        if (longer == shorter) return {longer * k};
        vector<int> ans;
        for (int i = 0; i < k + 1; ++i)
            ans.push_back(longer * i + shorter * (k - i));
        return ans;
    }
};