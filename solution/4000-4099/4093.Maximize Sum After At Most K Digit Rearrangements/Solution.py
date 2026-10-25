class Solution:
    def maximizeSum(self, nums: list[int], k: int) -> int:
        def f(x: int) -> int:
            cnt = [0] * 10
            while x:
                x, v = divmod(x, 10)
                cnt[v] += 1
            y = 0
            for i in range(9, -1, -1):
                for _ in range(cnt[i]):
                    y = y * 10 + i
            return y

        n = len(nums)
        t = [f(x) for x in nums]
        ids = list(range(n))
        ids.sort(key=lambda i: t[i] - nums[i], reverse=True)
        return sum(t[ids[i]] if i < k else nums[ids[i]] for i in range(n))
