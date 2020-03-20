class Solution:
    def oneEditAway(self, first: str, second: str) -> bool:
        n1, n2 = len(first), len(second)
        if abs(n1 - n2) > 1:
            return False
        if n1 + n2 <= 2:
            return True
        if first[0] != second[0]:
            if n1 == n2:
                return first[1:] == second[1:]
            else:
                return first[1:] == second or second[1:] == first
        else:
            return self.oneEditAway(first[1:], second[1:])