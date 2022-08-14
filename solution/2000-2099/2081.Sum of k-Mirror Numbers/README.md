# [2081. k 镜像数字的和](https://leetcode.cn/problems/sum-of-k-mirror-numbers)

[English Version](/solution/2000-2099/2081.Sum%20of%20k-Mirror%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个 <strong>k 镜像数字</strong>&nbsp;指的是一个在十进制和 k 进制下从前往后读和从后往前读都一样的&nbsp;<strong>没有前导 0</strong>&nbsp;的&nbsp;<strong>正</strong>&nbsp;整数。</p>

<ul>
	<li>比方说，<code>9</code>&nbsp;是一个 2 镜像数字。<code>9</code>&nbsp;在十进制下为&nbsp;<code>9</code>&nbsp;，二进制下为&nbsp;<code>1001</code>&nbsp;，两者从前往后读和从后往前读都一样。</li>
	<li>相反地，<code>4</code>&nbsp;不是一个 2 镜像数字。<code>4</code>&nbsp;在二进制下为&nbsp;<code>100</code>&nbsp;，从前往后和从后往前读不相同。</li>
</ul>

<p>给你进制&nbsp;<code>k</code>&nbsp;和一个数字&nbsp;<code>n</code>&nbsp;，请你返回 k 镜像数字中 <strong>最小</strong> 的 <code>n</code>&nbsp;个数 <strong>之和</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre><b>输入：</b>k = 2, n = 5
<b>输出：</b>25
<strong>解释：
</strong>最小的 5 个 2 镜像数字和它们的二进制表示如下：
  十进制       二进制
    1          1
    3          11
    5          101
    7          111
    9          1001
它们的和为 1 + 3 + 5 + 7 + 9 = 25 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>k = 3, n = 7
<b>输出：</b>499
<strong>解释：
</strong>7 个最小的 3 镜像数字和它们的三进制表示如下：
  十进制       三进制
    1          1
    2          2
    4          11
    8          22
    121        11111
    151        12121
    212        21212
它们的和为 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>k = 7, n = 17
<b>输出：</b>20379000
<b>解释：</b>17 个最小的 7 镜像数字分别为：
1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
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
    public long kMirror(int k, int n) {
        long ans = 0;
        for (int l = 1; ; ++l) {
            int x = (int) Math.pow(10, (l - 1) / 2);
            int y = (int) Math.pow(10, (l + 1) / 2);
            for (int i = x; i < y; i++) {
                long v = i;
                for (int j = l % 2 == 0 ? i : i / 10; j > 0; j /= 10) {
                    v = v * 10 + j % 10;
                }
                String ss = Long.toString(v, k);
                if (check(ss.toCharArray())) {
                    ans += v;
                    if (--n == 0) {
                        return ans;
                    }
                }
            }
        }
    }


    private boolean check(char[] c) {
        for (int i = 0, j = c.length - 1; i < j; i++, j--) {
            if (c[i] != c[j]) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
