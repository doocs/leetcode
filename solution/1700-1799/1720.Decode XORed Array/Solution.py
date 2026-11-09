class Solution:
    def decode(self, encoded: list[int], first: int) -> list[int]:
        ans = [first]
        for x in encoded:
            ans.append(ans[-1] ^ x)
        return ans
