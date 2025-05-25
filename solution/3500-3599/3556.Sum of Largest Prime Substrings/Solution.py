class Solution:
    def sumOfLargestPrimes(self, s: str) -> int:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        st = set()
        n = len(s)
        for i in range(n):
            x = 0
            for j in range(i, n):
                x = x * 10 + int(s[j])
                if is_prime(x):
                    st.add(x)
        return sum(sorted(st)[-3:])
