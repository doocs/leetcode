# [2612. 最少翻转操作数](https://leetcode.cn/problems/minimum-reverse-operations)

[English Version](/solution/2600-2699/2612.Minimum%20Reverse%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个在范围 <code>[0, n - 1]</code>&nbsp;以内的整数&nbsp;<code>p</code>&nbsp;，它们表示一个长度为 <code>n</code> 且下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>arr</code>&nbsp;，数组中除了下标为&nbsp;<code>p</code>&nbsp;处是 <code>1</code>&nbsp;以外，其他所有数都是 <code>0</code>&nbsp;。</p>

<p>同时给你一个整数数组&nbsp;<code>banned</code>&nbsp;，它包含数组中的一些位置。<code>banned</code>&nbsp;中第&nbsp;<strong>i</strong>&nbsp;个位置表示&nbsp;<code>arr[banned[i]] = 0</code>&nbsp;，题目保证&nbsp;<code>banned[i] != p</code>&nbsp;。</p>

<p>你可以对 <code>arr</code>&nbsp;进行 <strong>若干次</strong>&nbsp;操作。一次操作中，你选择大小为 <code>k</code>&nbsp;的一个 <strong>子数组</strong>&nbsp;，并将它 <b>翻转</b>&nbsp;。在任何一次翻转操作后，你都需要确保 <code>arr</code>&nbsp;中唯一的 <code>1</code>&nbsp;不会到达任何 <code>banned</code>&nbsp;中的位置。换句话说，<code>arr[banned[i]]</code>&nbsp;始终&nbsp;<strong>保持</strong>&nbsp;<code>0</code>&nbsp;。</p>

<p>请你返回一个数组&nbsp;<code>ans</code>&nbsp;，对于<em>&nbsp;</em><code>[0, n - 1]</code>&nbsp;之间的任意下标&nbsp;<code>i</code>&nbsp;，<code>ans[i]</code>&nbsp;是将&nbsp;<code>1</code>&nbsp;放到位置&nbsp;<code>i</code>&nbsp;处的&nbsp;<strong>最少</strong>&nbsp;翻转操作次数，如果无法放到位置&nbsp;<code>i</code>&nbsp;处，此数为&nbsp;<code>-1</code>&nbsp;。</p>

<ul>
	<li><strong>子数组</strong>&nbsp;指的是一个数组里一段连续 <strong>非空</strong>&nbsp;的元素序列。</li>
	<li>对于所有的 <code>i</code>&nbsp;，<code>ans[i]</code>&nbsp;相互之间独立计算。</li>
	<li>将一个数组中的元素 <strong>翻转</strong> 指的是将数组中的值变成 <strong>相反顺序</strong>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 4, p = 0, banned = [1,2], k = 4
<b>输出：</b>[0,-1,-1,1]
<b>解释：</b><code>k = 4，所以只有一种可行的翻转操作，就是将整个数组翻转。一开始 </code>1<strong> </strong>在位置 0 处，所以将它翻转到位置 0 处需要的操作数为 0 。
我们不能将 1 翻转到 banned 中的位置，所以位置 1 和 2 处的答案都是 -1 。
通过一次翻转操作，可以将 1 放到位置 3 处，所以位置 3 的答案是 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 5, p = 0, banned = [2,4], k = 3
<b>输出：</b>[0,-1,-1,-1,-1]
<b>解释：</b>这个例子中 1 一开始在位置 0 处，所以此下标的答案为 0 。
翻转的子数组长度为 k = 3 ，1 此时在位置 0 处，所以我们可以翻转子数组 [0, 2]，但翻转后的下标 2 在 banned 中，所以不能执行此操作。
由于 1 没法离开位置 0 ，所以其他位置的答案都是 -1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 4, p = 2, banned = [0,1,3], k = 1
<b>输出：</b>[-1,-1,0,-1]
<b>解释：</b>这个例子中，我们只能对长度为 1 的子数组执行翻转操作，所以 1 无法离开初始位置。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= p &lt;= n - 1</code></li>
	<li><code>0 &lt;= banned.length &lt;= n - 1</code></li>
	<li><code>0 &lt;= banned[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= k &lt;= n&nbsp;</code></li>
	<li><code>banned[i] != p</code></li>
	<li><code>banned</code>&nbsp;中的值 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedSet


class Solution:
    def minReverseOperations(self, n: int, p: int, banned: List[int], k: int) -> List[int]:
        ans = [-1] * n
        ans[p] = 0
        ts = [SortedSet() for _ in range(2)]
        for i in range(n):
            ts[i % 2].add(i)
        ts[p % 2].remove(p)
        for i in banned:
            ts[i % 2].remove(i)
        q = deque([p])
        while q:
            x = q.popleft()
            i = abs(x - k + 1)
            j = ts[i % 2].bisect_left(i)
            while j < len(ts[i % 2]) and ts[i % 2][j] < n - abs(n - x - k):
                q.append(ts[i % 2][j])
                ans[ts[i % 2][j]] = ans[x] + 1
                ts[i % 2].remove(ts[i % 2][j])
                j = ts[i % 2].bisect_left(i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] ans = new int[n];
        TreeSet<Integer>[] ts = new TreeSet[] {new TreeSet<>(), new TreeSet<>()};
        for (int i = 0; i < n; ++i) {
            ts[i % 2].add(i);
            ans[i] = i == p ? 0 : -1;
        }
        ts[p % 2].remove(p);
        for (int i : banned) {
            ts[i % 2].remove(i);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(p);
        while (!q.isEmpty()) {
            int x = q.poll();
            int i = Math.abs(x - k + 1);
            Integer j = ts[i % 2].ceiling(i);
            while (j != null && j < n - Math.abs(n - x - k)) {
                q.offer(j);
                ans[j] = ans[x] + 1;
                ts[i % 2].remove(j);
                j = ts[i % 2].higher(j);
            }
        }
        return ans;
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
