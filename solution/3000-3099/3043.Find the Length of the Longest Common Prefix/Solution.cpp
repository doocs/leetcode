class Solution {
public:
    int longestCommonPrefix(vector<int>& arr1, vector<int>& arr2) {
        unordered_set<int> s;
        for (int x : arr1) {
            for (; x; x /= 10) {
                s.insert(x);
            }
        }
        int mx = 0;
        for (int x : arr2) {
            for (; x; x /= 10) {
                if (s.count(x)) {
                    mx = max(mx, x);
                    break;
                }
            }
        }
        return mx > 0 ? (int) log10(mx) + 1 : 0;
    }
};
