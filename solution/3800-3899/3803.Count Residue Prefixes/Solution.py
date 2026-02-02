class Solution:
    def residuePrefixes(self, s: str) -> int:
        st = set()
        ans = 0
        for i, c in enumerate(s, 1):
            st.add(c)
            if len(st) == i % 3:
                ans += 1
        return ans
