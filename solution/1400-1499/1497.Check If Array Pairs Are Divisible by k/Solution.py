class Solution:
    def canArrange(self, arr: List[int], k: int) -> bool:
        mod = [0] * k
        for v in arr:
            mod[v % k] += 1
        return all(mod[i] == mod[k - i] for i in range(1, k)) and mod[0] % 2 == 0
