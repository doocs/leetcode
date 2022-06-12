class Solution:
    def successfulPairs(self, spells: List[int], potions: List[int], success: int) -> List[int]:
        potions.sort()
        m = len(potions)
        ans = []
        for s in spells:
            left, right = 0, m
            while left < right:
                mid = (left + right) >> 1
                if s * potions[mid] >= success:
                    right = mid
                else:
                    left = mid + 1
            ans.append(m - left)
        return ans
