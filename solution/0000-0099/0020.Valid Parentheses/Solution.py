class Solution:
    def isValid(self, s: str) -> bool:
        if not s:
            return True
        helper = []
        for c in s:
            if c in '([{':
                helper.append(c)
            else:
                if len(helper) == 0 or (helper.pop() + c) not in ["()", "[]", "{}"]:
                    return False
        return len(helper) == 0