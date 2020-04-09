# [面试题56 - I. 数组中数字出现的次数](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/)

## 题目描述
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

**示例 1：**

```
输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
```

**示例 2：**

```
输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
```

**限制：**

- `2 <= nums <= 10000`

## 解法
### Python3
```python
class Solution:
    def singleNumbers(self, nums: List[int]) -> List[int]:
        xor_res = 0
        for num in nums:
            xor_res ^= num
        pos = 0
        while (xor_res & 1) == 0:
            pos += 1
            xor_res >>= 1
        
        a = b = 0
        for num in nums:
            t = num >> pos
            if (t & 1) == 0:
                a ^= num
            else:
                b ^= num
        return [a, b]
```

### Java
```java
class Solution {
    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int pos = 0;
        while ((xor & 1) == 0) {
            ++pos;
            xor >>= 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            int t = num >> pos;
            if ((t & 1) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[] {a, b};
    }
}
```

### ...
```

```
