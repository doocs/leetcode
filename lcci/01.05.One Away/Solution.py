class Solution:
    def oneEditAway(self, first: str, second: str) -> bool:
        n1, n2 = len(first), len(second)
        diff = n1 - n2
        if abs(diff) > 1:
            return False
        i, j, op = 0, 0, 1
        while i < n1 and j < n2:
            not_same = first[i] != second[j]
            if not_same:
                if diff == 1:
                    i += 1
                elif diff == -1:
                    j += 1
                else:
                    i += 1
                    j += 1
                op -= 1
            else:
                i += 1
                j += 1
            if op < 0:
                return False
        return True
