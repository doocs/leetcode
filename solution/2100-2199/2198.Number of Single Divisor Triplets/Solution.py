class Solution:
    def singleDivisorTriplet(self, nums: List[int]) -> int:
        def check(a, b, c):
            s = a + b + c
            return sum(s % x == 0 for x in [a, b, c]) == 1

        counter = Counter(nums)
        ans = 0
        for a, cnt1 in counter.items():
            for b, cnt2 in counter.items():
                for c, cnt3 in counter.items():
                    if check(a, b, c):
                        if a == b:
                            ans += cnt1 * (cnt1 - 1) * cnt3
                        elif a == c:
                            ans += cnt1 * (cnt1 - 1) * cnt2
                        elif b == c:
                            ans += cnt1 * cnt2 * (cnt2 - 1)
                        else:
                            ans += cnt1 * cnt2 * cnt3
        return ans
