a, b = 0, 1
mx = 10**9 + 1
f = set()
while b < mx:
    a, b = b, a + b
    f.add(b)


class Solution:
    def threeFibonacciSum(self, n: int) -> bool:
        return n % 2 == 0 and n // 2 in f
