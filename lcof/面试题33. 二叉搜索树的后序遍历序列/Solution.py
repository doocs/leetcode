class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        n = len(postorder)
        if n < 2:
            return True
        for i in range(n):
            if postorder[i] > postorder[-1]:
                break
        for j in range(i + 1, n - 1):
            if postorder[j] < postorder[-1]:
                return False
        return (i == 0 or self.verifyPostorder(postorder[:i])) and (i == n - 1 or self.verifyPostorder(postorder[i:-1]))
