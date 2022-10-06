class Solution:
    def evaluate(self, s: str, knowledge: List[List[str]]) -> str:
        d = {a: b for a, b in knowledge}
        i, n = 0, len(s)
        ans = []
        while i < n:
            if s[i] == '(':
                j = s.find(')', i + 1)
                ans.append(d.get(s[i + 1 : j], '?'))
                i = j
            else:
                ans.append(s[i])
            i += 1
        return ''.join(ans)
