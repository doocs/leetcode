class Solution {
public:
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u'};
        vector<int> nums;
        for (int i = 0; i < words.size(); ++i) {
            char a = words[i][0], b = words[i].back();
            if (vowels.count(a) && vowels.count(b)) {
                nums.push_back(i);
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            int cnt = upper_bound(nums.begin(), nums.end(), r) - lower_bound(nums.begin(), nums.end(), l);
            ans.push_back(cnt);
        }
        return ans;
    }
};