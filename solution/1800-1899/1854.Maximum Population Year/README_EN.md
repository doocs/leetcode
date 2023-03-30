# [1854. Maximum Population Year](https://leetcode.com/problems/maximum-population-year)

[中文文档](/solution/1800-1899/1854.Maximum%20Population%20Year/README.md)

## Description

<p>You are given a 2D integer array <code>logs</code> where each <code>logs[i] = [birth<sub>i</sub>, death<sub>i</sub>]</code> indicates the birth and death years of the <code>i<sup>th</sup></code> person.</p>

<p>The <strong>population</strong> of some year <code>x</code> is the number of people alive during that year. The <code>i<sup>th</sup></code> person is counted in year <code>x</code>&#39;s population if <code>x</code> is in the <strong>inclusive</strong> range <code>[birth<sub>i</sub>, death<sub>i</sub> - 1]</code>. Note that the person is <strong>not</strong> counted in the year that they die.</p>

<p>Return <em>the <strong>earliest</strong> year with the <strong>maximum population</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> logs = [[1993,1999],[2000,2010]]
<strong>Output:</strong> 1993
<strong>Explanation:</strong> The maximum population is 1, and 1993 is the earliest year with this population.
</pre>

<p><strong class="example">Example 2:</strong></p>

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
