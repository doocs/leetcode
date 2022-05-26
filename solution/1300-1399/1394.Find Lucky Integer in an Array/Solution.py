class Solution:
    def findLucky(self, arr: List[int]) -> int:
        counter = Counter(arr)
        ans = -1
        for num, n in counter.items():
            if num == n and ans < num:
                ans = num
        return ans
