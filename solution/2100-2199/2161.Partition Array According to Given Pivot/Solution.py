class Solution:
    def pivotArray(self, nums: List[int], pivot: int) -> List[int]:
        a, b, c = [], [], []
        for x in nums:
            if x < pivot:
                a.append(x)
            elif x == pivot:
                b.append(x)
            else:
                c.append(x)
        return a + b + c
