class Solution {
public:
    int sortArray(vector<int>& nums, vector<int>& pre) {
        int n = nums.size();

        int target = 0;
        for (int i = 0; i < n; i++) {
            target = target * 8 + i;
        }

        int start = 0;
        for (int x : nums) {
            start = start * 8 + x;
        }

        if (start == target) {
            return 0;
        }

        unordered_set<int> vis;
        vis.insert(start);

        queue<pair<vector<int>, int>> q;
        q.emplace(nums, 0);

        while (!q.empty()) {
            auto [state, dist] = q.front();
            q.pop();

            int nd = dist + 1;

            for (int x : pre) {
                vector<int> nxt = state;
                reverse(nxt.begin(), nxt.begin() + x);

                int key = 0;
                for (int v : nxt) {
                    key = key * 8 + v;
                }

                if (key == target) {
                    return nd;
                }

                if (vis.insert(key).second) {
                    q.emplace(move(nxt), nd);
                }
            }
        }

        return -1;
    }
};