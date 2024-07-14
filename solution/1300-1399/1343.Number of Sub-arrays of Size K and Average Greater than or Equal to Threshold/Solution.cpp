class Solution {
public:
    int numOfSubarrays(vector<int>& arr, int k, int threshold) {
        threshold *= k;
        int s = accumulate(arr.begin(), arr.begin() + k, 0);
        int ans = s >= threshold;
        for (int i = k; i < arr.size(); ++i) {
            s += arr[i] - arr[i - k];
            ans += s >= threshold;
        }
        return ans;
    }
};