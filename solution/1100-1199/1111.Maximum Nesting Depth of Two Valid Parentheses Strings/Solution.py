class Solution:
    def maxDepthAfterSplit(self, seq: str) -> List[int]:
        ans = [0] * len(seq)
        a = b = 0
        for i, c in enumerate(seq):
            if c == "(":
                if a < b:
                    a += 1
                else:
                    b += 1
                    ans[i] = 1
            else:
                if a > b:
                    a -= 1
                else:
                    b -= 1
                    ans[i] = 1
        return ans
