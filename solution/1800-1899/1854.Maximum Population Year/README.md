# [1854. 人口最多的年份](https://leetcode-cn.com/problems/maximum-population-year)

[English Version](/solution/1800-1899/1854.Maximum%20Population%20Year/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>logs</code> ，其中每个 <code>logs[i] = [birth<sub>i</sub>, death<sub>i</sub>]</code> 表示第 <code>i</code> 个人的出生和死亡年份。</p>

<p>年份 <code>x</code> 的 <strong>人口</strong> 定义为这一年期间活着的人的数目。第 <code>i</code> 个人被计入年份 <code>x</code> 的人口需要满足：<code>x</code> 在闭区间 <code>[birth<sub>i</sub>, death<sub>i</sub> - 1]</code> 内。注意，人不应当计入他们死亡当年的人口中。</p>

<p>返回 <strong>人口最多</strong> 且 <strong>最早</strong> 的年份。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>logs = [[1993,1999],[2000,2010]]
<strong>输出：</strong>1993
<strong>解释：</strong>人口最多为 1 ，而 1993 是人口为 1 的最早年份。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>logs = [[1950,1961],[1960,1971],[1970,1981]]
<strong>输出：</strong>1960
<strong>解释：</strong> 
人口最多为 2 ，分别出现在 1960 和 1970 。
其中最早年份是 1960 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= logs.length &lt;= 100</code></li>
	<li><code>1950 &lt;= birth<sub>i</sub> &lt; death<sub>i</sub> &lt;= 2050</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

差分数组实现。

用 delta 数组记录每个人的出生和死亡年份。

题目中起始年份为 1950，我们希望数组的起始下标对应起始年份，并且年份与数组下标一一对应，因此我们需要引入起始年份与数组起始下标之差 `offset=1950`，使得下标 i 对应 `i+offset` 年。

遍历 logs 时，将每个人出生年份对应的变化量加上 1，同时将死亡年份对应的变化量减去 1。

最后，遍历 delta 数组，可以求出每一年的人口数量并维护其最大值和对应的最小下标 res。遍历结束后，将最小下标加上 offset，即是所求的年份。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumPopulation(self, logs: List[List[int]]) -> int:
        offset = 1950
        delta = [0] * 101
        # 遍历每个人的出生和死亡年份
        for birth, death in logs:
            # 出生年份人数+1
            delta[birth - offset] += 1
            # 死亡年份人数-1
            delta[death - offset] -= 1

        # mx表示人口数量最大值，cur表示当前年份人口数量，res表示人口数量最大的年份-offset
        mx = cur = res = 0
        for i in range(101):
            cur += delta[i]
            if mx < cur:
                mx = cur
                res = i
        return res + offset
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumPopulation(int[][] logs) {
        int offset = 1950;
        int[] delta = new int[101];
        // 遍历每个人的出生和死亡年份
        for (int[] log : logs) {
            // 出生年份人数+1
            ++delta[log[0] - offset];
            // 死亡年份人数-1
            --delta[log[1] - offset];
        }
        
        // mx表示人口数量最大值，cur表示当前年份人口数量，res表示人口数量最大的年份-offset
        int mx = 0, cur = 0, res = 0;
        for (int i = 0; i < 101; ++i) {
            cur += delta[i];
            if (mx < cur) {
                mx = cur;
                res = i;
            }
        }
        return res + offset;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
