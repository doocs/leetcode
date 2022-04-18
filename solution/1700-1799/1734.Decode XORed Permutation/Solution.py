class Solution:
    def decode(self, encoded: List[int]) -> List[int]:
        n = len(encoded) + 1
        a = b = 0
        for i in range(0, n - 1, 2):
            a ^= encoded[i]
        for i in range(n + 1):
            b ^= i
        ans = [a ^ b]
        for e in encoded[::-1]:
            ans.append(ans[-1] ^ e)
        return ans[::-1]
