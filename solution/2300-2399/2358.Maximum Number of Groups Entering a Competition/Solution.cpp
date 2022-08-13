class Solution {
public:
    int maximumGroups(vector<int>& grades) {
        sort(grades.begin(), grades.end());
        int ans = 1;
        vector<int> prev = {1, grades[0]};
        vector<int> curr = {0, 0};
        for (int i = 1; i < grades.size(); ++i) {
            curr[0]++;
            curr[1] += grades[i];
            if (prev[0] < curr[0] && prev[1] < curr[1]) {
                prev = curr;
                curr = {0, 0};
                ++ans;
            }
        }
        return ans;
    }
};