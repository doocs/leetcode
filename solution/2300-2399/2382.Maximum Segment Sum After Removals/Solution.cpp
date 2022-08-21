using ll = long long;

class Solution {
public:
    vector<int> p;
    vector<ll> s;

    vector<long long> maximumSegmentSum(vector<int>& nums, vector<int>& removeQueries) {
        int n = nums.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        s.assign(n, 0);
        vector<ll> ans(n);
        ll mx = 0;
        for (int j = n - 1; j; --j) {
            int i = removeQueries[j];
            s[i] = nums[i];
            if (i && s[find(i - 1)]) merge(i, i - 1);
            if (i < n - 1 && s[find(i + 1)]) merge(i, i + 1);
            mx = max(mx, s[find(i)]);
            ans[j - 1] = mx;
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        p[pa] = pb;
        s[pb] += s[pa];
    }
};