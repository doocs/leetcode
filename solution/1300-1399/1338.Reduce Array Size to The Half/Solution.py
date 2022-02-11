class Solution:
    def minSetSize(self, arr: List[int]) -> int:
        couter = Counter(arr)
        ans = n = 0
        for _, cnt in couter.most_common():
            n += cnt
            ans += 1
            if n * 2 >= len(arr):
                break
        return ans
