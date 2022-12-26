class Solution {
public:
    int minSwaps(vector<int>& data) {
        int k = 0;
        for (int& v : data) {
            k += v;
        }
        int t = 0;
        for (int i = 0; i < k; ++i) {
            t += data[i];
        }
        int mx = t;
        for (int i = k; i < data.size(); ++i) {
            t += data[i];
            t -= data[i - k];
            mx = max(mx, t);
        }
        return k - mx;
    }
};