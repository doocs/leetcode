class Solution:
    def lastVisitedIntegers(self, nums: List[int]) -> List[int]:
        seen = []
        ans = []
        k = 0
        
        for num in nums:
            if num == -1:
                k += 1
                if k <= len(seen):
                    ans.append(seen[-k])
                else:
                    ans.append(-1)
            else:
                seen.append(num)
                k = 0
                
        return ans
