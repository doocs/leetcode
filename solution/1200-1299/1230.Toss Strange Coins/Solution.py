class Solution:
    def probabilityOfHeads(self, prob: List[float], target: int) -> float:
        f = [0] * (target + 1)
        f[0] = 1
        for p in prob:
            for j in range(target, -1, -1):
                f[j] *= 1 - p
                if j:
                    f[j] += p * f[j - 1]
        return f[target]
