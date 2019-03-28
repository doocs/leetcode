class Solution:

    def queryString(self, S: str, N: int) -> bool:
        """
        迭代构建数字库，然后循环判断
        """
        s = set()
        li = []
        for byte in list(S)[::-1]:
            i = int(byte)
            li[0:0] = [0]
            if i:
                li = [num + 2**index for index, num in enumerate(li)]
            s.update(li)
        for i in range(1, N + 1):
            if i in s:
                continue
            else:
                return False
        return True
