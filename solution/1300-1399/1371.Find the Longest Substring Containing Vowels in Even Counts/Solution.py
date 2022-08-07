class Solution:
    def findTheLongestSubstring(self, s: str) -> int:
        pos = [inf] * 32
        pos[0] = -1
        vowels = 'aeiou'
        state = ans = 0
        for i, c in enumerate(s):
            for j, v in enumerate(vowels):
                if c == v:
                    state ^= 1 << j
            ans = max(ans, i - pos[state])
            pos[state] = min(pos[state], i)
        return ans
