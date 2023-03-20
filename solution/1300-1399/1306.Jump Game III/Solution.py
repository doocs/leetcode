class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        q = deque([start])
        while q:
            i = q.popleft()
            if arr[i] == 0:
                return True
            x = arr[i]
            arr[i] = -1
            for j in (i + x, i - x):
                if 0 <= j < len(arr) and arr[j] >= 0:
                    q.append(j)
        return False
