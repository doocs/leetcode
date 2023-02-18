"""
   This is the custom function interface.
   You should not implement it, or speculate about its implementation
   class CustomFunction:
       # Returns f(x, y) for any given positive integers x and y.
       # Note that f(x, y) is increasing with respect to both x and y.
       # i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
       def f(self, x, y):
  
"""


class Solution:
    def findSolution(self, customfunction: "CustomFunction", z: int) -> List[List[int]]:
        ans = []
        x, y = 1, 1000
        while x <= 1000 and y:
            t = customfunction.f(x, y)
            if t < z:
                x += 1
            elif t > z:
                y -= 1
            else:
                ans.append([x, y])
                x, y = x + 1, y - 1
        return ans
