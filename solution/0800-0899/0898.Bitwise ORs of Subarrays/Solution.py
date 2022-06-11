class Solution:
    def subarrayBitwiseORs(self, arr: List[int]) -> int:
        s = set()
        prev = 0
        for i, v in enumerate(arr):
            prev |= v
            curr = 0
            for j in range(i, -1, -1):
                curr |= arr[j]
                s.add(curr)
                if curr == prev:
                    break
        return len(s)
