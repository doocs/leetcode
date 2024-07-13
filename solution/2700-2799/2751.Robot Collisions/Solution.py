class Solution:
    def survivedRobotsHealths(
        self, positions: List[int], healths: List[int], directions: str
    ) -> List[int]:
        n = len(positions)
        indices = list(range(n))
        stack = []

        indices.sort(key=lambda i: positions[i])

        for currentIndex in indices:
            if directions[currentIndex] == "R":
                stack.append(currentIndex)
            else:
                while stack and healths[currentIndex] > 0:
                    topIndex = stack.pop()

                    if healths[topIndex] > healths[currentIndex]:
                        healths[topIndex] -= 1
                        healths[currentIndex] = 0
                        stack.append(topIndex)
                    elif healths[topIndex] < healths[currentIndex]:
                        healths[currentIndex] -= 1
                        healths[topIndex] = 0
                    else:
                        healths[currentIndex] = 0
                        healths[topIndex] = 0

        result = [health for health in healths if health > 0]
        return result
