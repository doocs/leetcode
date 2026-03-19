class Solution:
    def validSubarrays(self, nums: list[int], k: int) -> int:
        n = len(nums)
        peaks = []
        for i in range(1, n - 1):
            if nums[i] > nums[i - 1] and nums[i] > nums[i + 1]:
                peaks.append(i)

        ans = 0
        for j, p in enumerate(peaks):
            left_min = max(p - k, 0)
            if j > 0:
                left_min = max(left_min, peaks[j - 1] + 1)

            right_max = min(p + k, n - 1)
            if j < len(peaks) - 1:
                right_max = min(right_max, peaks[j + 1] - 1)

            ans += (p - left_min + 1) * (right_max - p + 1)
        return ans
