class Solution:
    def maximumScore(self, a: int, b: int, c: int) -> int:
        h = [-a, -b, -c]
        heapify(h)
        ans = 0
        while 1:
            a, b = heappop(h), heappop(h)
            if b == 0:
                break
            heappush(h, a + 1)
            heappush(h, b + 1)
            ans += 1
        return ans
