class Solution:
    def isHappy(self, n: int) -> bool:
        def get_next(n):
            s = 0
            while n > 0:
                n, digit = divmod(n, 10)
                s += digit**2
            return s

        visited = set()
        while n != 1 and n not in visited:
            visited.add(n)
            n = get_next(n)
        return n == 1
