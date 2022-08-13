class Solution {
public:
    int halveArray(vector<int>& nums) {
        priority_queue<double> q;
        long long s = 0;
        for (int& v : nums) {
            s += v;
            q.push(v);
        }
        double d = s / 2.0;
        int ans = 0;
        while (d > 0) {
            double t = q.top() / 2;
            q.pop();
            d -= t;
            q.push(t);
            ++ans;
        }
        return ans;
    }
};