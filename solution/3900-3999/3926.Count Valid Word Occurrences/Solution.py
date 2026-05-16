class Solution:
    def countWordOccurrences(self, chunks: list[str], queries: list[str]) -> list[int]:
        s = "".join(chunks)
        n = len(s)
        cnt = defaultdict(int)
        i = 0
        while i < n:
            if s[i] in " -":
                i += 1
                continue
            j = i
            while (
                j < n
                and s[j] != " "
                and (s[j] != "-" or (j + 1 < n and s[j + 1] not in " -"))
            ):
                j += 1
            cnt[s[i:j]] += 1
            i = j
        return [cnt[q] for q in queries]
