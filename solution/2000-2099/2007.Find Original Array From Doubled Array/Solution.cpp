class Solution {
public:
    vector<int> findOriginalArray(vector<int>& changed) {
        int n = changed.size();
        if (n & 1) {
            return {};
        }
        sort(changed.begin(), changed.end());
        vector<int> cnt(changed.back() + 1);
        for (int& x : changed) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int& x : changed) {
            if (cnt[x] == 0) {
                continue;
            }
            if (x * 2 >= cnt.size() || cnt[x * 2] <= 0) {
                return {};
            }
            ans.push_back(x);
            --cnt[x];
            --cnt[x * 2];
        }
        return ans.size() == n / 2 ? ans : vector<int>();
    }
};