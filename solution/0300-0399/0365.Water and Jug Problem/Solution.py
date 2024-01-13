class Solution:
    def canMeasureWater(
        self, jug1Capacity: int, jug2Capacity: int, targetCapacity: int
    ) -> bool:
        stk, seen = [], set()
        stk.append([0, 0])

        def get_hash(nums):
            return nums[0] * 10000006 + nums[1]

        while stk:
            if get_hash(stk[-1]) in seen:
                stk.pop()
                continue
            seen.add(get_hash(stk[-1]))
            cur = stk.pop()
            cur1, cur2 = cur[0], cur[1]
            if (
                cur1 == targetCapacity
                or cur2 == targetCapacity
                or cur1 + cur2 == targetCapacity
            ):
                return True
            stk.append([jug1Capacity, cur2])
            stk.append([0, cur2])
            stk.append([cur1, jug2Capacity])
            stk.append([cur1, 0])
            if cur1 + cur2 > jug1Capacity:
                stk.append([jug1Capacity, cur2 - jug1Capacity + cur1])
            else:
                stk.append([cur1 + cur2, 0])
            if cur1 + cur2 > jug2Capacity:
                stk.append([cur1 - jug2Capacity + cur2, jug2Capacity])
            else:
                stk.append([0, cur1 + cur2])
        return False
