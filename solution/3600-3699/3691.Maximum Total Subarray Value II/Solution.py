class SparseTableRMQ:
    def __init__(self, data: List[int]):
        self.n = len(data)
        self.max_log = self.n.bit_length() + 1
        self.f_max = [[0] * self.max_log for _ in range(self.n)]
        self.f_min = [[0] * self.max_log for _ in range(self.n)]

        self.lg = [0] * (self.n + 1)
        for i in range(2, self.n + 1):
            self.lg[i] = self.lg[i >> 1] + 1

        for i in range(self.n):
            self.f_max[i][0] = data[i]
            self.f_min[i][0] = data[i]

        for j in range(1, self.max_log):
            for i in range(self.n - (1 << j) + 1):
                self.f_max[i][j] = max(
                    self.f_max[i][j - 1], self.f_max[i + (1 << (j - 1))][j - 1]
                )
                self.f_min[i][j] = min(
                    self.f_min[i][j - 1], self.f_min[i + (1 << (j - 1))][j - 1]
                )

    def query_max(self, l: int, r: int) -> int:
        k = self.lg[r - l + 1]
        return max(self.f_max[l][k], self.f_max[r - (1 << k) + 1][k])

    def query_min(self, l: int, r: int) -> int:
        k = self.lg[r - l + 1]
        return min(self.f_min[l][k], self.f_min[r - (1 << k) + 1][k])


class Solution:
    def maxTotalValue(self, nums: List[int], k: int) -> int:
        n = len(nums)
        st = SparseTableRMQ(nums)
        pq = []
        for l in range(n):
            val = st.query_max(l, n - 1) - st.query_min(l, n - 1)
            heappush(pq, (-val, l, n - 1))

        ans = 0
        for _ in range(k):
            val, l, r = heappop(pq)
            ans += -val
            if r > l:
                val = st.query_max(l, r - 1) - st.query_min(l, r - 1)
                heappush(pq, (-val, l, r - 1))
        return ans
