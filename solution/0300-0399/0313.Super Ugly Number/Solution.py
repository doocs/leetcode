from queue import PriorityQueue


class Solution:
    def nthSuperUglyNumber(self, n: int, primes: List[int]) -> int:
        ugly, pq, p = [0]*(n+1), PriorityQueue(), 2
        ugly[1] = 1
        for prime in primes:
            pq.put([prime, prime, 2])

        while p <= n:
            top = pq.get()
            if top[0] != ugly[p-1]:
                ugly[p], p = top[0], p+1
            top[0], top[2] = ugly[top[2]]*top[1], top[2]+1
            pq.put(top)
        return ugly[n]