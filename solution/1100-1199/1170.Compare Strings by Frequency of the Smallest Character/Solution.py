class Solution:
    def numSmallerByFrequency(self, queries: List[str], words: List[str]) -> List[int]:
        def f(s: str) -> int:
            cnt = Counter(s)
            for c in ascii_lowercase:
                if x := cnt[c]:
                    return x

        n = len(words)
        nums = sorted(f(w) for w in words)
        return [n - bisect_right(nums, f(q)) for q in queries]
