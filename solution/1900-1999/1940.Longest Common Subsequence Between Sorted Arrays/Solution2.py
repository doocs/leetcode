class Solution:
    def longestCommomSubsequence(self, arrays: List[List[int]]) -> List[int]:
        def common(l1, l2):
            i, j, n1, n2 = 0, 0, len(l1), len(l2)
            res = []
            while i < n1 and j < n2:
                if l1[i] == l2[j]:
                    res.append(l1[i])
                    i += 1
                    j += 1
                elif l1[i] > l2[j]:
                    j += 1
                else:
                    i += 1
            return res

        n = len(arrays)
        for i in range(1, n):
            arrays[i] = common(arrays[i - 1], arrays[i])
        return arrays[n - 1]
