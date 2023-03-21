class Solution {
public:
    int maxBoxesInWarehouse(vector<int>& boxes, vector<int>& warehouse) {
        int n = warehouse.size();
        const int inf = 1 << 30;
        vector<int> left(n, inf);
        vector<int> right(n, inf);
        for (int i = 1; i < n; ++i) {
            left[i] = min(left[i - 1], warehouse[i - 1]);
        }
        for (int i = n - 2; ~i; --i) {
            right[i] = min(right[i + 1], warehouse[i + 1]);
        }
        for (int i = 0; i < n; ++i) {
            warehouse[i] = min(warehouse[i], max(left[i], right[i]));
        }
        sort(boxes.begin(), boxes.end());
        sort(warehouse.begin(), warehouse.end());
        int ans = 0;
        int i = 0;
        for (int x : boxes) {
            while (i < n && warehouse[i] < x) {
                ++i;
            }
            if (i == n) {
                break;
            }
            ++ans;
            ++i;
        }
        return ans;
    }
};