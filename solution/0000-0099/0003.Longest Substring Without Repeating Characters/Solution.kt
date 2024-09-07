class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var char_set = BooleanArray(128)
        var left = 0
        var ans = 0
        s.forEachIndexed { right, c ->
            while (char_set[c.code]) {
                char_set[s[left].code] = false
                left++
            }
            char_set[c.code] = true
            ans = Math.max(ans, right - left + 1)
        }
        return ans
    }
}
