class Solution:
    def numFriendRequests(self, ages: List[int]) -> int:
        counter = Counter(ages)
        ans = 0
        for i in range(1, 121):
            n1 = counter[i]
            for j in range(1, 121):
                n2 = counter[j]
                if not (j <= 0.5 * i + 7 or j > i or (j > 100 and i < 100)):
                    ans += n1 * n2
                    if i == j:
                        ans -= n2
        return ans
