import std/[algorithm, sequtils]

proc medianOfTwoSortedArrays(nums1: seq[int], nums2: seq[int]): float =
  if nums1.len > nums2.len:
    return medianOfTwoSortedArrays(nums2, nums1)

  let
    m = nums1.len
    n = nums2.len
    half = (m + n + 1) div 2
  var
    lo = 0
    hi = m

  while lo <= hi:
    let
      i = (lo + hi) div 2
      j = half - i
      maxLeft1 = if i == 0: low(int) else: nums1[i - 1]
      minRight1 = if i == m: high(int) else: nums1[i]
      maxLeft2 = if j == 0: low(int) else: nums2[j - 1]
      minRight2 = if j == n: high(int) else: nums2[j]

    if maxLeft1 <= minRight2 and maxLeft2 <= minRight1:
      if (m + n) mod 2 == 0:
        let
          leftMax = if maxLeft1 > maxLeft2: maxLeft1 else: maxLeft2
          rightMin = if minRight1 < minRight2: minRight1 else: minRight2
        result = (float(leftMax) + float(rightMin)) / 2
      else:
        let leftMax = if maxLeft1 > maxLeft2: maxLeft1 else: maxLeft2
        result = float(leftMax)
      return
    elif maxLeft1 > minRight2:
      hi = i - 1
    else:
      lo = i + 1

# Driver Code

# var
#   arrA: seq[int] = @[1, 2]
#   arrB: seq[int] = @[3, 4, 5]
# echo medianOfTwoSortedArrays(arrA, arrB)

