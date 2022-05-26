class Solution {
public:
    double trimMean(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int n = arr.size();
        double s = 0;
        for (int start = (int) (n * 0.05), i = start; i < n - start; ++i)
            s += arr[i];
        return s / (n * 0.9);
    }
};