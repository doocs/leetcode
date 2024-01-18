using pii = pair<int, int>;

class Solution {
public:
    vector<int> p;
    vector<int> size;

    int validSubarraySize(vector<int>& nums, int threshold) {
        int n = nums.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        size.assign(n, 1);
        vector<pii> arr(n);
        for (int i = 0; i < n; ++i) arr[i] = {nums[i], i};
        sort(arr.begin(), arr.end());
        vector<bool> vis(n);
        for (int j = n - 1; ~j; --j) {
            int v = arr[j].first, i = arr[j].second;
            if (i && vis[i - 1]) merge(i, i - 1);
            if (j < n - 1 && vis[i + 1]) merge(i, i + 1);
            if (v > threshold / size[find(i)]) return size[find(i)];
            vis[i] = true;
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return;
        p[pa] = pb;
        size[pb] += size[pa];
    }
};