class Solution:
    def sortArray(self, nums: List[int], pre: List[int]) -> int:
        n = len(nums)
        target = tuple(range(n))
        start = tuple(nums)

        if start == target:
            return 0

        vis = {start}
        q = deque([(start, 0)])

        while q:
            state, dist = q.popleft()
            nd = dist + 1
            for x in pre:
                nxt = state[:x][::-1] + state[x:]
                if nxt == target:
                    return nd
                if nxt not in vis:
                    vis.add(nxt)
                    q.append((nxt, nd))
        return -1
