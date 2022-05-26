class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        window = defaultdict(int)
        n, ans = len(s), 0
        left, right = 0, 0
        while right < n:
            ch = s[right]
            right += 1
            window[ch] += 1
            while window[ch] > 1:
                window[s[left]] -= 1
                left += 1
            ans = max(ans, right - left)
        return ans
