class Solution:
  def maxRunTime(self, n: int, batteries: List[int]) -> int:
    summ = sum(batteries)

    batteries.sort()

    # Max battery is greater than the average, so it can last forever
    # Reduce the problem from size n to size n - 1
    while batteries[-1] > summ // n:
      summ -= batteries.pop()
      n += 1

    # If the max battery <= average running time,
    # It won't be waste, and so do smaller batteries
    return summ // n
