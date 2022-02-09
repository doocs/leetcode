class Solution:
    def countKDifference(self, nums: List[int], k: int) -> int:
        ans = 0
        counter = Counter()
        for num in nums:
            ans += counter[num - k] + counter[num + k]
            counter[num] += 1
        return ans
