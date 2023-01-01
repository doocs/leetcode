class Solution:
    def closestPrimes(self, left: int, right: int) -> List[int]:
        cnt = 0
        st = [False] * (right + 1)
        prime = [0] * (right + 1)
        for i in range(2, right + 1):
            if not st[i]:
                prime[cnt] = i
                cnt += 1
            j = 0
            while prime[j] <= right // i:
                st[prime[j] * i] = 1
                if i % prime[j] == 0:
                    break
                j += 1
        p = [v for v in prime[:cnt] if left <= v <= right]
        mi = inf
        ans = [-1, -1]
        for a, b in pairwise(p):
            if (d := b - a) < mi:
                mi = d
                ans = [a, b]
        return ans
