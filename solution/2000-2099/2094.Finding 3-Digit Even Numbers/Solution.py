class Solution:
    def findEvenNumbers(self, digits: List[int]) -> List[int]:
        cnt = Counter(digits)
        ans = []
        for x in range(100, 1000, 2):
            cnt1 = Counter()
            y = x
            while y:
                y, v = divmod(y, 10)
                cnt1[v] += 1
            if all(cnt[i] >= cnt1[i] for i in range(10)):
                ans.append(x)
        return ans
