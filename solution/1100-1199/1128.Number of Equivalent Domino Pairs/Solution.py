class Solution:
    def numEquivDominoPairs(self, dominoes: List[List[int]]) -> int:
        cnt = Counter()
        ans = 0
        for a, b in dominoes:
            ans += cnt[(a, b)]
            cnt[(a, b)] += 1
            if a != b:
                cnt[(b, a)] += 1
        return ans
