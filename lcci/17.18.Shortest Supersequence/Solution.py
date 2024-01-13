class Solution:
    def shortestSeq(self, big: List[int], small: List[int]) -> List[int]:
        need = Counter(small)
        window = Counter()
        cnt, j, k, mi = len(small), 0, -1, inf
        for i, x in enumerate(big):
            window[x] += 1
            if need[x] >= window[x]:
                cnt -= 1
            while cnt == 0:
                if i - j + 1 < mi:
                    mi = i - j + 1
                    k = j
                if need[big[j]] >= window[big[j]]:
                    cnt += 1
                window[big[j]] -= 1
                j += 1
        return [] if k < 0 else [k, k + mi - 1]
