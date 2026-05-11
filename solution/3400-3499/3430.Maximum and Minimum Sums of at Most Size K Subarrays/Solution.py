
class Solution:
    def minMaxSubarraySum(self, nums: list[int], k: int) -> int:
        subarrays_max_min_sum = 0

        max_stack: deque[list[int]] = deque([])  # Format: [idx, num, shares].
        subarrays_max_sum = 0

        min_stack: deque[list[int]] = deque([])  # Format: [idx, num, shares].
        subarrays_min_sum = 0

        for end_idx, num in enumerate(nums):
            start_idx = max(0, end_idx - k + 1)

            # Window start idx slides by 1: must update stacks' info.
            if start_idx > 0:
                max_stack[0][2] -= 1  # Decrement stack's front num shares.
                subarrays_max_sum -= max_stack[0][1]

                if max_stack[0][0] < start_idx:  # Front num out of window.
                    max_stack.popleft()

                min_stack[0][2] -= 1  # Decrement stack's front num shares.
                subarrays_min_sum -= min_stack[0][1]

                if min_stack[0][0] < start_idx:  # Front num out of window.
                    min_stack.popleft()

            max_shares = 1  # Base case.
            subarrays_max_sum += num

            while max_stack and max_stack[-1][1] <= num:
                _, prev_num, prev_shares = max_stack.pop()

                max_shares += prev_shares  # Max shares transition.

                # Reflect transition in max sum.
                subarrays_max_sum += (num - prev_num) * prev_shares

            max_stack.append([end_idx, num, max_shares])

            min_shares = 1  # Base case.
            subarrays_min_sum += num

            while min_stack and min_stack[-1][1] >= num:
                _, prev_num, prev_shares = min_stack.pop()

                min_shares += prev_shares  # Min shares transition.

                # Reflect transition in min sum.
                subarrays_min_sum += (num - prev_num) * prev_shares

            min_stack.append([end_idx, num, min_shares])

            subarrays_max_min_sum += subarrays_max_sum + subarrays_min_sum

        return subarrays_max_min_sum
