class Solution:
    def modeWeight(self, nums: List[int], k: int) -> int:
        pq = []
        cnt = defaultdict(int)
        for x in nums[:k]:
            cnt[x] += 1
            heappush(pq, (-cnt[x], x))

        def get_mode() -> int:
            while -pq[0][0] != cnt[pq[0][1]]:
                heappop(pq)
            freq, val = -pq[0][0], pq[0][1]
            return freq * val

        ans = 0
        ans += get_mode()

        for i in range(k, len(nums)):
            x, y = nums[i], nums[i - k]
            cnt[x] += 1
            cnt[y] -= 1
            heappush(pq, (-cnt[x], x))
            heappush(pq, (-cnt[y], y))

            ans += get_mode()

        return ans
