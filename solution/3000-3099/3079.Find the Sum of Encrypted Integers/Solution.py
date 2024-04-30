class Solution:
    def sumOfEncryptedInt(self, nums: List[int]) -> int:
        def encrypt(x: int) -> int:
            mx = p = 0
            while x:
                x, v = divmod(x, 10)
                mx = max(mx, v)
                p = p * 10 + 1
            return mx * p

        return sum(encrypt(x) for x in nums)
