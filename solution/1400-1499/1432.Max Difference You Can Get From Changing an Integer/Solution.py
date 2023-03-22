class Solution:
    def maxDiff(self, num: int) -> int:
        a, b = str(num), str(num)
        for c in a:
            if c != "9":
                a = a.replace(c, "9")
                break
        if b[0] != "1":
            b = b.replace(b[0], "1")
        else:
            for c in b[1:]:
                if c not in "01":
                    b = b.replace(c, "0")
                    break
        return int(a) - int(b)
