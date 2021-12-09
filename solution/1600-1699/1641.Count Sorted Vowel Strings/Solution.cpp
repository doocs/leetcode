class Solution {
public:
    int countVowelStrings(int n) {
        vector<int> cnt(5, 1);
        for (int i = 2; i <= n; ++i)
            for (int j = 3; j >= 0; --j)
                cnt[j] += cnt[j + 1];
        return accumulate(cnt.begin(), cnt.end(), 0);
    }
};