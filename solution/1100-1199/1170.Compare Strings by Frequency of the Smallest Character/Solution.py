class Solution:
    def numSmallerByFrequency(self, queries: List[str], words: List[str]) -> List[int]:
        def f(s):
            cnt = Counter(s)
            for c in ascii_lowercase:
                if cnt[c]:
                    return cnt[c]

        arr = [f(s) for s in words]
        arr.sort()
        n = len(arr)
        return [n - bisect_right(arr, f(q)) for q in queries]
