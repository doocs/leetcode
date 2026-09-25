class Solution:
    def removeOccurrences(self, s: str, part: str) -> str:
        m = len(part)
        st = []
        for c in s:
            st.append(c)
            if len(st) >= m and ''.join(st[-m:]) == part:
                del st[-m:]
        return ''.join(st)
