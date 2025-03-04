class Solution:
    def resultArray(self, nums: List[int]) -> List[int]:
        arr1 = [nums[0]]
        arr2 = [nums[1]]
        sl1 = SortedList(arr1)
        sl2 = SortedList(arr2)
        for x in nums[2:]:
            i = sl1.bisect_right(x)
            j = sl2.bisect_right(x)
            if len(sl1) - i > len(sl2) - j:
                arr1.append(x)
                sl1.add(x)
            elif len(sl1) - i < len(sl2) - j:
                arr2.append(x)
                sl2.add(x)
            elif len(sl1) <= len(sl2):
                arr1.append(x)
                sl1.add(x)
            else:
                arr2.append(x)
                sl2.add(x)
        return arr1 + arr2
