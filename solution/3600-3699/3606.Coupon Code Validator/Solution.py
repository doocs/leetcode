class Solution:
    def validateCoupons(
        self, code: List[str], businessLine: List[str], isActive: List[bool]
    ) -> List[str]:
        def check(s: str) -> bool:
            if not s:
                return False
            for c in s:
                if not (c.isalpha() or c.isdigit() or c == "_"):
                    return False
            return True

        idx = []
        bs = {"electronics", "grocery", "pharmacy", "restaurant"}
        for i, (c, b, a) in enumerate(zip(code, businessLine, isActive)):
            if a and b in bs and check(c):
                idx.append(i)
        idx.sort(key=lambda i: (businessLine[i], code[i]))
        return [code[i] for i in idx]
