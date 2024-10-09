class Solution {
public:
    int halveArray(vector<int>& nums) {
        priority_queue<double> pq;
        double s = 0;
        for (int x : nums) {
            s += x;
            pq.push((double) x);
        }
        s /= 2.0;
        int ans = 0;
        while (s > 0) {
            double t = pq.top() / 2.0;
            pq.pop();
            s -= t;
            pq.push(t);
            ++ans;
        }
        return ans;
    }
};
