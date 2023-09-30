# [2869. Minimum Operations to Collect Elements](https://leetcode.cn/problems/minimum-operations-to-collect-elements/)

[English Version](/solution/2800-2899/2869.Minimum%20Operations%20to%20Collect%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一次操作中，你可以将数组的最后一个元素删除，将该元素添加到一个集合中。</p>

<p>请你返回收集元素&nbsp;<code>1, 2, ..., k</code>&nbsp;需要的&nbsp;<strong>最少操作次数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,1,5,4,2], k = 2
<b>输出：</b>4
<b>解释：</b>4 次操作后，集合中的元素依次添加了 2 ，4 ，5 和 1 。此时集合中包含元素 1 和 2 ，所以答案为 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre><b>输入：</b>nums = [3,1,5,4,2], k = 5
<b>输出：</b>5
<b>解释：</b>5 次操作后，集合中的元素依次添加了 2 ，4 ，5 ，1 和 3 。此时集合中包含元素 1 到 5 ，所以答案为 5 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre><b>输入：</b>nums = [3,2,5,3,1], k = 3
<b>输出：</b>4
<b>解释：</b>4 次操作后，集合中的元素依次添加了 1 ，3 ，5 和 2 。此时集合中包含元素 1 到 3  ，所以答案为 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li>输入保证你可以收集到元素&nbsp;<code>1, 2, ..., k</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(List<Integer> nums, int k) {
        boolean[] isAdded = new boolean[k];
        int n = nums.size();
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (nums.get(i) > k || isAdded[nums.get(i) - 1]) {
                continue;
            }
            isAdded[nums.get(i) - 1] = true;
            count++;
            if (count == k) {
                return n - i;
            }
        }
        return n;
    }
}

```

### **C++**

```cpp

```

### **Go**

```go

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
