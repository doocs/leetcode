class Solution {
public:
    vector<int> findOriginalArray(vector<int>& changed) {
        if (changed.size() % 2 != 0) return {};
        int n = 100010;
        vector<int> counter(n);
        for (int x : changed) ++counter[x];
        if (counter[0] % 2 != 0) return {};
        vector<int> res(changed.size() / 2);
        int j = counter[0] / 2;
        for (int i = 1; i < n; ++i) {
            if (counter[i] == 0) continue;
            if (i * 2 >= n || counter[i] > counter[i * 2]) return {};
            counter[i * 2] -= counter[i];
            while (counter[i]--) res[j++] = i;
        }
        return res;
    }
};