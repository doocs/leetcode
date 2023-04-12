class Solution:
    def findLucky(self, arr: List[int]) -> int:
        cnt = Counter(arr)
        ans = -1
        for x, v in cnt.items():
            if x == v and ans < x:
                ans = x
        return ans
