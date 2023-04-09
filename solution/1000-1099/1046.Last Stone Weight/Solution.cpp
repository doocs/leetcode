class Solution {
public:
    int lastStoneWeight(vector<int>& stones) {
        priority_queue<int> pq;
        for (int x : stones) {
            pq.push(x);
        }
        while (pq.size() > 1) {
            int y = pq.top();
            pq.pop();
            int x = pq.top();
            pq.pop();
            if (x != y) {
                pq.push(y - x);
            }
        }
        return pq.empty() ? 0 : pq.top();
    }
};