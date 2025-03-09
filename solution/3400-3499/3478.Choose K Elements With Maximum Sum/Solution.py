class Solution:
    def findMaxSum(self, nums1: List[int], nums2: List[int], k: int) -> List[int]:
        arr = [(x, i) for i, x in enumerate(nums1)]
        arr.sort()
        pq = []
        s = j = 0
        n = len(arr)
        ans = [0] * n
        for h, (x, i) in enumerate(arr):
            while j < h and arr[j][0] < x:
                y = nums2[arr[j][1]]
                heappush(pq, y)
                s += y
                if len(pq) > k:
                    s -= heappop(pq)
                j += 1
            ans[i] = s
        return ans
