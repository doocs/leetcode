class Solution:
    def isBalanced(self, num: str) -> bool:
        f = [0, 0]
        for i, x in enumerate(map(int, num)):
            f[i & 1] += x
        return f[0] == f[1]
