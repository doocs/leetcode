from sortedcontainers import SortedList


class Solution:
    def minimumCost(self, nums: List[int], k: int, dist: int) -> int:
        n = len(nums)

        sl = SortedList()
        y = nums[0]
        ans = float("inf")
        i = 1
        running_sum = 0

        for j in range(1, n):
            pos = bisect.bisect_left(sl, nums[j])
            sl.add(nums[j])

            if pos < k - 1:
                running_sum += nums[j]
                if len(sl) > k - 1:
                    running_sum -= sl[k - 1]

            while j - i > dist:
                removed_pos = sl.index(nums[i])
                removed_element = nums[i]
                sl.remove(removed_element)

                if removed_pos < k - 1:
                    running_sum -= removed_element
                    if len(sl) >= k - 1:
                        running_sum += sl[k - 2]
                i += 1

            if j - i + 1 >= k - 1:
                ans = min(ans, running_sum)

        return ans + y
