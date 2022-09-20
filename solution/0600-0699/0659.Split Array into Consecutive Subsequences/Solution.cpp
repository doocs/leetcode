class Solution {
public:
    bool isPossible(vector<int>& nums) {
        unordered_map<int, priority_queue<int, vector<int>, greater<int>>> d;
        for (int v : nums) {
            if (d.count(v - 1)) {
                auto& q = d[v - 1];
                d[v].push(q.top() + 1);
                q.pop();
                if (q.empty()) {
                    d.erase(v - 1);
                }
            } else {
                d[v].push(1);
            }
        }
        for (auto& [_, v] : d) {
            if (v.top() < 3) {
                return false;
            }
        }
        return true;
    }
};