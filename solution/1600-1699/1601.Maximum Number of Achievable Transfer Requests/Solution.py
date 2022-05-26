class Solution:
    def maximumRequests(self, n: int, requests: List[List[int]]) -> int:
        def check(x):
            delta = [0] * n
            for i, (f, t) in enumerate(requests):
                if (x >> i) & 1:
                    delta[f] -= 1
                    delta[t] += 1
            return all(d == 0 for d in delta)

        ans, m = 0, len(requests)
        for mask in range(1 << m):
            cnt = mask.bit_count()
            if cnt <= ans:
                continue
            if check(mask):
                ans = cnt
        return ans
