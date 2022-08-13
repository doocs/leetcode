class Solution {
public:
    int longestWPI(vector<int>& hours) {
        int s = 0, ans = 0;
        unordered_map<int, int> seen;
        for (int i = 0; i < hours.size(); ++i) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0)
                ans = i + 1;
            else {
                if (!seen.count(s)) seen[s] = i;
                if (seen.count(s - 1)) ans = max(ans, i - seen[s - 1]);
            }
        }
        return ans;
    }
};