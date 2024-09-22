class Solution:
    def minNumberOfSeconds(self, mountainHeight: int, workerTimes: List[int]) -> int:
        def check(t: int) -> bool:
            h = 0
            for wt in workerTimes:
                h += int(sqrt(2 * t / wt + 1 / 4) - 1 / 2)
            return h >= mountainHeight

        return bisect_left(range(10**16), True, key=check)
