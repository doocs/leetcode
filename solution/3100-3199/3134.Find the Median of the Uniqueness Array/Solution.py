class Solution:
    def medianOfUniquenessArray(self, nums: List[int]) -> int:
        def check(mx: int) -> bool:
            cnt = defaultdict(int)
            k = l = 0
            for r, x in enumerate(nums):
                cnt[x] += 1
                while len(cnt) > mx:
                    y = nums[l]
                    cnt[y] -= 1
                    if cnt[y] == 0:
                        cnt.pop(y)
                    l += 1
                k += r - l + 1
                if k >= (m + 1) // 2:
                    return True
            return False

        n = len(nums)
        m = (1 + n) * n // 2
        return bisect_left(range(n), True, key=check)
