class Solution:
    def largestSumAfterKNegations(self, nums: List[int], k: int) -> int:
        counter = Counter(nums)
        ans = sum(nums)
        for i in range(-100, 0):
            if counter[i]:
                ops = min(counter[i], k)
                ans -= i * ops * 2
                counter[i] -= ops
                counter[-i] += ops
                k -= ops
                if k == 0:
                    break
        if k > 0 and k % 2 == 1 and not counter[0]:
            for i in range(1, 101):
                if counter[i]:
                    ans -= 2 * i
                    break
        return ans
