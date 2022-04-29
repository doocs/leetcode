# [面试题 17.16. 按摩师](https://leetcode.cn/problems/the-masseuse-lcci)

[English Version](/lcci/17.16.The%20Masseuse/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。</p>

<p><strong>注意：</strong>本题相对原题稍作改动</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong> [1,2,3,1]
<strong>输出：</strong> 4
<strong>解释：</strong> 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong> [2,7,9,3,1]
<strong>输出：</strong> 12
<strong>解释：</strong> 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong> [2,1,4,5,3,1,1,3]
<strong>输出：</strong> 12
<strong>解释：</strong> 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

递推求解。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def massage(self, nums: List[int]) -> int:
        if not nums:
            return 0
        n = len(nums)
        if n < 2:
            return nums[0]
        a, b = nums[0], max(nums[0], nums[1])
        res = b
        for i in range(2, n):
            res = max(a + nums[i], b)
            a, b = b, res
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int massage(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return n == 0 ? 0 : nums[0];
        }
        int a = nums[0], b = Math.max(nums[0], nums[1]);
        int res = b;
        for (int i = 2; i < n; ++i) {
            res = Math.max(a + nums[i], b);
            a = b;
            b = res;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
