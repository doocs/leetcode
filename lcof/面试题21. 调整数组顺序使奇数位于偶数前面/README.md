# [面试题21. 调整数组顺序使奇数位于偶数前面](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

## 题目描述
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

**示例：**

```
输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
```

**提示：**

- 1 <= nums.length <= 50000
- 1 <= nums[i] <= 10000

## 解法
### Python3
```python
class Solution:
    def exchange(self, nums: List[int]) -> List[int]:
        res = [0 for _ in range(len(nums))]
        p, q = 0, len(nums) - 1
        for e in nums:
            if (e & 1) == 0:
                res[q] = e
                q -= 1
            else:
                res[p] = e
                p += 1
        return res
```

### Java
```java
class Solution {
    public int[] exchange(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int p = 0, q = len - 1;
        for (int e : nums) {
            if ((e & 1) == 0) {
                res[q--] = e;
            } else {
                res[p++] = e;
            }
        }
        return res;
    }
}
```

### ...
```

```
