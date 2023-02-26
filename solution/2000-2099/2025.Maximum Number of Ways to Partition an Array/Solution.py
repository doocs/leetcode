class Solution:
    def waysToPartition(self, nums: List[int], k: int) -> int:
        n = len(nums)
        s = [nums[0]] * n
        right = defaultdict(int)
        for i in range(1, n):
            s[i] = s[i - 1] + nums[i]
            right[s[i - 1]] += 1

        ans = 0
        if s[-1] % 2 == 0:
            ans = right[s[-1] // 2]

        left = defaultdict(int)
        for v, x in zip(s, nums):
            d = k - x
            if (s[-1] + d) % 2 == 0:
                t = left[(s[-1] + d) // 2] + right[(s[-1] - d) // 2]
                if ans < t:
                    ans = t
            left[v] += 1
            right[v] -= 1
        return ans
