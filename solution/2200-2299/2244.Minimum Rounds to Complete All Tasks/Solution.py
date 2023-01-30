class Solution:
    def minimumRounds(self, tasks: List[int]) -> int:
        cnt = Counter(tasks)
        ans = 0
        for v in cnt.values():
            if v == 1:
                return -1
            ans += v // 3 + (v % 3 != 0)
        return ans
