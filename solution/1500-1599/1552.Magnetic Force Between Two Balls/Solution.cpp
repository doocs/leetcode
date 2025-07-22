class Solution {
public:
    int maxDistance(vector<int>& position, int m) {
        ranges::sort(position);
        int l = 1, r = position.back();
        auto count = [&](int f) {
            int prev = position[0];
            int cnt = 1;
            for (int& curr : position) {
                if (curr - prev >= f) {
                    prev = curr;
                    cnt++;
                }
            }
            return cnt;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (count(mid) >= m) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
