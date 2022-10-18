class Solution {
public:
    double averageWaitingTime(vector<vector<int>>& customers) {
        double tot = 0;
        int t = 0;
        for (auto& e : customers) {
            int a = e[0], b = e[1];
            t = max(t, a) + b;
            tot += t - a;
        }
        return tot / customers.size();
    }
};