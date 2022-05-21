class Solution:
    def flipgame(self, fronts: List[int], backs: List[int]) -> int:
        same = {a for a, b in zip(fronts, backs) if a == b}
        ans = 9999
        for x in chain(fronts, backs):
            if x not in same:
                ans = min(ans, x)
        return ans % 9999
