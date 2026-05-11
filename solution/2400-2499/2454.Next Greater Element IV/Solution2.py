
class Solution:
    def secondGreaterElement(self, nums: list[int]) -> list[int]:
        second_next_greater = [-1] * len(nums)

        stack_1: list[tuple[int, int]] = []  # Decreasing monotonic stacks: (num, idx).
        stack_2: list[tuple[int, int]] = []

        transporter: list[tuple[int, int]] = []  # Transport tuples from stack 1 to stack 2.

        for idx, num in enumerate(nums):
            while stack_2 and stack_2[-1][0] < num:
                _, past_idx = stack_2.pop(-1)
                second_next_greater[past_idx] = num

            while stack_1 and stack_1[-1][0] < num:
                transporter.append(stack_1.pop(-1))

            while transporter:
                stack_2.append(transporter.pop(-1))  # Ensure decreasing monotonicity.
            stack_1.append((num, idx))

        return second_next_greater
