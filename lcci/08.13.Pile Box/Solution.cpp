class Solution {
public:
    int pileBox(vector<vector<int>>& box) {
        sort(box.begin(), box.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] < b[0] || (a[0] == b[0] && b[1] < a[1]);
        });
        int n = box.size();
        int f[n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                    f[i] = max(f[i], f[j]);
                }
            }
            f[i] += box[i][2];
        }
        return *max_element(f, f + n);
    }
};