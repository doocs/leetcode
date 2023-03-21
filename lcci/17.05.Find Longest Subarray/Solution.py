class Solution:
    def findLongestSubarray(self, array: List[str]) -> List[str]:
        vis = {0: -1}
        s = mx = k = 0
        for i, x in enumerate(array):
            s += 1 if x.isalpha() else -1
            if s in vis:
                if mx < i - (j := vis[s]):
                    mx = i - j
                    k = j + 1
            else:
                vis[s] = i
        return array[k : k + mx]
