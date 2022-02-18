class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        n = len(arr)
        q = deque([start])
        while q:
            i = q.popleft()
            if arr[i] == 0:
                return True
            for j in [i + arr[i], i - arr[i]]:
                if 0 <= j < n and arr[j] >= 0:
                    q.append(j)
            arr[i] = -1
        return False
