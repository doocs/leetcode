class Solution {
    func search(_ arr: [Int], _ target: Int) -> Int {
        var l = 0
        var r = arr.count - 1

        while arr[l] == arr[r] && l < r {
            r -= 1
        }

        while l < r {
            let mid = (l + r) >> 1
            if arr[mid] > arr[r] {
                if arr[l] <= target && target <= arr[mid] {
                    r = mid
                } else {
                    l = mid + 1
                }
            } else if arr[mid] < arr[r] {
                if arr[mid] < target && target <= arr[r] {
                    l = mid + 1
                } else {
                    r = mid
                }
            } else {
                r -= 1
            }
        }

        return arr[l] == target ? l : -1
    }
}