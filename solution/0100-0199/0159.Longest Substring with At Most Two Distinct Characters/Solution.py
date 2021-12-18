class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        mp = Counter()
        i = j = ans = 0
        for c in s:
            mp[c] += 1
            while len(mp) > 2:
                mp[s[i]] -= 1
                if mp[s[i]] == 0:
                    mp.pop(s[i])
                i += 1
            ans = max(ans, j - i + 1)
            j += 1
        return ans
