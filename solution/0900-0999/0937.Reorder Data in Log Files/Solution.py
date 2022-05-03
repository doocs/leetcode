class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        def cmp(x):
            a, b = x.split(' ', 1)
            return (0, b, a) if b[0].isalpha() else (1,)

        return sorted(logs, key=cmp)
