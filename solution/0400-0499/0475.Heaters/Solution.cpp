class Solution {
public:
    int findRadius(vector<int>& houses, vector<int>& heaters) {
        sort(houses.begin(), houses.end());
        sort(heaters.begin(), heaters.end());
        int left = 0, right = 1e9;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(houses, heaters, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    bool check(vector<int>& houses, vector<int>& heaters, int r) {
        int m = houses.size(), n = heaters.size();
        int i = 0, j = 0;
        while (i < m) {
            if (j >= n) return false;
            int mi = heaters[j] - r;
            int mx = heaters[j] + r;
            if (houses[i] < mi) return false;
            if (houses[i] > mx)
                ++j;
            else
                ++i;
        }
        return true;
    }
};