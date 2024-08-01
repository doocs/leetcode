class Solution {
  fun maximumRemovals(s: String, p: String, removable: IntArray): Int {
      val strLen = s.length
      val subLen = p.length

      fun isSub(k: Int): Boolean {
          val removed = removable.sliceArray(0 ..< k).toHashSet()

          var subIndex = 0
          for (strIndex in 0 ..< strLen) {
              if (s[strIndex] == p[subIndex] && !removed.contains(strIndex)) {
                  ++subIndex
                  if (subIndex >= subLen) {
                      break
                  }
              }
          }

          return subIndex == subLen
      }

      var left = 0
      var right = removable.size

      while (left < right) {
          val middle = (left + right) / 2
          if (isSub(middle + 1)) {
              left = middle + 1
          } else {
              right = middle
          }
      }
      return left
  }
}
