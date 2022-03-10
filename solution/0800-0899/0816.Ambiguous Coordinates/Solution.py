class Solution:
    def ambiguousCoordinates(self, s: str) -> List[str]:
        def convert(i, j):
            res = []
            for k in range(1, j - i + 1):
                left, right = s[i : i + k], s[i + k : j]
                valid = (
                    left == '0' or not left.startswith('0')
                ) and not right.endswith('0')
                if valid:
                    res.append(left + ('.' if k < j - i else '') + right)
            return res

        n = len(s)
        ans = []
        for i in range(2, n - 1):
            for x in convert(1, i):
                for y in convert(i, n - 1):
                    ans.append(f'({x}, {y})')
        return ans
