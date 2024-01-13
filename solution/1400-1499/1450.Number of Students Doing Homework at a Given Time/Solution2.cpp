class Solution {
public:
    int busyStudent(vector<int>& startTime, vector<int>& endTime, int queryTime) {
        vector<int> c(1010);
        for (int i = 0; i < startTime.size(); ++i) {
            c[startTime[i]]++;
            c[endTime[i] + 1]--;
        }
        int ans = 0;
        for (int i = 0; i <= queryTime; ++i) {
            ans += c[i];
        }
        return ans;
    }
};