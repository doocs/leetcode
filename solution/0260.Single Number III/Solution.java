```java
class Solution {
    public int[] singleNumber(int[] nums) {
        int t = 0;
        for (int num : nums) {
            t ^= num;
        }
        int lastBit = t & -t;
        int t1 = 0, t2 = 0;
        for (int num : nums) {
            if ((num & lastBit) != 0) {
                t1 ^= num;
            } else {
                t2 ^= num;
            }
        }
        return new int[]{t1, t2};
    }
}
```
