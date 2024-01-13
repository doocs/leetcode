class Solution:
    def isValidSerialization(self, preorder: str) -> bool:
        stk = []
        for c in preorder.split(","):
            stk.append(c)
            while len(stk) > 2 and stk[-1] == stk[-2] == "#" and stk[-3] != "#":
                stk = stk[:-3]
                stk.append("#")
        return len(stk) == 1 and stk[0] == "#"
