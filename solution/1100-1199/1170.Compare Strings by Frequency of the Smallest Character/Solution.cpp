class Solution {
public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        auto f = [](string& s) {
            int cnt[26] = {0};
            for (char& c : s) {
                cnt[c - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                if (cnt[i]) {
                    return cnt[i];
                }
            }
            return 0;
        };
        vector<int> arr;
        for (auto& s : words) {
            arr.emplace_back(f(s));
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (auto& q : queries) {
            int x = f(q);
            ans.emplace_back(arr.end() - upper_bound(arr.begin(), arr.end(), x));
        }
        return ans;
    }
};