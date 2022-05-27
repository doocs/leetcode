class Solution:
    def maximumRequests(self, n: int, requests: List[List[int]]) -> int:
        def check(x):
            d = [0] * n
            for i, (f, t) in enumerate(requests):
                if (x >> i) & 1:
                    d[f] -= 1
                    d[t] += 1
            return all(v == 0 for v in d)

        ans, m = 0, len(requests)
        for mask in range(1 << m):
            cnt = mask.bit_count()
            if cnt > ans and check(mask):
                ans = cnt
        return ans
