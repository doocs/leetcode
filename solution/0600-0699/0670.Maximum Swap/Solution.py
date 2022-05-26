class Solution:
    def maximumSwap(self, num):
        """
        :type num: int
        :rtype: int
        """
        # s为能得到的最大数
        s = ''.join(sorted(list(str(num)), reverse=True))
        nums = str(num)
        if s == nums:
            return num
        for i in range(len(s)):
            if s[i] != nums[i]:
                kai = i
                break
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] == s[kai]:
                loc = i
                break
        return int(s[:kai + 1] + nums[kai + 1:loc] + nums[kai] + nums[loc + 1:])
