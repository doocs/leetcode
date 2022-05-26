# [1854. Maximum Population Year](https://leetcode.com/problems/maximum-population-year)

[中文文档](/solution/1800-1899/1854.Maximum%20Population%20Year/README.md)

## Description

<p>You are given a 2D integer array <code>logs</code> where each <code>logs[i] = [birth<sub>i</sub>, death<sub>i</sub>]</code> indicates the birth and death years of the <code>i<sup>th</sup></code> person.</p>

<p>The <strong>population</strong> of some year <code>x</code> is the number of people alive during that year. The <code>i<sup>th</sup></code> person is counted in year <code>x</code>&#39;s population if <code>x</code> is in the <strong>inclusive</strong> range <code>[birth<sub>i</sub>, death<sub>i</sub> - 1]</code>. Note that the person is <strong>not</strong> counted in the year that they die.</p>

<p>Return <em>the <strong>earliest</strong> year with the <strong>maximum population</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> logs = [[1993,1999],[2000,2010]]
<strong>Output:</strong> 1993
<strong>Explanation:</strong> The maximum population is 1, and 1993 is the earliest year with this population.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> logs = [[1950,1961],[1960,1971],[1970,1981]]
<strong>Output:</strong> 1960
<strong>Explanation:</strong> 
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= logs.length &lt;= 100</code></li>
	<li><code>1950 &lt;= birth<sub>i</sub> &lt; death<sub>i</sub> &lt;= 2050</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
