class Solution(object):
    def summaryRanges(self, nums):
        """
        :type nums: List[int]
        :rtype: List[str]
        """
        length = len(nums)
        if length == 0:
            return []
        ans = []
        count = 0
        idx = 0
        while idx + count < length - 1:
            if nums[idx + count] == nums[idx + count + 1] - 1:
                count += 1
            else:
                string = ''
                if count == 0:
                    string = str(nums[idx])
                else:
                    string = str(nums[idx]) + "->" + str(nums[idx + count])
                ans.append(string)
                idx += count + 1
                count = 0
        # 末尾处理
        string = ''
        if count > 0:
            string = str(nums[idx]) + "->" + str(nums[idx + count])
        else:
            string = str(nums[idx])
        ans.append(string)
        return ans
