class Solution:
    def nextBeautifulNumber(self, n: int) -> int:
        def check(num):
            counter = [0] * 10
            for c in str(num):
                counter[int(c)] += 1

            for c in str(num):
                if counter[int(c)] != int(c):
                    return False
            return True

        for i in range(n + 1, 10**7):
            if check(i):
                return i
        return -1
