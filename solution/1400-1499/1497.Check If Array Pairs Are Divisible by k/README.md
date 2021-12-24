# [1497. 检查数组对是否可以被 k 整除](https://leetcode-cn.com/problems/check-if-array-pairs-are-divisible-by-k)

[English Version](/solution/1400-1499/1497.Check%20If%20Array%20Pairs%20Are%20Divisible%20by%20k/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code> 和一个整数 <code>k</code> ，其中数组长度是偶数，值为 <code>n</code> 。</p>

<p>现在需要把数组恰好分成 <code>n /&nbsp;2</code> 对，以使每对数字的和都能够被 <code>k</code> 整除。</p>

<p>如果存在这样的分法，请返回 <em>True</em> ；否则，返回 <em>False</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3,4,5,10,6,7,8,9], k = 5
<strong>输出：</strong>true
<strong>解释：</strong>划分后的数字对为 (1,9),(2,8),(3,7),(4,6) 以及 (5,10) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3,4,5,6], k = 7
<strong>输出：</strong>true
<strong>解释：</strong>划分后的数字对为 (1,6),(2,5) 以及 (3,4) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3,4,5,6], k = 10
<strong>输出：</strong>false
<strong>解释：</strong>无法在将数组中的数字分为三对的同时满足每对数字和能够被 10 整除的条件。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [-10,10], k = 2
<strong>输出：</strong>true
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [-1,1,-2,2,-3,3,-4,4], k = 3
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>arr.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>n</code> 为偶数</li>
	<li><code>-10^9 &lt;= arr[i] &lt;= 10^9</code></li>
	<li><code>1 &lt;= k &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPathCrossing(self, path: str) -> bool:
        x = y = 0
        visited = set()
        visited.add('0.0')
        for c in path:
            if c == 'N':
                y += 1
            elif c == 'S':
                y -= 1
            elif c == 'E':
                x += 1
            else:
                x -= 1
            pos = f'{x}.{y}'
            if pos in visited:
                return True
            visited.add(pos)
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPathCrossing(String path) {
        Set<String> visited = new HashSet<>();
        visited.add("0.0");
        int x = 0, y = 0;
        for (int i = 0; i < path.length(); ++i) {
            char c = path.charAt(i);
            if (c == 'N') {
                ++y;
            } else if (c == 'S') {
                --y;
            } else if (c == 'E') {
                ++x;
            } else {
                --x;
            }
            String pos = x + "." + y;
            if (visited.contains(pos)) {
                return true;
            }
            visited.add(pos);
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
