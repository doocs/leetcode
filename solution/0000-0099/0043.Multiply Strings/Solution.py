class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        def add(s1, s2):
            n1, n2 = len(s1), len(s2)
            i = carry = 0
            res = []
            while i < max(n1, n2) or carry > 0:
                a = int(s1[n1 - i - 1]) if i < n1 else 0
                b = int(s2[n2 - i - 1]) if i < n2 else 0
                carry, t = divmod(a + b + carry, 10)
                res.append(str(t))
                i += 1
            return ''.join(res[::-1])

        if '0' in [num1, num2]:
            return '0'
        n1, n2 = len(num1), len(num2)
        ans = ''
        for i in range(n1):
            a = int(num1[n1 - i - 1])
            t = ''
            for j in range(n2):
                b = int(num2[n2 - j - 1])
                t = add(t, str(a * b) + '0' * j)
            ans = add(ans, t + '0' * i)
        return ans
