class Solution:
    def perfectMenu(
        self,
        materials: List[int],
        cookbooks: List[List[int]],
        attribute: List[List[int]],
        limit: int,
    ) -> int:
        n = len(cookbooks)
        ans = -1
        for mask in range(1 << n):
            a = b = 0
            cnt = [0] * 5
            for i in range(n):
                if mask >> i & 1:
                    x, y = attribute[i]
                    a += x
                    b += y
                    for j, v in enumerate(cookbooks[i]):
                        cnt[j] += v
            if b >= limit and ans < a and all(c <= d for c, d in zip(cnt, materials)):
                ans = a
        return ans
