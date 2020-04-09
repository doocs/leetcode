class Solution:
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        dic = {}
        for ele in nums:
            dic[ele] = dic.get(ele,0) + 1
        neg = sorted(filter(lambda x:x<0,dic))
        pos = sorted(filter(lambda x:x>=0,dic))
        if(0 in dic and dic[0]>2):
            res = [[0,0,0]]
        else:
            res = []
        for ele1 in neg:
            for ele2 in pos:
                tar = -(ele1 + ele2)
                if(tar in dic):
                    if(tar in (ele1,ele2) and dic[tar]>1):
                        res.append([ele1,tar,ele2])
                    elif(tar < ele1 or tar > ele2):
                        res.append([ele1,tar,ele2])
        return res