import scala.collection.mutable

object Solution {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    var map = new mutable.HashMap[Int, Int]()
    for (i <- 0 to nums.length) {
      if (map.contains(target - nums(i))) {
        return Array(map(target - nums(i)), i)
      } else {
        map += (nums(i) -> i)
      }
    }
    Array(0, 0)
  }
}
