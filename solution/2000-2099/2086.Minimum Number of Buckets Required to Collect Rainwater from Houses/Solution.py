class Solution:
    def minimumBuckets(self, street: str) -> int:
        n, last = len(street), -2
        ans = 0
        for i, v in enumerate(street):
            if v == 'H':
                if last == i - 1:
                    continue
                if i + 1 < n and street[i + 1] == '.':
                    last = i + 1
                elif i - 1 >= 0 and street[i - 1] == '.':
                    last = i - 1
                else:
                    return -1
                ans += 1
        return ans
