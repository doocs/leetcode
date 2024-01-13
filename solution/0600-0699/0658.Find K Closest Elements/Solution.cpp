int target;

class Solution {
public:
    static bool cmp(int& a, int& b) {
        int v = abs(a - target) - abs(b - target);
        return v == 0 ? a < b : v < 0;
    }

    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        target = x;
        sort(arr.begin(), arr.end(), cmp);
        vector<int> ans(arr.begin(), arr.begin() + k);
        sort(ans.begin(), ans.end());
        return ans;
    }
};