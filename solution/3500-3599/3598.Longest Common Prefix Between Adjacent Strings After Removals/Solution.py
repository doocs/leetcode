class Solution:
    def longestCommonPrefix(self, words: List[str]) -> List[int]:
        @cache
        def calc(s: str, t: str) -> int:
            k = 0
            for a, b in zip(s, t):
                if a != b:
                    break
                k += 1
            return k

        def add(i: int, j: int):
            if 0 <= i < n and 0 <= j < n:
                sl.add(calc(words[i], words[j]))

        def remove(i: int, j: int):
            if 0 <= i < n and 0 <= j < n:
                sl.remove(calc(words[i], words[j]))

        n = len(words)
        sl = SortedList(calc(a, b) for a, b in pairwise(words))
        ans = []
        for i in range(n):
            remove(i, i + 1)
            remove(i - 1, i)
            add(i - 1, i + 1)
            ans.append(sl[-1] if sl and sl[-1] > 0 else 0)
            remove(i - 1, i + 1)
            add(i - 1, i)
            add(i, i + 1)
        return ans
