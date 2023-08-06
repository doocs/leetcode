class Solution {
public:
    int numberOfEmployeesWhoMetTarget(vector<int>& hours, int target) {
        int ans = 0;
        for (int x : hours) {
            ans += x >= target;
        }
        return ans;
    }
};