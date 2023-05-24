class Solution:
    def sumFourDivisors(self, nums: List[int]) -> int:
        def f(x: int) -> int:
            i = 2
            cnt, s = 2, x + 1
            while i <= x // i:
                if x % i == 0:
                    cnt += 1
                    s += i
                    if i * i != x:
                        cnt += 1
                        s += x // i
                i += 1
            return s if cnt == 4 else 0

        return sum(f(x) for x in nums)
