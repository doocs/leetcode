class Solution {
public:
    int maxNumberOfApples(vector<int>& weight) {
        sort(weight.begin(), weight.end());
        int ans = 0, t = 0;
        for (int v : weight) {
            if (t + v > 5000) break;
            t += v;
            ++ans;
        }
        return ans;
    }
};