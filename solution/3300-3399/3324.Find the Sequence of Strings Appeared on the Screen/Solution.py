class Solution:
    def stringSequence(self, target: str) -> List[str]:
        ans = []
        for c in target:
            s = ans[-1] if ans else ""
            for a in ascii_lowercase:
                t = s + a
                ans.append(t)
                if a == c:
                    break
        return ans
