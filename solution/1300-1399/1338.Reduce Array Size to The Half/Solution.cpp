class Solution {
public:
    int minSetSize(vector<int>& arr) {
        int mx = *max_element(arr.begin(), arr.end());
        int cnt[mx + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int& x : arr) {
            ++cnt[x];
        }
        sort(cnt, cnt + mx + 1, greater<int>());
        int ans = 0;
        int m = 0;
        for (int& x : cnt) {
            if (x) {
                m += x;
                ++ans;
                if (m * 2 >= arr.size()) {
                    break;
                }
            }
        }
        return ans;
    }
};