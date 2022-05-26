class Solution:
    def minOperations(self, boxes: str) -> List[int]:
        n = len(boxes)
        res = [0] * n
        total = 0
        for i, b in enumerate(boxes):
            if b == '1':
                res[0] += i
                total += 1
        left, right = 0, total
        for i in range(1, n):
            if boxes[i - 1] == '1':
                left += 1
                right -= 1
            res[i] = res[i - 1] + left - right
        return res
