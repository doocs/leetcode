class Solution {
public:
    vector<int> cookies;
    vector<int> cnt;
    int k;
    int ans;

    int distributeCookies(vector<int>& cookies, int k) {
        ans = 0x3f3f3f3f;
        this->cookies = cookies;
        this->cnt = vector<int>(k);
        this->k = k;
        dfs(0);
        return ans;
    }

    void dfs(int u) {
        if (u == cookies.size()) {
            ans = min(ans, *max_element(cnt.begin(), cnt.end()));
            return;
        }
        for (int i = 0; i < k; ++i) {
            if (cnt[i] + cookies[u] < ans) {
                cnt[i] += cookies[u];
                dfs(u + 1);
                cnt[i] -= cookies[u];
            }
        }
    }
};