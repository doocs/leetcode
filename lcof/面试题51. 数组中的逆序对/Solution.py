class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        def merge_sort(l, r):
            if l >= r:
                return 0
            mid = (l + r) >> 1
            ans = merge_sort(l, mid) + merge_sort(mid + 1, r)
            t = []
            i, j = l, mid + 1
            while i <= mid and j <= r:
                if nums[i] <= nums[j]:
                    t.append(nums[i])
                    i += 1
                else:
                    ans += mid - i + 1
                    t.append(nums[j])
                    j += 1
            t.extend(nums[i : mid + 1])
            t.extend(nums[j : r + 1])
            nums[l : r + 1] = t
            return ans

        return merge_sort(0, len(nums) - 1)
