class Solution:
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        cnt = Counter(hand)
        for v in sorted(hand):
            if cnt[v]:
                for x in range(v, v + groupSize):
                    if cnt[x] == 0:
                        return False
                    cnt[x] -= 1
                    if cnt[x] == 0:
                        cnt.pop(x)
        return True
