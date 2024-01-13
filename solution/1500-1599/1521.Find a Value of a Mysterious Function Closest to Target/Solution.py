class Solution:
    def closestToTarget(self, arr: List[int], target: int) -> int:
        ans = abs(arr[0] - target)
        s = {arr[0]}
        for x in arr:
            s = {x & y for y in s} | {x}
            ans = min(ans, min(abs(y - target) for y in s))
        return ans
