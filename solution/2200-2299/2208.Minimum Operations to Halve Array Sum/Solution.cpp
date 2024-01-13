class Solution {
public:
    int halveArray(vector<int>& nums) {
        priority_queue<double> q;
        double s = 0;
        for (int& v : nums) {
            s += v;
            q.push(v);
        }
        s /= 2.0;
        int ans = 0;
        while (s > 0) {
            double t = q.top() / 2;
            q.pop();
            s -= t;
            q.push(t);
            ++ans;
        }
        return ans;
    }
};