class Solution:
    def maxArea(self, height: int, positions: List[int], directions: str) -> int:
        delta = defaultdict(int)
        diff = res = 0
        for pos, dir in zip(positions, directions):
            res += pos
            if dir == "U":
                diff += 1
                delta[height - pos] -= 2
                delta[height * 2 - pos] += 2
            else:
                diff -= 1
                delta[pos] += 2
                delta[height + pos] -= 2
        ans = res
        pre = 0
        for cur, d in sorted(delta.items()):
            res += (cur - pre) * diff
            pre = cur
            diff += d
            ans = max(ans, res)
        return ans
