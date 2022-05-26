class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        self.res = 0

        def merge(part1, part2, nums):
            len1, len2 = len(part1) - 1, len(part2) - 1
            t = len(nums) - 1
            while len1 >= 0 and len2 >= 0:
                if part1[len1] > part2[len2]:
                    self.res += (len2 + 1)
                    nums[t] = part1[len1]
                    len1 -= 1
                else:
                    nums[t] = part2[len2]
                    len2 -= 1
                t -= 1
            while len1 >= 0:
                nums[t] = part1[len1]
                t -= 1
                len1 -= 1
            while len2 >= 0:
                nums[t] = part2[len2]
                t -= 1
                len2 -= 1

        def merge_sort(nums):
            if len(nums) < 2:
                return
            mid = len(nums) // 2
            s1, s2 = nums[:mid], nums[mid:]
            merge_sort(s1)
            merge_sort(s2)
            merge(s1, s2, nums)

        merge_sort(nums)
        return self.res
