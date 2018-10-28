#time 32ms with python2
class Solution(object):
    def twoSum(self, nums, target):
        buff = {}
        for i in range(len(nums)):
            if nums[i] in buff:     #如果这个数在字典里，证明之前有数可以和他匹配
                return buff[nums[i]],i   #返回之前留信息的数的编号，和当前这个数的编号
            else: 
                buff[target - nums[i]] = i   #这个数字不在字典里，那么就存一下他需要匹配的数，并记录编号

