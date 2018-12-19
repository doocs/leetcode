class Solution:
    def searchInsert(self, Arr, find):
        """
        :type Arr: list[int]
        :type find: int
        :rtype: int
        """
    
        left = 0
        right = len(Arr)-1
    
        while left <= right :
            mid = (left + right)//2
        
            if Arr[mid] == find :
                return mid
            elif Arr[mid] < find :
                left = mid + 1
            else:
                right = mid - 1
    
        return left
