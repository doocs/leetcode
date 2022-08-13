class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        vector<int> counter(26);
        for (char c : p) ++counter[c - 'a'];
        vector<int> ans;
        int left = 0, right = 0;
        vector<int> t(26);
        while (right < s.size()) {
            int i = s[right] - 'a';
            ++t[i];
            while (t[i] > counter[i]) {
                --t[s[left] - 'a'];
                ++left;
            }
            if (right - left + 1 == p.size()) ans.push_back(left);
            ++right;
        }
        return ans;
    }
};