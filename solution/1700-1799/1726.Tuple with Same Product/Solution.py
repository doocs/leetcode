class Solution:
    def tupleSameProduct(self, nums: List[int]) -> int:
        product, n = {}, len(nums)
        for i in range(1, n):
            for j in range(0, i):
                multiplier = nums[i]*nums[j]
                product[multiplier] = product.setdefault(multiplier, 0) + 1
        ans = int(0)
        for v in product.values():
            ans += int(v*(v-1)/2)
        return ans << 3
