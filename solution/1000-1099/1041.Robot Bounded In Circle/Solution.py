class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        dicertion = [0] * 4
        cur = 0
        for i in range(len(instructions)):
            if instructions[i] == 'L':
                cur = cur + 1 if cur < 3 else 0
            elif instructions[i] == 'R':
                cur = cur - 1 if cur > 0 else 3
            else:
                dicertion[cur] += 1
        return cur != 0 or (dicertion[0] == dicertion[2] and dicertion[1] == dicertion[3])