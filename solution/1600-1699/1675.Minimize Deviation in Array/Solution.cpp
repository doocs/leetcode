class Solution {
public:
    int minimumDeviation(vector<int>& nums) {
        int mi = INT_MAX;
        priority_queue<int> pq;
        for (int v : nums) {
            if (v & 1) v <<= 1;
            pq.push(v);
            mi = min(mi, v);
        }
        int ans = pq.top() - mi;
        while (pq.top() % 2 == 0) {
            int x = pq.top() >> 1;
            pq.pop();
            pq.push(x);
            mi = min(mi, x);
            ans = min(ans, pq.top() - mi);
        }
        return ans;
    }
};