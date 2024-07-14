class Solution:
    def decode(self, encoded: List[int], first: int) -> List[int]:
        ans = [first]
        for x in encoded:
            ans.append(ans[-1] ^ x)
        return ans
