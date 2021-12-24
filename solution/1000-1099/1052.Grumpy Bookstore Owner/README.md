# [1052. 爱生气的书店老板](https://leetcode-cn.com/problems/grumpy-bookstore-owner)

[English Version](/solution/1000-1099/1052.Grumpy%20Bookstore%20Owner/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>今天，书店老板有一家店打算试营业&nbsp;<code>customers.length</code>&nbsp;分钟。每分钟都有一些顾客（<code>customers[i]</code>）会进入书店，所有这些顾客都会在那一分钟结束后离开。</p>

<p>在某些时候，书店老板会生气。 如果书店老板在第 <code>i</code> 分钟生气，那么 <code>grumpy[i] = 1</code>，否则 <code>grumpy[i] = 0</code>。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。</p>

<p>书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续&nbsp;<code>X</code> 分钟不生气，但却只能使用一次。</p>

<p>请你返回这一天营业下来，最多有多少客户能够感到满意的数量。<br>
&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
<strong>输出：</strong>16
<strong>解释：
</strong>书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= X &lt;=&nbsp;customers.length ==&nbsp;grumpy.length &lt;= 20000</code></li>
	<li><code>0 &lt;=&nbsp;customers[i] &lt;= 1000</code></li>
	<li><code>0 &lt;=&nbsp;grumpy[i] &lt;= 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

- 用 `s` 累计不使用秘密技巧时，满意的顾客数；
- 用 `t` 计算大小为 `X` 的滑动窗口最多增加的满意的顾客数；
- 结果即为 `s+t`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSatisfied(self, customers: List[int], grumpy: List[int], X: int) -> int:
        # 用s累计不使用秘密技巧时，满意的顾客数
        # 用t计算大小为X的滑动窗口最多增加的满意的顾客数
        # 结果即为s+t
        s = t = 0
        win, n = 0, len(customers)
        for i in range(n):
            if grumpy[i] == 0:
                s += customers[i]
            else:
                win += customers[i]
            if i >= X and grumpy[i - X] == 1:
                win -= customers[i - X]
            # 求滑动窗口的最大值
            t = max(t, win)
        return s + t
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        // 用s累计不使用秘密技巧时，满意的顾客数
        // 用t计算大小为X的滑动窗口最多增加的满意的顾客数
        // 结果即为s+t
        int s = 0, t = 0;
        for (int i = 0, win = 0, n = customers.length; i < n; ++i) {
            if (grumpy[i] == 0) {
                s += customers[i];
            } else {
                win += customers[i];
            }
            if (i >= X && grumpy[i - X] == 1) {
                win -= customers[i - X];
            }
            // 求滑动窗口的最大值
            t = Math.max(t, win);
        }
        return s + t;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
