# [2317. 操作后的最大异或和](https://leetcode.cn/problems/maximum-xor-after-operations)

[English Version](/solution/2300-2399/2317.Maximum%20XOR%20After%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。一次操作中，选择 <strong>任意</strong>&nbsp;非负整数&nbsp;<code>x</code>&nbsp;和一个下标&nbsp;<code>i</code>&nbsp;，<strong>更新</strong>&nbsp;<code>nums[i]</code>&nbsp;为&nbsp;<code>nums[i] AND (nums[i] XOR x)</code>&nbsp;。</p>

<p>注意，<code>AND</code>&nbsp;是逐位与运算，<code>XOR</code>&nbsp;是逐位异或运算。</p>

<p>请你执行 <strong>任意次</strong>&nbsp;更新操作，并返回&nbsp;<code>nums</code>&nbsp;中所有元素&nbsp;<strong>最大</strong>&nbsp;逐位异或和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,2,4,6]
<b>输出：</b>7
<b>解释：</b>选择 x = 4 和 i = 3 进行操作，num[3] = 6 AND (6 XOR 4) = 6 AND 2 = 2 。
现在，nums = [3, 2, 4, 2] 且所有元素逐位异或得到 3 XOR 2 XOR 4 XOR 2 = 7 。
可知 7 是能得到的最大逐位异或和。
注意，其他操作可能也能得到逐位异或和 7 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,9,2]
<b>输出：</b>11
<b>解释：</b>执行 0 次操作。
所有元素的逐位异或和为 1 XOR 2 XOR 3 XOR 9 XOR 2 = 11 。
可知 11 是能得到的最大逐位异或和。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

在一次操作中，我们可以把 `nums[i]` 更新为 `nums[i] AND (nums[i] XOR x)`。由于 $x$ 是任意非负整数，因此 $nums[i] \oplus x$ 的结果是一个任意值，再与 `nums[i]` 逐位与运算，可以把 `nums[i]` 的二进制表示中的若干位 $1$ 变为 $0$。

而题目中要获取的是 `nums` 所有元素的最大逐位异或和，对于一个二进制位，只要在 `nums` 中存在一个元素对应的二进制位为 $1$，那么这个二进制位对于最大逐位异或和的贡献就是 $1$。因此答案就是 `nums` 中所有元素的逐位或运算的结果。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumXOR(self, nums: List[int]) -> int:
        return reduce(or_, nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans |= x;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumXOR(vector<int>& nums) {
        int ans = 0;
        for (int& x : nums) {
            ans |= x;
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumXOR(nums []int) (ans int) {
	for _, x := range nums {
		ans |= x
	}
	return
}
```

### **TypeScript**

```ts
function maximumXOR(nums: number[]): number {
    let ans = 0;
    for (const x of nums) {
        ans |= x;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
