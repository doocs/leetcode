import std/[algorithm, sequtils]

proc medianOfTwoSortedArrays(nums1: seq[int], nums2: seq[int]): float =
  var
    fullList: seq[int] = concat(nums1, nums2)
    value: int = fullList.len div 2

  fullList.sort()

  if fullList.len mod 2 == 0:
    result = (fullList[value - 1] + fullList[value]) / 2
  else:
    result = fullList[value].toFloat()

# Driver Code

# var
#   arrA: seq[int] = @[1, 2]
#   arrB: seq[int] = @[3, 4, 5]
# echo medianOfTwoSortedArrays(arrA, arrB)

