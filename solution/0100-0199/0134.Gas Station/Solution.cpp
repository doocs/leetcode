class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int n = gas.size();
        int i = n - 1, j = n - 1;
        int cnt = 0, s = 0;
        while (cnt < n) {
            s += gas[j] - cost[j];
            ++cnt;
            j = (j + 1) % n;
            while (s < 0 && cnt < n) {
                --i;
                s += gas[i] - cost[i];
                ++cnt;
            }
        }
        return s < 0 ? -1 : i;
    }
};