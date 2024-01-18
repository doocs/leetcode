class Solution:
    def minimumOperations(self, nums: List[int], start: int, goal: int) -> int:
        def next(x):
            res = []
            for num in nums:
                res.append(x + num)
                res.append(x - num)
                res.append(x ^ num)
            return res

        q = deque([start])
        vis = {start}
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                x = q.popleft()
                for y in next(x):
                    if y == goal:
                        return ans
                    if 0 <= y <= 1000 and y not in vis:
                        vis.add(y)
                        q.append(y)
        return -1
