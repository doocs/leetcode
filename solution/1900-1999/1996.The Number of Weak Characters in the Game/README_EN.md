# [1996. The Number of Weak Characters in the Game](https://leetcode.com/problems/the-number-of-weak-characters-in-the-game)

[中文文档](/solution/1900-1999/1996.The%20Number%20of%20Weak%20Characters%20in%20the%20Game/README.md)

## Description

<p>You are playing a game that contains multiple characters, and each of the characters has <strong>two</strong> main properties: <strong>attack</strong> and <strong>defense</strong>. You are given a 2D integer array <code>properties</code> where <code>properties[i] = [attack<sub>i</sub>, defense<sub>i</sub>]</code> represents the properties of the <code>i<sup>th</sup></code> character in the game.</p>

<p>A character is said to be <strong>weak</strong> if any other character has <strong>both</strong> attack and defense levels <strong>strictly greater</strong> than this character&#39;s attack and defense levels. More formally, a character <code>i</code> is said to be <strong>weak</strong> if there exists another character <code>j</code> where <code>attack<sub>j</sub> &gt; attack<sub>i</sub></code> and <code>defense<sub>j</sub> &gt; defense<sub>i</sub></code>.</p>

<p>Return <em>the number of <strong>weak</strong> characters</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> properties = [[5,5],[6,3],[3,6]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> No character has strictly greater attack and defense than the other.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> properties = [[2,2],[3,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The first character is weak because the second character has a strictly greater attack and defense.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> properties = [[1,5],[10,4],[4,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The third character is weak because the second character has a strictly greater attack and defense.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= properties.length &lt;= 10<sup>5</sup></code></li>
	<li><code>properties[i].length == 2</code></li>
	<li><code>1 &lt;= attack<sub>i</sub>, defense<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfWeakCharacters(self, properties: List[List[int]]) -> int:
        properties.sort(key=lambda x: (-x[0], x[1]))
        ans = mx = 0
        for _, x in properties:
            ans += x < mx
            mx = max(mx, x)
        return ans
```

### **Java**

```java
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> b[0] - a[0] == 0 ? a[1] - b[1] : b[0] - a[0]);
        int ans = 0, mx = 0;
        for (var x : properties) {
            if (x[1] < mx) {
                ++ans;
            }
            mx = Math.max(mx, x[1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfWeakCharacters(vector<vector<int>>& properties) {
        sort(properties.begin(), properties.end(), [&](auto& a, auto& b) { return a[0] == b[0] ? a[1] < b[1] : a[0] > b[0]; });
        int ans = 0, mx = 0;
        for (auto& x : properties) {
            ans += x[1] < mx;
            mx = max(mx, x[1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfWeakCharacters(properties [][]int) (ans int) {
	sort.Slice(properties, func(i, j int) bool {
		a, b := properties[i], properties[j]
		if a[0] == b[0] {
			return a[1] < b[1]
		}
		return a[0] > b[0]
	})
	mx := 0
	for _, x := range properties {
		if x[1] < mx {
			ans++
		} else {
			mx = x[1]
		}
	}
	return
}
```

### **TypeScript**

```ts
function numberOfWeakCharacters(properties: number[][]): number {
    properties.sort((a, b) => (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
    let ans = 0;
    let mx = 0;
    for (const [, x] of properties) {
        if (x < mx) {
            ans++;
        } else {
            mx = x;
        }
    }
    return ans;
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} properties
 * @return {number}
 */
var numberOfWeakCharacters = function (properties) {
    properties.sort((a, b) => (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
    let ans = 0;
    let mx = 0;
    for (const [, x] of properties) {
        if (x < mx) {
            ans++;
        } else {
            mx = x;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
