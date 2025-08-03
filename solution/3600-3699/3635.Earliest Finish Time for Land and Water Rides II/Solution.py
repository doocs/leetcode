class Solution:
    def earliestFinishTime(
        self,
        landStartTime: List[int],
        landDuration: List[int],
        waterStartTime: List[int],
        waterDuration: List[int],
    ) -> int:
        def calc(a1, t1, a2, t2):
            min_end = min(a + t for a, t in zip(a1, t1))
            return min(max(a, min_end) + t for a, t in zip(a2, t2))

        x = calc(landStartTime, landDuration, waterStartTime, waterDuration)
        y = calc(waterStartTime, waterDuration, landStartTime, landDuration)
        return min(x, y)
