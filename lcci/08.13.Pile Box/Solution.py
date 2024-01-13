class Solution:
    def pileBox(self, box: List[List[int]]) -> int:
        box.sort(key=lambda x: (x[0], -x[1]))
        n = len(box)
        f = [0] * n
        for i in range(n):
            for j in range(i):
                if box[j][1] < box[i][1] and box[j][2] < box[i][2]:
                    f[i] = max(f[i], f[j])
            f[i] += box[i][2]
        return max(f)
