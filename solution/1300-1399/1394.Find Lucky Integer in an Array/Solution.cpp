class Solution {
public:
    int findLucky(vector<int>& arr) {
        int n = 510;
        vector<int> counter(n);
        for (int e : arr) ++counter[e];
        int ans = -1;
        for (int i = 1; i < n; ++i) {
            if (i == counter[i] && ans < i) ans = i;
        }
        return ans;
    }
};