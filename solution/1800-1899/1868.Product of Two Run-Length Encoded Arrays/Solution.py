class Solution:
    def findRLEArray(
        self, encoded1: List[List[int]], encoded2: List[List[int]]
    ) -> List[List[int]]:
        ans = []
        j = 0
        for vi, fi in encoded1:
            while fi:
                f = min(fi, encoded2[j][1])
                v = vi * encoded2[j][0]
                if ans and ans[-1][0] == v:
                    ans[-1][1] += f
                else:
                    ans.append([v, f])
                fi -= f
                encoded2[j][1] -= f
                if encoded2[j][1] == 0:
                    j += 1
        return ans
