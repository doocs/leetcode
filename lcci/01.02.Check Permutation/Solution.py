class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        n1, n2 = len(s1), len(s2)
        if n1 != n2:
            return False
        counter = Counter()
        for i in range(n1):
            counter[s1[i]] += 1
            counter[s2[i]] -= 1
        return all(v == 0 for v in counter.values())
