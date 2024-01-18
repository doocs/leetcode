class Solution {
public:
    int minimumOperations(vector<int>& nums, int start, int goal) {
        queue<int> q{{start}};
        vector<bool> vis(1001);
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                int x = q.front();
                q.pop();
                for (int y : next(nums, x)) {
                    if (y == goal) return ans;
                    if (y >= 0 && y <= 1000 && !vis[y]) {
                        vis[y] = true;
                        q.push(y);
                    }
                }
            }
        }
        return -1;
    }

    vector<int> next(vector<int>& nums, int x) {
        vector<int> res;
        for (int num : nums) {
            res.push_back(x + num);
            res.push_back(x - num);
            res.push_back(x ^ num);
        }
        return res;
    }
};