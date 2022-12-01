class Solution:
    def minOperations(self, boxes: str) -> List[int]:
        n = len(boxes)
        ans = [0] * n
        cnt = 0
        for i in range(1, n):
            if boxes[i - 1] == '1':
                cnt += 1
            ans[i] = ans[i - 1] + cnt
        cnt = s = 0
        for i in range(n - 2, -1, -1):
            if boxes[i + 1] == '1':
                cnt += 1
            s += cnt
            ans[i] += s
        return ans
