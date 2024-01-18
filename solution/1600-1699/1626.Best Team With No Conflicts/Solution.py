class Solution:
    def bestTeamScore(self, scores: List[int], ages: List[int]) -> int:
        arr = sorted(zip(scores, ages))
        n = len(arr)
        f = [0] * n
        for i, (score, age) in enumerate(arr):
            for j in range(i):
                if age >= arr[j][1]:
                    f[i] = max(f[i], f[j])
            f[i] += score
        return max(f)
