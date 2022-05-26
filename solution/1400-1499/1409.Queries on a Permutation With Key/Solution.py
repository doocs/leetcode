class Solution:
    def processQueries(self, queries: List[int], m: int) -> List[int]:
        nums = list(range(1, m + 1))
        res = []
        for num in queries:
            res.append(nums.index(num))
            nums.remove(num)
            nums.insert(0, num)
        return res
