class Solution:
    def addToArrayForm(self, num: List[int], k: int) -> List[int]:
        i, carry = len(num) - 1, 0
        ans = []
        while i >= 0 or k or carry:
            carry += (0 if i < 0 else num[i]) + (k % 10)
            carry, v = divmod(carry, 10)
            ans.append(v)
            k //= 10
            i -= 1
        return ans[::-1]
