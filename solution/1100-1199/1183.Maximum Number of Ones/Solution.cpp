class Solution {
public:
    int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int x = sideLength;
        vector<int> cnt(x * x);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int k = (i % x) * x + (j % x);
                ++cnt[k];
            }
        }
        sort(cnt.rbegin(), cnt.rend());
        int ans = 0;
        for (int i = 0; i < maxOnes; ++i) {
            ans += cnt[i];
        }
        return ans;
    }
};