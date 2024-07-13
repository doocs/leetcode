class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        ans = []
        v = 1
        for _ in range(n):
            ans.append(v)
            if v * 10 <= n:
                v *= 10
            else:
                while v % 10 == 9 or v + 1 > n:
                    v //= 10
                v += 1
        return ans
