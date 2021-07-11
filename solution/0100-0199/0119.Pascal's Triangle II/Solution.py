class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        def makePascal(prevArr):
            if len(prevArr) == 0:
                return [1]
            elif len(prevArr) == 1:
                return [1, 1]
            else:
                NewArr = [0] * (len(prevArr) + 1)
                NewArr[0], NewArr[-1] = 1, 1
                for i in range(len(prevArr) - 1):
                    NewArr[i + 1] = prevArr[i] + prevArr[i + 1]
                return NewArr

        temp = []
        Pascal = []
        for i in range(rowIndex + 1):
            temp = makePascal(temp)
            Pascal.append(temp)
        return Pascal[rowIndex]
