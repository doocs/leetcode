class Solution {
public:
    long long interchangeableRectangles(vector<vector<int>>& rectangles) {
        long long ans = 0;
        unordered_map<long long, int> cnt;
        for (auto& e : rectangles) {
            int w = e[0], h = e[1];
            int g = gcd(w, h);
            w /= g;
            h /= g;
            long long x = 1LL * w * 100001 + h;
            ans += cnt[x];
            cnt[x]++;
        }
        return ans;
    }
};