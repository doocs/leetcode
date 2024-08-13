class Solution {
    func findKthLargest(_ nums: [Int], _ k: Int) -> Int {
        var nums = nums
        let n = nums.count
        return quickSelect(&nums, 0, n - 1, n - k)
    }

    private func quickSelect(_ nums: inout [Int], _ left: Int, _ right: Int, _ k: Int) -> Int {
        if left == right {
            return nums[left]
        }
        
        var i = left - 1
        var j = right + 1
        let pivot = nums[(left + right) / 2]
        
        while i < j {
            repeat { i += 1 } while nums[i] < pivot
            repeat { j -= 1 } while nums[j] > pivot
            if i < j {
                nums.swapAt(i, j)
            }
        }
        
        if j < k {
            return quickSelect(&nums, j + 1, right, k)
        }
        return quickSelect(&nums, left, j, k)
    }
}