class Solution:
    def countTriplets(self, arr: List[int]) -> int:
        n = len(arr)
        pre = [0] * (n + 1)
        for i in range(n):
            pre[i + 1] = pre[i] ^ arr[i]
        ans = 0
        for i in range(n - 1):
            for j in range(i + 1, n):
                for k in range(j, n):
                    a, b = pre[j] ^ pre[i], pre[k + 1] ^ pre[j]
                    if a == b:
                        ans += 1
        return ans
