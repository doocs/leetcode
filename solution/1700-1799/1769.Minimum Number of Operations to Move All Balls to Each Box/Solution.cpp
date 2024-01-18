class Solution {
public:
    vector<int> minOperations(string boxes) {
        int n = boxes.size();
        int left[n];
        int right[n];
        memset(left, 0, sizeof left);
        memset(right, 0, sizeof right);
        for (int i = 1, cnt = 0; i < n; ++i) {
            cnt += boxes[i - 1] == '1';
            left[i] = left[i - 1] + cnt;
        }
        for (int i = n - 2, cnt = 0; ~i; --i) {
            cnt += boxes[i + 1] == '1';
            right[i] = right[i + 1] + cnt;
        }
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) ans[i] = left[i] + right[i];
        return ans;
    }
};