# [1486. 数组异或操作](https://leetcode.cn/problems/xor-operation-in-an-array)

[English Version](/solution/1400-1499/1486.XOR%20Operation%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数，<code>n</code> 和 <code>start</code> 。</p>

<p>数组 <code>nums</code> 定义为：<code>nums[i] = start + 2*i</code>（下标从 0 开始）且 <code>n == nums.length</code> 。</p>

<p>请返回 <code>nums</code> 中所有元素按位异或（<strong>XOR</strong>）后得到的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 5, start = 0
<strong>输出：</strong>8
<strong>解释：</strong>数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
     &quot;^&quot; 为按位异或 XOR 运算符。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 4, start = 3
<strong>输出：</strong>8
<strong>解释：</strong>数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 1, start = 7
<strong>输出：</strong>7
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 10, start = 5
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= start &lt;= 1000</code></li>
	<li><code>n == nums.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def xorOperation(self, n: int, start: int) -> int:
        res = 0
        for i in range(n):
            res ^= start + (i << 1)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
  public int xorOperation(int n, int start) {
    int ret = start;
    for (int i = 1; i < n; i++) {
      ret = ret ^ (start + (i << 1));
    }
    return ret;
  }
}
```

### **...**

```

```

<!-- tabs:end -->
