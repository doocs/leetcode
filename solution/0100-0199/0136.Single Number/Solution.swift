
``` swift
class Solution {
  func singleNumber(_ nums: [Int]) -> Int {
    var a = nums.sorted()
    // print(a);
    var n = a.count
    for i in stride(from: 0, through: n - 1, by: 2) {
      if i == n - 1 {

        return a[i]
      }

      else if a[i] != a[i + 1] {

        return a[i]
      }

    }
    return 0
  }
}
```