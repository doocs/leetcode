class Solution:
    def slowestKey(self, releaseTimes: List[int], keysPressed: str) -> str:
        ans = keysPressed[0]
        mx = releaseTimes[0]
        for i in range(1, len(keysPressed)):
            d = releaseTimes[i] - releaseTimes[i - 1]
            if d > mx or (d == mx and ord(keysPressed[i]) > ord(ans)):
                mx = d
                ans = keysPressed[i]
        return ans
