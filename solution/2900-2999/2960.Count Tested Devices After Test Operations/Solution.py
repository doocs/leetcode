class Solution:
    def countTestedDevices(self, batteryPercentages: List[int]) -> int:
        ans = 0
        for x in batteryPercentages:
            ans += x > ans
        return ans
