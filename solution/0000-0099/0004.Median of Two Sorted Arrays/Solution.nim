proc medianOfTwoSortedArrays(nums1: seq[int], nums2: seq[int]): float =
  let
    m = nums1.len
    n = nums2.len

  proc f(i, j, k: int): int =
    if i >= m:
      return nums2[j + k - 1]
    if j >= n:
      return nums1[i + k - 1]
    if k == 1:
      return min(nums1[i], nums2[j])
    let p = k div 2
    let x = if i + p - 1 < m: nums1[i + p - 1] else: 1 shl 30
    let y = if j + p - 1 < n: nums2[j + p - 1] else: 1 shl 30
    if x < y:
      result = f(i + p, j, k - p)
    else:
      result = f(i, j + p, k - p)

  let
    a = f(0, 0, (m + n + 1) div 2)
    b = f(0, 0, (m + n + 2) div 2)
  result = (a + b) / 2
