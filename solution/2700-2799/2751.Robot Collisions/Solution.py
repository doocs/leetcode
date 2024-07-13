class Solution:
    def survivedRobotsHealths(self, positions: List[int], healths: List[int], directions: str) -> List[int]:
        temp = {x: i for i,x in enumerate(positions)}

        stack = []
        for x in sorted(positions):
            i = temp[x]

            if directions[i] == "R":
                stack.append(i)
            else:
                while stack and healths[i]:
                    j = stack.pop()
                    if healths[i] > healths[j]:
                        healths[j] = 0
                        healths[i] -= 1
                    elif healths[i] < healths[j]:
                        healths[i] = 0
                        healths[j] -= 1
                        stack.append(j)
                    else:
                        healths[i] = healths[j] = 0
                        
        return [item for item in healths if item > 0]
