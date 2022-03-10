class Solution:
    def findLongestSubarray(self, array: List[str]) -> List[str]:
        seen = {0: -1}
        t = mx = 0
        ans = []
        for i, s in enumerate(array):
            t += 1 if s.isalpha() else -1
            if t in seen:
                if mx < i - seen[t]:
                    mx = i - seen[t]
                    ans = array[seen[t] + 1 : i + 1]
            else:
                seen[t] = i
        return ans
