class Solution {
public:
    int minStoneSum(vector<int>& piles, int k) {
        priority_queue<int> q;
        for (int& p : piles) q.push(p);
        while (k--) {
            int p = q.top();
            q.pop();
            q.push((p + 1) >> 1);
        }
        int ans = 0;
        while (!q.empty()) {
            ans += q.top();
            q.pop();
        }
        return ans;
    }
};