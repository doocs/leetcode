class Solution {
public:
    vector<int> countSteppingNumbers(int low, int high) {
        vector<int> ans;
        if (low == 0) ans.push_back(0);
        queue<long long> q;
        for (int i = 1; i < 10; ++i) q.push(i);
        while (!q.empty()) {
            int v = q.front();
            q.pop();
            if (v > high) break;
            if (v >= low) ans.push_back(v);
            int x = v % 10;
            if (x) q.push(1ll * v * 10 + x - 1);
            if (x < 9) q.push(1ll * v * 10 + x + 1);
        }
        return ans;
    }
};