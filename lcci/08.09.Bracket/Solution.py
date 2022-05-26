class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        res = []
        def generate(state, left, right):
            if left > right:
                return
            if right == 0:
                res.append(state)
                return
            if left > 0:
                generate(state + '(', left - 1, right)
            if right > 0:
                generate(state + ')', left, right - 1)
        generate('', n, n)
        return res