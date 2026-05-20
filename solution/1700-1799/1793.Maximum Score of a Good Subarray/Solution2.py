class Solution:
    def maximumScore(self, nums: list[int], k: int) -> int:
        max_score = nums[k]  # Base case.
        min_num = nums[k]

        left_idx, right_idx = k, k

        while 0 < left_idx or right_idx < len(nums) - 1:
            if left_idx == 0:  # Can only go right.
                right_idx += 1
                min_num = min(min_num, nums[right_idx])

            elif right_idx == len(nums) - 1:  # Can only go left.
                left_idx -= 1
                min_num = min(min_num, nums[left_idx])

            else:  # Can go bidirectional.
                if nums[left_idx - 1] >= nums[right_idx + 1]:
                    left_idx -= 1
                    min_num = min(min_num, nums[left_idx])

                else:
                    right_idx += 1
                    min_num = min(min_num, nums[right_idx])

            score = min_num * (right_idx + 1 - left_idx)
            max_score = max(max_score, score)

        return max_score
