class Solution {
public:
    int maxBoxesInWarehouse(vector<int>& boxes, vector<int>& warehouse) {
        int n = warehouse.size();
        int left[n];
        left[0] = warehouse[0];
        for (int i = 1; i < n; ++i) {
            left[i] = min(left[i - 1], warehouse[i]);
        }
        sort(boxes.begin(), boxes.end());
        int i = 0, j = n - 1;
        while (i < boxes.size()) {
            while (j >= 0 && left[j] < boxes[i]) {
                --j;
            }
            if (j < 0) {
                break;
            }
            ++i;
            --j;
        }
        return i;
    }
};