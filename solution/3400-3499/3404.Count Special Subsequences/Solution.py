class Solution:
    def numberOfSubsequences(self, nums: List[int]) -> int:
        n = len(nums)
        cnt = defaultdict(int)
        for r in range(4, n - 2):
            c = nums[r]
            for s in range(r + 2, n):
                d = nums[s]
                g = gcd(c, d)
                cnt[(d // g, c // g)] += 1
        ans = 0
        for q in range(2, n - 4):
            b = nums[q]
            for p in range(q - 1):
                a = nums[p]
                g = gcd(a, b)
                ans += cnt[(a // g, b // g)]
            c = nums[q + 2]
            for s in range(q + 4, n):
                d = nums[s]
                g = gcd(c, d)
                cnt[(d // g, c // g)] -= 1
        return ans
