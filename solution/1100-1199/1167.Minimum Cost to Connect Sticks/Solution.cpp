class Solution {
public:
    int connectSticks(vector<int>& sticks) {
        priority_queue<int, vector<int>, greater<int>> pq;
        for (int x : sticks) pq.push(x);
        int res = 0;
        while (pq.size() > 1) {
            int val = pq.top();
            pq.pop();
            val += pq.top();
            pq.pop();
            res += val;
            pq.push(val);
        }
        return res;
    }
};