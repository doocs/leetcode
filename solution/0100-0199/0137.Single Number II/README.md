# [137. 只出现一次的数字 II](https://leetcode-cn.com/problems/single-number-ii)

[English Version](/solution/0100-0199/0137.Single%20Number%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次 。</strong>请你找出并返回那个只出现了一次的元素。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,3,2]
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,0,1,0,1,99]
<strong>输出：</strong>99
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
	<li><code>nums</code> 中，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次</strong></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

统计所有数字每个位中 1 出现的次数，对于某个位，1 出现的次数一定是 3 的倍数 +1 或 0。对这个数 %3 得到的结果就是那个出现一次的数字在该位上的值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        bits = [0] * 32
        for num in nums:
            for i in range(32):
                bits[i] += (num & 1)
                num >>= 1
        res = 0
        for i in range(32):
            if bits[i] % 3 != 0:
                res |= (1 << i)
        # 如果为负数，先将 0-32 位取反（即 res ^ 0xffffffff ），再将所有位取反（即 ~ ）
        return res if bits[31] % 3 == 0 else ~(res ^ 0xffffffff)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; ++i) {
                bits[i] += (num & 1);
                num >>= 1;
            }
        }

        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (bits[i] % 3 == 1) {
                res |= (1 << i);
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
