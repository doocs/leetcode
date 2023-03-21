class Solution {
public:
    int numOfSubarrays(vector<int>& arr, int k, int threshold) {
        int s = accumulate(arr.begin(), arr.begin() + k, 0);
        int ans = s >= k * threshold;
        for (int i = k; i < arr.size(); ++i) {
            s += arr[i] - arr[i - k];
            ans += s >= k * threshold;
        }
        return ans;
    }
};