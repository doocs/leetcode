'''
https://leetcode.com/problems/contains-duplicate-ii/

Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
'''
# Performance
'''
Runtime: 96 ms, faster than 93.53% of Python3 online submissions for Contains Duplicate II.
Memory Usage: 20.5 MB, less than 62.50% of Python3 online submissions for Contains Duplicate II.
'''

# Algorithm Explained
'''
Create a hashmap to remember the most recent position of unique values
If we found duplicate and the range is less than k, then return true
Else remember that index

Space: O(n) with n is the number of original array
Complexity: O(n)
'''
class Solution:
  def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
    pos = {}
    for idx, element in enumerate(nums):
      if element in pos and idx - pos[element] <= k:
        return True
      pos[element] = idx
    return False
            
            
                
        