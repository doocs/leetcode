class Solution:
    def survivedRobotsHealths(self, positions, healths, directions):
        idx = sorted(range(len(positions)), key=lambda i: positions[i])
        stk = []

        for i in idx:
            if directions[i] == "R":
                stk.append(i)
                continue

            while stk and healths[i]:
                j = stk[-1]
                if healths[j] > healths[i]:
                    healths[j] -= 1
                    healths[i] = 0
                elif healths[j] < healths[i]:
                    healths[i] -= 1
                    healths[j] = 0
                    stk.pop()
                else:
                    healths[i] = healths[j] = 0
                    stk.pop()
                    break

        return [h for h in healths if h > 0]
