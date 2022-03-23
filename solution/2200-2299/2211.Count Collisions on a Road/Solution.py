class Solution:
    def countCollisions(self, directions: str) -> int:
        l, r = 0, len(directions)-1
        while l <= r and directions[l] == 'L':
            l += 1
        while l <= r and directions[r] == 'R':
            r -= 1
        count = 0
        for i in range(l, r+1):
            count += directions[i] != 'S'
        return count