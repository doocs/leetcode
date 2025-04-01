class Solution {
public:
    long long minEliminationTime(vector<int>& timeReq, int splitTime) {
        using ll = long long;
        priority_queue<ll, vector<ll>, greater<ll>> pq;
        for (int v : timeReq) {
            pq.push(v);
        }
        while (pq.size() > 1) {
            pq.pop();
            ll x = pq.top();
            pq.pop();
            pq.push(x + splitTime);
        }
        return pq.top();
    }
};