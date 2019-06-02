'''
找进位的规律。
'''

class Solution:
    def addNegabinary(self, arr1: List[int], arr2: List[int]) -> List[int]:
        arr1.reverse()
        arr2.reverse()
        ans=[]
        len1=len(arr1)
        len2=len(arr2)
        loc=0
        jinwei=0
        while loc<max(len1,len2):
            shu1=shu2=0
            if loc<len1:
                shu1=arr1[loc]
            if loc<len2:
                shu2=arr2[loc]
            if shu1+shu2+jinwei>=2:
                ans.append((shu1+shu2+jinwei)%2)
                jinwei=-1
                loc+=1
                continue
            elif shu1+shu2+jinwei>=0:
                ans.append(shu1+shu2+jinwei)
                jinwei=0
                loc+=1
            else:
                ans.append(1)
                jinwei=1
                loc+=1
        if jinwei==1:
            ans.append(jinwei)
        elif jinwei==-1:
            ans+=[1,1]
        ans.reverse()
        while len(ans)>1 and ans[0]==0:
            ans.pop(0)
        return ans
