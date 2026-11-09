class Solution:
    def largestAltitude(self, gain: list[int]) -> int:
        return max(accumulate(gain, initial=0))
