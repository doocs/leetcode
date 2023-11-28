class Solution:
    def maximizeSquareHoleArea(
        self, n: int, m: int, hBars: List[int], vBars: List[int]
    ) -> int:
        def f(nums: List[int]) -> int:
            nums.sort()
            ans = cnt = 1
            for i in range(1, len(nums)):
                if nums[i] == nums[i - 1] + 1:
                    cnt += 1
                    ans = max(ans, cnt)
                else:
                    cnt = 1
            return ans + 1

        return min(f(hBars), f(vBars)) ** 2
