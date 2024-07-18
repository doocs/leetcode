class Solution:
  def numSubmatrixSumTarget(self, matrix, target):
    """
    :type matrix: List[List[int]]
    :type target: int
    :rtype: int
    """
    m = len(matrix)
    n = len(matrix[0])
    ans = 0
    for i in range(m):
      col = [0] * n
      for j in range(i, m):
        for k in range(n):
          col[k] += matrix[j][k]
        ans += self.f(col, target)
    return ans

  def f(self, nums, target):
    """
    :type nums: List[int]
    :type target: int
    :rtype: int
    """
    d = {0: 1}  # map to store prefix sum counts
    s = 0
    cnt = 0
    for x in nums:
      s += x
      cnt += d.get(s - target, 0)  # count subarray sums equal to target
      d[s] = d.get(s, 0) + 1  # update prefix sum count
    return cnt
