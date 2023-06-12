# [2735. 收集巧克力](https://leetcode.cn/problems/collecting-chocolates)

[English Version](/solution/2700-2799/2735.Collecting%20Chocolates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 、下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，表示收集不同巧克力的成本。每个巧克力都对应一个不同的类型，最初，位于下标 <code>i</code> 的巧克力就对应第 <code>i</code> 个类型。</p>

<p>在一步操作中，你可以用成本 <code>x</code> 执行下述行为：</p>

<ul>
	<li>同时对于所有下标 <code>0 &lt;= i &lt; n - 1</code> 进行以下操作， 将下标 <code>i</code> 处的巧克力的类型更改为下标 <code>(i + 1)</code> 处的巧克力对应的类型。如果 <code>i == n - 1</code> ，则该巧克力的类型将会变更为下标 <code>0</code> 处巧克力对应的类型。</li>
</ul>

<p>假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [20,1,15], x = 5
<strong>输出：</strong>13
<strong>解释：</strong>最开始，巧克力的类型分别是 [0,1,2] 。我们可以用成本 1 购买第 1 个类型的巧克力。
接着，我们用成本 5 执行一次操作，巧克力的类型变更为 [2,0,1] 。我们可以用成本 1 购买第 0 个类型的巧克力。
然后，我们用成本 5 执行一次操作，巧克力的类型变更为 [1,2,0] 。我们可以用成本 1 购买第 2 个类型的巧克力。
因此，收集所有类型的巧克力需要的总成本是 (1 + 5 + 1 + 5 + 1) = 13 。可以证明这是一种最优方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3], x = 4
<strong>输出：</strong>6
<strong>解释：</strong>我们将会按最初的成本收集全部三个类型的巧克力，而不需执行任何操作。因此，收集所有类型的巧克力需要的总成本是 1 + 2 + 3 = 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

### 方法1：暴力

成本分为移动成本 + 收集成本

枚举最大移动次数`len`。

若移动次数为`len`，移动成本`= len * x`

对应下标为`i` 的巧克力，收集成本为`min[i][(i + len) % n]` ，其中`n` 为巧克力个数，`min[i][j]` 表示`nums[i-j]` 的最小值（可$$O(n^2)$$）预处理。

最终结果$$result = \sum_{i = 0}^{n - 1} min[i][(i + len)\mod n] + len * x$$ 



时间复杂度：$$O(n^2)$$

空间复杂度：$$O(n^2)$$ 

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[][] min = new int[n][n];
        for (int i = 0; i < n; i++) {
            int w = 0x3f3f3f3f;
            for (int j = i; j < n; j++) {
                w = Math.min(w, nums[j]);
                min[i][j] = w;
            }
        }
        
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                int l = j - i;
                if (l < 0) {
                    sum += Math.min(min[0][j], min[n + l][n - 1]);
                } else {
                    sum += min[l][j];
                }
            }
            res = Math.min(res, sum + x * 1L * i);
        }
        return res;
    }
}
```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
