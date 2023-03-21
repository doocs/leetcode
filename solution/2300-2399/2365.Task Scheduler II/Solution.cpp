class Solution {
public:
    long long taskSchedulerII(vector<int>& tasks, int space) {
        unordered_map<int, long long> day;
        long long ans = 0;
        for (int& task : tasks) {
            ++ans;
            ans = max(ans, day[task]);
            day[task] = ans + space + 1;
        }
        return ans;
    }
};