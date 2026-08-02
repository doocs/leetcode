class Solution:
    def countTasks(self, tasks: List[int], shifts: List[int]) -> List[int]:
        m, n = len(tasks), len(shifts)
        s = list(accumulate(tasks, initial=0))
        ans = [0] * n
        i = cur = 0
        for j in range(n):
            if shifts[j] < tasks[i] - cur:
                cur += shifts[j]
                ans[j] = m - i
            else:
                t = shifts[j] - (tasks[i] - cur)
                if t >= s[-1] - s[i + 1]:
                    i = cur = 0
                else:
                    l, r = i + 1, m
                    while l < r:
                        mid = (l + r) >> 1
                        if t < s[mid + 1] - s[i + 1]:
                            r = mid
                        else:
                            l = mid + 1
                    cur = t - (s[l] - s[i + 1])
                    i = l
                    ans[j] = m - i
        return ans
