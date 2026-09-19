class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size(), m = needle.size();
        const int mod = (1 << 31) - 1;
        long long target = 0, sha = 0, multi = 1;
        for (int i = 0; i < m; ++i) {
            target = (target * 256 + needle[i]) % mod;
        }
        for (int i = 1; i < m; ++i) {
            multi = multi * 256 % mod;
        }
        int left = 0;
        for (int right = 0; right < n; ++right) {
            sha = (sha * 256 + haystack[right]) % mod;
            if (right - left + 1 < m) {
                continue;
            }
            if (sha == target && haystack.substr(left, m) == needle) {
                return left;
            }
            sha = (sha - haystack[left] * multi % mod + mod) % mod;
            ++left;
        }
        return -1;
    }
};
