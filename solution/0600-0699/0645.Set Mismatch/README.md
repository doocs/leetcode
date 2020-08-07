# [645. 错误的集合](https://leetcode-cn.com/problems/set-mismatch)

[English Version](/solution/0600-0699/0645.Set%20Mismatch/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>集合 <code>S</code> 包含从1到&nbsp;<code>n</code>&nbsp;的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。</p>

<p>给定一个数组 <code>nums</code> 代表了集合 <code>S</code> 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,2,4]
<strong>输出:</strong> [2,3]
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>给定数组的长度范围是&nbsp;[2, 10000]。</li>
	<li>给定的数组是无序的。</li>
</ol>



## 解法
<!-- 这里可写通用的实现逻辑 -->

首先使用 1 到 n 的所有数字做异或运算，然后再与数组中的所有数字异或，得到的值就是缺失数字与重复的数字异或的结果。

接着计算中这个值中其中一个非零的位 pos。然后 pos 位是否为 1，将 nums 数组的元素分成两部分，分别异或；接着将 `1~n` 的元素也分成两部分，分别异或。得到的两部分结果分别为 a,b，即是缺失数字与重复数字。

最后判断数组中是否存在 a 或 b，若存在 a，说明重复数字是 a，返回 `[a,b]`，否则返回 `[b,a]`。

<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        res = 0
        for num in nums:
            res ^= num
        for i in range(1, len(nums) + 1):
            res ^= i
        pos = 0
        while (res & 1) == 0:
            res >>= 1
            pos += 1
        a = b = 0
        for num in nums:
            if ((num >> pos) & 1) == 0:
                a ^= num
            else:
                b ^= num
        for i in range(1, len(nums) + 1):
            if ((i >> pos) & 1) == 0:
                a ^= i
            else:
                b ^= i
        for num in nums:
            if num == a:
                return [a, b]
        return [b, a]
```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        for (int i = 1, n = nums.length; i < n + 1; ++i) {
            res ^= i;
        }
        int pos = 0;
        while ((res & 1) == 0) {
            res >>= 1;
            ++pos;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if (((num >> pos) & 1) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        for (int i = 1, n = nums.length; i < n + 1; ++i) {
            if (((i >> pos) & 1) == 0) {
                a ^= i;
            } else {
                b ^= i;
            }
        }
        for (int num : nums) {
            if (num == a) {
                return new int[]{a, b};
            }
        }
        return new int[]{b, a};
    }
}
```

### **...**
```

```

<!-- tabs:end -->