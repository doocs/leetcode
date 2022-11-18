class Solution:
    def splitMessage(self, message: str, limit: int) -> List[str]:
        n = len(message)
        sa = 0
        for k in range(1, n + 1):
            sa += len(str(k))
            sb = len(str(k)) * k
            sc = 3 * k
            if limit * k - (sa + sb + sc) >= n:
                ans = []
                i = 0
                for j in range(1, k + 1):
                    tail = f'<{j}/{k}>'
                    t = message[i : i + limit - len(tail)] + tail
                    ans.append(t)
                    i += limit - len(tail)
                return ans
        return []
