class Solution {
public:
    int boxDelivering(vector<vector<int>>& boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.size();
        long ws[n + 1];
        int f[n + 1];
        int cs[n];
        ws[0] = cs[0] = f[0] = 0;
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) cs[i + 1] = cs[i] + (p != boxes[i + 1][0]);
        }
        deque<int> q{{0}};
        for (int i = 1; i <= n; ++i) {
            while (!q.empty() && (i - q.front() > maxBoxes || ws[i] - ws[q.front()] > maxWeight)) q.pop_front();
            if (!q.empty()) f[i] = cs[i - 1] + f[q.front()] - cs[q.front()] + 2;
            if (i < n) {
                while (!q.empty() && f[q.back()] - cs[q.back()] >= f[i] - cs[i]) q.pop_back();
                q.push_back(i);
            }
        }
        return f[n];
    }
};