class Solution {
public:
    long long taskSchedulerII(vector<int>& tasks, int space) {
        unordered_map<int, long long> mp;
        long long ans = 0;
        for (int v : tasks) {
            ++ans;
            if (mp.count(v)) ans = max(ans, mp[v]);
            mp[v] = ans + space + 1;
        }
        return ans;
    }
};