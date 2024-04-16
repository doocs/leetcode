class Solution {
public:
    vector<int> findOriginalArray(vector<int>& changed) {
        sort(changed.begin(), changed.end());
        vector<int> cnt(changed.back() + 1);
        for (int x : changed) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int x : changed) {
            if (cnt[x] == 0) {
                continue;
            }
            --cnt[x];
            int y = x << 1;
            if (y >= cnt.size() || cnt[y] <= 0) {
                return {};
            }
            --cnt[y];
            ans.push_back(x);
        }
        return ans;
    }
};