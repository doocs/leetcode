class Solution {
public:
    int maxArea(int h, int w, vector<int>& horizontalCuts, vector<int>& verticalCuts) {
        horizontalCuts.push_back(0);
        horizontalCuts.push_back(h);
        verticalCuts.push_back(0);
        verticalCuts.push_back(w);
        sort(horizontalCuts.begin(), horizontalCuts.end());
        sort(verticalCuts.begin(), verticalCuts.end());
        int x = 0, y = 0;
        for (int i = 1; i < horizontalCuts.size(); ++i) {
            x = max(x, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for (int i = 1; i < verticalCuts.size(); ++i) {
            y = max(y, verticalCuts[i] - verticalCuts[i - 1]);
        }
        int mod = 1e9 + 7;
        return (int) ((1ll * x * y) % mod);
    }
};