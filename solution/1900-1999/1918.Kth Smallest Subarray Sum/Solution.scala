object Solution {
    def kthSmallestSubarraySum(nums: Array[Int], k: Int): Int = {
        var l = Int.MaxValue
        var r = 0
        
        for (x <- nums) {
            l = l.min(x)
            r += x
        }
        
        def f(s: Int): Int = {
            var cnt = 0
            var t = 0
            var j = 0
            
            for (i <- nums.indices) {
                t += nums(i)
                while (t > s) {
                    t -= nums(j)
                    j += 1
                }
                cnt += i - j + 1
            }
            cnt
        }
        
        while (l < r) {
            val mid = (l + r) / 2
            if (f(mid) >= k) r = mid
            else l = mid + 1
        }
        l
    }
}
