class Solution:
    def maxCandies(
        self,
        status: List[int],
        candies: List[int],
        keys: List[List[int]],
        containedBoxes: List[List[int]],
        initialBoxes: List[int],
    ) -> int:
        q = deque([i for i in initialBoxes if status[i] == 1])
        ans = sum(candies[i] for i in initialBoxes if status[i] == 1)
        has = set(initialBoxes)
        took = {i for i in initialBoxes if status[i] == 1}

        while q:
            i = q.popleft()
            for k in keys[i]:
                status[k] = 1
                if k in has and k not in took:
                    ans += candies[k]
                    took.add(k)
                    q.append(k)
            for j in containedBoxes[i]:
                has.add(j)
                if status[j] and j not in took:
                    ans += candies[j]
                    took.add(j)
                    q.append(j)
        return ans
