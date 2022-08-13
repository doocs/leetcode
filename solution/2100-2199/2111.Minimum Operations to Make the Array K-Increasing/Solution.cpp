class Solution {
public:
    int kIncreasing(vector<int>& arr, int k) {
        int ans = 0, n = arr.size();
        for (int i = 0; i < k; ++i) {
            vector<int> t;
            for (int j = i; j < n; j += k) t.push_back(arr[j]);
            ans += lis(t);
        }
        return ans;
    }

    int lis(vector<int>& arr) {
        vector<int> t;
        for (int x : arr) {
            auto it = upper_bound(t.begin(), t.end(), x);
            if (it == t.end())
                t.push_back(x);
            else
                *it = x;
        }
        return arr.size() - t.size();
    }
};