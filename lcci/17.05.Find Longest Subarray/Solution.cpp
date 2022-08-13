class Solution {
public:
    vector<string> findLongestSubarray(vector<string>& array) {
        unordered_map<int, int> seen;
        seen[0] = -1;
        int t = 0, mx = 0, j = 0;
        for (int i = 0; i < array.size(); ++i) {
            t += isdigit(array[i][0]) ? 1 : -1;
            if (seen.count(t)) {
                if (mx < i - seen[t]) {
                    mx = i - seen[t];
                    j = seen[t] + 1;
                }
            } else {
                seen[t] = i;
            }
        }
        return {array.begin() + j, array.begin() + j + mx};
    }
};