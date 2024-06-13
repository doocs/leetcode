class Solution:
    def findWinningPlayer(self, skills: List[int], k: int) -> int:
        n = len(skills)
        k = min(k, n - 1)
        i = cnt = 0
        for j in range(1, n):
            if skills[i] < skills[j]:
                i = j
                cnt = 1
            else:
                cnt += 1
            if cnt == k:
                break
        return i
