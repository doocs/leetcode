class Solution:
    def maximumNumber(self, num: str, change: List[int]) -> str:
        find = False
        nums = list(num)
        for i, c in enumerate(num):
            if int(c) < change[int(c)]:
                nums[i] = str(change[int(c)])
                find = True
            elif find and int(c) == change[int(c)]:
                continue
            elif find:
                break
        return ''.join(nums)
