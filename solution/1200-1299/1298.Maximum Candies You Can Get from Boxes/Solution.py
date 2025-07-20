class Solution:
    def maxCandies(
        self,
        status: List[int],
        candies: List[int],
        keys: List[List[int]],
        containedBoxes: List[List[int]],
        initialBoxes: List[int],
    ) -> int:
        q = deque()
        has, took = set(initialBoxes), set()
        ans = 0

        for box in initialBoxes:
            if status[box]:
                q.append(box)
                took.add(box)
                ans += candies[box]
        while q:
            box = q.popleft()
            for k in keys[box]:
                if not status[k]:
                    status[k] = 1
                    if k in has and k not in took:
                        q.append(k)
                        took.add(k)
                        ans += candies[k]

            for b in containedBoxes[box]:
                has.add(b)
                if status[b] and b not in took:
                    q.append(b)
                    took.add(b)
                    ans += candies[b]
        return ans
