class Solution {
    private var nums: [Int] = []
    private var temp: [Int] = []

    func reversePairs(_ nums: [Int]) -> Int {
        self.nums = nums
        let n = nums.count
        self.temp = [Int](repeating: 0, count: n)
        return mergeSort(0, n - 1)
    }

    private func mergeSort(_ left: Int, _ right: Int) -> Int {
        if left >= right {
            return 0
        }
        let mid = (left + right) / 2
        var count = mergeSort(left, mid) + mergeSort(mid + 1, right)
        var i = left
        var j = mid + 1
        var k = left
        
        while i <= mid && j <= right {
            if nums[i] <= nums[j] {
                temp[k] = nums[i]
                i += 1
            } else {
                count += mid - i + 1
                temp[k] = nums[j]
                j += 1
            }
            k += 1
        }
        
        while i <= mid {
            temp[k] = nums[i]
            i += 1
            k += 1
        }
        
        while j <= right {
            temp[k] = nums[j]
            j += 1
            k += 1
        }
        
        for i in left...right {
            nums[i] = temp[i]
        }
        
        return count
    }
}