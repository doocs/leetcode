class Solution:
    def countSuffix(self, nums: list[int], target: list[int]) -> int:
        diff = defaultdict(int)
        for x in nums:
            diff[x] += 1
        for x in target:
            diff[x] -= 1
            if diff[x] < 0:
                return 0

        n = len(nums)
        max_i = n - 1
        for i, (x, y) in enumerate(zip(nums, target)):
            if x != y:
                max_i = i
                break

        min_i = 0
        for i in range(n - 1, 0, -1):
            if target[i - 1] < target[i]:
                min_i = i
                break

        return max(max_i - min_i + 1, 0)
