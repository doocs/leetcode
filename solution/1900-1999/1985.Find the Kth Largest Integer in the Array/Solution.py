class Solution:
    def kthLargestNumber(self, nums: List[str], k: int) -> str:
        def cmp(a, b):
            if len(a) != len(b):
                return len(b) - len(a)
            return 1 if b > a else -1

        nums.sort(key=cmp_to_key(cmp))
        return nums[k - 1]
