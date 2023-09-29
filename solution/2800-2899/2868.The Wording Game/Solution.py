class Solution:
    def canAliceWin(self, a: List[str], b: List[str]) -> bool:
        i, j, k = 1, 0, 1
        w = a[0]
        while 1:
            if k:
                if j == len(b):
                    return True
                if (b[j][0] == w[0] and b[j] > w) or ord(b[j][0]) - ord(w[0]) == 1:
                    w = b[j]
                    k ^= 1
                j += 1
            else:
                if i == len(a):
                    return False
                if (a[i][0] == w[0] and a[i] > w) or ord(a[i][0]) - ord(w[0]) == 1:
                    w = a[i]
                    k ^= 1
                i += 1
