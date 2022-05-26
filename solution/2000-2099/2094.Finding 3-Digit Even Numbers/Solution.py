class Solution:
    def findEvenNumbers(self, digits: List[int]) -> List[int]:
        ans = []
        counter = Counter(digits)
        for i in range(100, 1000, 2):
            t = []
            k = i
            while k:
                t.append(k % 10)
                k //= 10
            cnt = Counter(t)
            if all([counter[i] >= cnt[i] for i in range(10)]):
                ans.append(i)
        return ans
