class Solution:
    def findShortestSubArray(self, nums: List[int]) -> int:
        mapper = {}
        for i, v in enumerate(nums):
            if v in mapper:
                arr = mapper[v]
                arr[0] += 1
                arr[2] = i
            else:
                arr = [1, i, i]
                mapper[v] = arr
        max_degree = min_len = 0
        for arr in mapper.values():
            if max_degree < arr[0]:
                max_degree = arr[0]
                min_len = arr[2] - arr[1] + 1
            elif max_degree == arr[0]:
                min_len = min(min_len, arr[2] - arr[1] + 1)
        return min_len
