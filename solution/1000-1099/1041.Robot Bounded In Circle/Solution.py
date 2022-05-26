class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        cur, direction = 0, [0] * 4
        for ins in instructions:
            if ins == 'L':
                cur = (cur + 1) % 4
            elif ins == 'R':
                cur = (cur + 3) % 4
            else:
                direction[cur] += 1
        return cur != 0 or (
            direction[0] == direction[2] and direction[1] == direction[3]
        )
