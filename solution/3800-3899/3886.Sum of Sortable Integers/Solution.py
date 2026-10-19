class Solution:
    def sortableIntegers(self, nums: list[int]) -> int:
        n = len(nums)
        pref_max = [0] * (n + 1)
        for i, x in enumerate(nums):
            pref_max[i + 1] = max(pref_max[i], x)
        suff_min = [10**9] * (n + 1)
        for i in range(n - 1, -1, -1):
            suff_min[i] = min(nums[i], suff_min[i + 1])
        pref_drop = [0] * n
        for i in range(1, n):
            pref_drop[i] = pref_drop[i - 1] + (nums[i - 1] > nums[i])

        def check(k: int) -> bool:
            for start in range(0, n, k):
                end = start + k - 1
                drops = pref_drop[end] - pref_drop[start]
                if nums[end] > nums[start]:
                    drops += 1
                if drops > 1 or pref_max[start] > suff_min[start]:
                    return False
            return True

        ans = 0
        i = 1
        while i <= n // i:
            if n % i == 0:
                if check(i):
                    ans += i
                other = n // i
                if i != other and check(other):
                    ans += other
            i += 1
        return ans
