class Solution:
    def minimumPairRemoval(self, nums: List[int]) -> int:
        n = len(nums)
        sl = SortedList()
        idx = SortedList(range(n))
        inv = 0
        for i in range(n - 1):
            sl.add((nums[i] + nums[i + 1], i))
            if nums[i] > nums[i + 1]:
                inv += 1
        ans = 0
        while inv:
            ans += 1
            s, i = sl.pop(0)
            pos = idx.index(i)
            j = idx[pos + 1]
            if nums[i] > nums[j]:
                inv -= 1
            if pos > 0:
                h = idx[pos - 1]
                if nums[h] > nums[i]:
                    inv -= 1
                sl.remove((nums[h] + nums[i], h))
                if nums[h] > s:
                    inv += 1
                sl.add((nums[h] + s, h))
            if pos + 2 < len(idx):
                k = idx[pos + 2]
                if nums[j] > nums[k]:
                    inv -= 1
                sl.remove((nums[j] + nums[k], j))
                if s > nums[k]:
                    inv += 1
                sl.add((s + nums[k], i))

            nums[i] = s
            idx.remove(j)
        return ans
