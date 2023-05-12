class Solution {
public:
    vector<int> rearrangeBarcodes(vector<int>& barcodes) {
        int mx = *max_element(barcodes.begin(), barcodes.end());
        int cnt[mx + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int x : barcodes) {
            ++cnt[x];
        }
        sort(barcodes.begin(), barcodes.end(), [&](int a, int b) {
            return cnt[a] > cnt[b] || (cnt[a] == cnt[b] && a < b);
        });
        int n = barcodes.size();
        vector<int> ans(n);
        for (int k = 0, j = 0; k < 2; ++k) {
            for (int i = k; i < n; i += 2) {
                ans[i] = barcodes[j++];
            }
        }
        return ans;
    }
};