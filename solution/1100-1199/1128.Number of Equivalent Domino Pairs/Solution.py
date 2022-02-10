class Solution:
    def numEquivDominoPairs(self, dominoes: List[List[int]]) -> int:
        counter = Counter()
        ans = 0
        for a, b in dominoes:
            v = a * 10 + b if a > b else b * 10 + a
            ans += counter[v]
            counter[v] += 1
        return ans
