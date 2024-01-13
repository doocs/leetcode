class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        int cnt = 0;
        for (int& v : arr)
            if (v == 0) ++cnt;
        if (cnt > 1) return true;
        sort(arr.begin(), arr.end());
        int n = arr.size();
        for (int& v : arr) {
            if (v == 0) continue;
            int idx = lower_bound(arr.begin(), arr.end(), v * 2) - arr.begin();
            if (idx != n && arr[idx] == v * 2) return true;
        }
        return false;
    }
};