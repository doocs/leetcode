class Solution {
public:
    int maximizeSweetness(vector<int>& sweetness, int k) {
        int l = 0, r = accumulate(sweetness.begin(), sweetness.end(), 0);
        auto check = [&](int x) {
            int s = 0, cnt = 0;
            for (int v : sweetness) {
                s += v;
                if (s >= x) {
                    s = 0;
                    ++cnt;
                }
            }
            return cnt > k;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};