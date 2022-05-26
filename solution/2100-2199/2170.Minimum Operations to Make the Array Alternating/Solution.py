class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        def get(i):
            c = Counter(nums[i::2]).most_common(2)
            if not c:
                return [(0, 0), (0, 0)]
            if len(c) == 1:
                return [c[0], (0, 0)]
            return c

        n = len(nums)
        return min(n - (n1 + n2) for a, n1 in get(0) for b, n2 in get(1) if a != b)
