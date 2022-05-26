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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumPopulation(self, logs: List[List[int]]) -> int:
        delta = [0] * 2055
        for birth, death in logs:
            delta[birth] += 1
            delta[death] -= 1

        mx = res = cur = 0
        for i, v in enumerate(delta):
            cur += v
            if mx < cur:
                mx = cur
                res = i
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] delta = new int[2055];
        for (int[] log : logs) {
            ++delta[log[0]];
            --delta[log[1]];
        }
        int res = 0, mx = 0, cur = 0;
        for (int i = 0; i < delta.length; ++i) {
            cur += delta[i];
            if (cur > mx) {
                mx = cur;
                res = i;
            }
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} logs
 * @return {number}
 */
var maximumPopulation = function (logs) {
    const offset = 1950;
    const len = 2050 - 1950 + 1;
    let delta = new Array(len).fill(0);
    for (let log of logs) {
        delta[log[0] - offset] += 1;
        delta[log[1] - offset] -= 1;
    }
    let max = 0;
    let total = 0;
    let index = 0;
    for (let i = 0; i < len; i++) {
        total += delta[i];
        if (total > max) {
            max = total;
            index = i;
        }
    }
    return index + offset;
};
```

### **C++**

```cpp
class Solution {
public:
    int maximumPopulation(vector<vector<int>>& logs) {
        vector<int> delta(101, 0);
        int offset = 1950;
        for (auto log : logs) {
            ++delta[log[0] - offset];
            --delta[log[1] - offset];
        }
        int res = 0, mx = 0, cur = 0;
        for (int i = 0; i < delta.size(); ++i) {
            cur += delta[i];
            if (cur > mx) {
                mx = cur;
                res = i;
            }
        }
        return res + offset;
    }
};
```

### **Go**

```go
func maximumPopulation(logs [][]int) int {
	delta := make([]int, 101)
	offset := 1950
	for _, log := range logs {
		delta[log[0]-offset]++
		delta[log[1]-offset]--
	}
	res, mx, cur := 0, 0, 0
	for i := 0; i < len(delta); i++ {
		cur += delta[i]
		if cur > mx {
			mx = cur
			res = i
		}
	}
	return res + offset
}
```

### **...**

```

```

<!-- tabs:end -->
