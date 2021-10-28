class Solution {
public:
    vector<int> partitionLabels(string s) {
        int n = s.size();
        vector<int> last(128);
        for (int i = 0; i < n; ++i) last[s[i]] = i;
        vector<int> ans;
        for (int i = 0, left = 0, right = 0; i < n; ++i)
        {
            right = max(right, last[s[i]]);
            if (i == right)
            {
                ans.push_back(right - left + 1);
                left = right + 1;
            }
        }
        return ans;
    }
};