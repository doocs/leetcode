# [1854. 人口最多的年份](https://leetcode.cn/problems/maximum-population-year)

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

**方法一：差分数组**

我们注意到，年份的范围是 $[1950,..2050]$，因此我们可以将这些年份映射到一个长度为 $101$ 的数组 $d$ 中，数组的下标表示年份减去 $1950$ 的值。

接下来遍历 $logs$，对于每个人，我们将 $d[birth_i - 1950]$ 加 $1$，将 $d[death_i - 1950]$ 减 $1$。最后遍历数组 $d$，求出前缀和的最大值，即为人口最多的年份，再加上 $1950$ 即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为数组 $logs$ 的长度；而 $C$ 为年份的范围大小，即 $2050 - 1950 + 1 = 101$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumPopulation(self, logs: List[List[int]]) -> int:
        d = [0] * 101
        offset = 1950
        for a, b in logs:
            a, b = a - offset, b - offset
            d[a] += 1
            d[b] -= 1
        s = mx = j = 0
        for i, x in enumerate(d):
            s += x
            if mx < s:
                mx, j = s, i
        return j + offset
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] d = new int[101];
        final int offset = 1950;
        for (var log : logs) {
            int a = log[0] - offset;
            int b = log[1] - offset;
            ++d[a];
            --d[b];
        }
        int s = 0, mx = 0;
        int j = 0;
        for (int i = 0; i < d.length; ++i) {
            s += d[i];
            if (mx < s) {
                mx = s;
                j = i;
            }
        }
        return j + offset;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumPopulation(vector<vector<int>>& logs) {
        int d[101]{};
        const int offset = 1950;
        for (auto& log : logs) {
            int a = log[0] - offset;
            int b = log[1] - offset;
            ++d[a];
            --d[b];
        }
        int s = 0, mx = 0;
        int j = 0;
        for (int i = 0; i < 101; ++i) {
            s += d[i];
            if (mx < s) {
                mx = s;
                j = i;
            }
        }
        return j + offset;
    }
};
```

### **Go**

```go
func maximumPopulation(logs [][]int) int {
	d := [101]int{}
	offset := 1950
	for _, log := range logs {
		a, b := log[0]-offset, log[1]-offset
		d[a]++
		d[b]--
	}
	var s, mx, j int
	for i, x := range d {
		s += x
		if mx < s {
			mx = s
			j = i
		}
	}
	return j + offset
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} logs
 * @return {number}
 */
var maximumPopulation = function (logs) {
    const d = new Array(101).fill(0);
    const offset = 1950;
    for (let [a, b] of logs) {
        a -= offset;
        b -= offset;
        d[a]++;
        d[b]--;
    }
    let j = 0;
    for (let i = 0, s = 0, mx = 0; i < 101; ++i) {
        s += d[i];
        if (mx < s) {
            mx = s;
            j = i;
        }
    }
    return j + offset;
};
```

### **TypeScript**

```ts
function maximumPopulation(logs: number[][]): number {
    const d: number[] = new Array(101).fill(0);
    const offset = 1950;
    for (const [birth, death] of logs) {
        d[birth - offset]++;
        d[death - offset]--;
    }
    let j = 0;
    for (let i = 0, s = 0, mx = 0; i < d.length; ++i) {
        s += d[i];
        if (mx < s) {
            mx = s;
            j = i;
        }
    }
    return j + offset;
}
```

### **...**

```

```

<!-- tabs:end -->
