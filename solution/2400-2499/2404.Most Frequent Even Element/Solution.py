class Solution:
    def mostFrequentEven(self, nums: List[int]) -> int:
        cnt = Counter(v for v in nums if v % 2 == 0)
        ans, mx = -1, 0
        for v, t in cnt.items():
            if mx < t or (mx == t and ans > v):
                mx = t
                ans = v
        return ans
