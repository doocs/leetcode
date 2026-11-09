class Solution:
    def mostFrequentIDs(self, nums: list[int], freq: list[int]) -> list[int]:
        cnt = Counter()
        lazy = Counter()
        ans = []
        pq = []
        for x, f in zip(nums, freq):
            lazy[cnt[x]] += 1
            cnt[x] += f
            heappush(pq, -cnt[x])
            while pq and lazy[-pq[0]] > 0:
                lazy[-pq[0]] -= 1
                heappop(pq)
            ans.append(0 if not pq else -pq[0])
        return ans
