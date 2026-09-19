class Solution:
    def countOfAtoms(self, formula: str) -> str:
        cnt = defaultdict(int)
        stack = []
        multiplier, freq = 1, 0
        i = len(formula) - 1
        while i >= 0:
            c = formula[i]
            if c.islower():
                end = i
                i -= 1
                while i >= 0 and formula[i].islower():
                    i -= 1
                cnt[formula[i : end + 1]] += max(freq, 1) * multiplier
                freq = 0
            elif c.isupper():
                cnt[c] += max(freq, 1) * multiplier
                freq = 0
            elif c.isdigit():
                freq = ord(c) - 48
                p = 10
                while i - 1 >= 0 and formula[i - 1].isdigit():
                    i -= 1
                    freq += p * (ord(formula[i]) - 48)
                    p *= 10
            elif c == ')':
                stack.append(multiplier)
                multiplier *= max(freq, 1)
                freq = 0
            else:
                multiplier = stack.pop()
            i -= 1
        ans = []
        for key in sorted(cnt):
            ans.append(key)
            if cnt[key] > 1:
                ans.append(str(cnt[key]))
        return ''.join(ans)
