class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        if len(changed) % 2 != 0:
            return []
        n = 100010
        counter = [0] * n
        for x in changed:
            counter[x] += 1
        if counter[0] % 2 != 0:
            return []
        res = [0] * (counter[0] // 2)
        for i in range(1, n):
            if counter[i] == 0:
                continue
            if i * 2 > n or counter[i] > counter[i * 2]:
                return []
            res.extend([i] * counter[i])
            counter[i * 2] -= counter[i]
        return res
