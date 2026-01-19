class Solution {
public:
    string lexSmallestAfterDeletion(string s) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        string stk;
        for (char c : s) {
            while (stk.size() && stk.back() > c && cnt[stk.back() - 'a'] > 1) {
                --cnt[stk.back() - 'a'];
                stk.pop_back();
            }
            stk.push_back(c);
        }
        while (cnt[stk.back() - 'a'] > 1) {
            --cnt[stk.back() - 'a'];
            stk.pop_back();
        }
        return stk;
    }
};
