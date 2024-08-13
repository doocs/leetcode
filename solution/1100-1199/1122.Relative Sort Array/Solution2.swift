class Solution {
    func relativeSortArray(_ arr1: [Int], _ arr2: [Int]) -> [Int] {
        var cnt = [Int](repeating: 0, count: 1001)
        for x in arr1 {
            cnt[x] += 1
        }

        guard let mi = arr1.min(), let mx = arr1.max() else {
            return []
        }

        var ans = [Int]()
        for x in arr2 {
            while cnt[x] > 0 {
                ans.append(x)
                cnt[x] -= 1
            }
        }

        for x in mi...mx {
            while cnt[x] > 0 {
                ans.append(x)
                cnt[x] -= 1
            }
        }

        return ans
    }
}
