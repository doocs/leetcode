class Solution:
    def longestSubsequenceRepeatedK(self, s: str, k: int) -> str:
        def check(t: str, k: int) -> bool:
            i = 0
            for c in s:
                if c == t[i]:
                    i += 1
                    if i == len(t):
                        k -= 1
                        if k == 0:
                            return True
                        i = 0
            return False

        cnt = Counter(s)
        cs = [c for c in ascii_lowercase if cnt[c] >= k]
        q = deque([""])
        ans = ""
        while q:
            cur = q.popleft()
            for c in cs:
                nxt = cur + c
                if check(nxt, k):
                    ans = nxt
                    q.append(nxt)
        return ans
