class Solution {
public:
    int minProcessingTime(vector<int>& processorTime, vector<int>& tasks) {
        sort(processorTime.begin(), processorTime.end());
        sort(tasks.begin(), tasks.end());
        int ans = 0, i = tasks.size() - 1;
        for (int t : processorTime) {
            ans = max(ans, t + tasks[i]);
            i -= 4;
        }
        return ans;
    }
};