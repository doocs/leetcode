class Solution {
public:
    vector<int> resultsArray(vector<vector<int>>& queries, int k) {
        vector<int> ans;
        priority_queue<int> pq;
        for (const auto& q : queries) {
            int x = abs(q[0]) + abs(q[1]);
            pq.push(x);
            if (pq.size() > k) {
                pq.pop();
            }
            ans.push_back(pq.size() == k ? pq.top() : -1);
        }
        return ans;
    }
};
