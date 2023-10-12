class Solution:
    def maximumBeauty(
        self, flowers: List[int], newFlowers: int, target: int, full: int, partial: int
    ) -> int:
        flowers.sort()
        n = len(flowers)
        s = list(accumulate(flowers, initial=0))
        ans, i = 0, n - bisect_left(flowers, target)
        for x in range(i, n + 1):
            newFlowers -= 0 if x == 0 else max(target - flowers[n - x], 0)
            if newFlowers < 0:
                break
            l, r = 0, n - x - 1
            while l < r:
                mid = (l + r + 1) >> 1
                if flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers:
                    l = mid
                else:
                    r = mid - 1
            y = 0
            if r != -1:
                cost = flowers[l] * (l + 1) - s[l + 1]
                y = min(flowers[l] + (newFlowers - cost) // (l + 1), target - 1)
            ans = max(ans, x * full + y * partial)
        return ans
