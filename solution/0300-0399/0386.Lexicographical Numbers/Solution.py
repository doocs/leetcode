class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        v = 1
        ans = []
        for i in range(n):
            ans.append(v)
            if v * 10 <= n:
                v *= 10
            else:
                while v % 10 == 9 or v + 1 > n:
                    v //= 10
                v += 1
        return ans
