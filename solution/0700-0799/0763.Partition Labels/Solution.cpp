class Solution {
public:
    vector<int> partitionLabels(string s) {
        vector<int> last(26);
        int n = s.size();
        for (int i = 0; i < n; ++i) last[s[i] - 'a'] = i;
        vector<int> ans;
        for (int i = 0, left = 0, right = 0; i < n; ++i) {
            right = max(right, last[s[i] - 'a']);
            if (i == right) {
                ans.push_back(right - left + 1);
                left = right + 1;
            }
        }
        return ans;
    }
};