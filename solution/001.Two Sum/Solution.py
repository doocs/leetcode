class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        tmp={k:v for k,v in enumerate(nums)}
        for k,v in tmp.items():
            tmp0=tmp.copy()
            tmp0.pop(k) 
            if target-v in tmp0.values():
                return [k,list(tmp0.keys())[list(tmp0.values()).index(target-v)]]
        return []