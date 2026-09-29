class Solution:
    def orchestraLayout(self, num: int, xPos: int, yPos: int) -> int:
        layer = min(xPos, yPos, num - 1 - xPos, num - 1 - yPos)
        side = num - 2 * layer
        start = (4 * (num - 1) * layer - 4 * layer * (layer - 1)) % 9 + 1

        if xPos == layer:
            offset = yPos - layer
        elif yPos == num - layer - 1:
            offset = side - 1 + xPos - layer
        elif xPos == num - layer - 1:
            offset = 2 * side - 2 + num - layer - 1 - yPos
        else:
            offset = 3 * side - 3 + num - layer - 1 - xPos

        return (start - 1 + offset) % 9 + 1
