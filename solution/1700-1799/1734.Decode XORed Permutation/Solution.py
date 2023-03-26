class Solution:
    def decode(self, encoded: List[int]) -> List[int]:
        n = len(encoded) + 1
        a = b = 0
        for i in range(0, n - 1, 2):
            a ^= encoded[i]
        for i in range(1, n + 1):
            b ^= i
        perm = [0] * n
        perm[-1] = a ^ b
        for i in range(n - 2, -1, -1):
            perm[i] = encoded[i] ^ perm[i + 1]
        return perm
