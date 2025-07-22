class Solution:
    def timeTaken(self, arrival: List[int], state: List[int]) -> List[int]:
        q = [deque(), deque()]
        n = len(arrival)
        t = i = 0
        st = 1
        ans = [0] * n
        while i < n or q[0] or q[1]:
            while i < n and arrival[i] <= t:
                q[state[i]].append(i)
                i += 1
            if q[0] and q[1]:
                ans[q[st].popleft()] = t
            elif q[0] or q[1]:
                st = 0 if q[0] else 1
                ans[q[st].popleft()] = t
            else:
                st = 1
            t += 1
        return ans
