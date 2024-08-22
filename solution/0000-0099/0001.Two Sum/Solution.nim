import std/enumerate
import std/tables

proc twoSum(nums: seq[int], target: int): seq[int] =
  var d = initTable[int, int]()
  for i, x in nums.pairs():
    let y = target - x
    if d.hasKey(y):
      return @[d[y], i]
    d[x] = i
  return @[]
