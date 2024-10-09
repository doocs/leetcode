---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1436.Destination%20City/README_EN.md
rating: 1192
source: Weekly Contest 187 Q1
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [1436. Destination City](https://leetcode.com/problems/destination-city)

[中文文档](/solution/1400-1499/1436.Destination%20City/README.md)

## Description

<!-- description:start -->

<p>You are given the array <code>paths</code>, where <code>paths[i] = [cityA<sub>i</sub>, cityB<sub>i</sub>]</code> means there exists a direct path going from <code>cityA<sub>i</sub></code> to <code>cityB<sub>i</sub></code>. <em>Return the destination city, that is, the city without any path outgoing to another city.</em></p>

<p>It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> paths = [[&quot;London&quot;,&quot;New York&quot;],[&quot;New York&quot;,&quot;Lima&quot;],[&quot;Lima&quot;,&quot;Sao Paulo&quot;]]
<strong>Output:</strong> &quot;Sao Paulo&quot; 
<strong>Explanation:</strong> Starting at &quot;London&quot; city you will reach &quot;Sao Paulo&quot; city which is the destination city. Your trip consist of: &quot;London&quot; -&gt; &quot;New York&quot; -&gt; &quot;Lima&quot; -&gt; &quot;Sao Paulo&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> paths = [[&quot;B&quot;,&quot;C&quot;],[&quot;D&quot;,&quot;B&quot;],[&quot;C&quot;,&quot;A&quot;]]
<strong>Output:</strong> &quot;A&quot;
<strong>Explanation:</strong> All possible trips are:&nbsp;
&quot;D&quot; -&gt; &quot;B&quot; -&gt; &quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;B&quot; -&gt; &quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;A&quot;.&nbsp;
Clearly the destination city is &quot;A&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> paths = [[&quot;A&quot;,&quot;Z&quot;]]
<strong>Output:</strong> &quot;Z&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 100</code></li>
	<li><code>paths[i].length == 2</code></li>
	<li><code>1 &lt;= cityA<sub>i</sub>.length, cityB<sub>i</sub>.length &lt;= 10</code></li>
	<li><code>cityA<sub>i</sub> != cityB<sub>i</sub></code></li>
	<li>All strings consist of lowercase and uppercase English letters and the space character.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

According to the problem description, the destination city will not appear in any of the $\textit{cityA}$. Therefore, we can first traverse the $\textit{paths}$ and put all $\textit{cityA}$ into a set $\textit{s}$. Then, we traverse the $\textit{paths}$ again to find the $\textit{cityB}$ that is not in $\textit{s}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of $\textit{paths}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def destCity(self, paths: List[List[str]]) -> str:
        s = {a for a, _ in paths}
        return next(b for _, b in paths if b not in s)
```

#### Java

```java
class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> s = new HashSet<>();
        for (var p : paths) {
            s.add(p.get(0));
        }
        for (int i = 0;; ++i) {
            var b = paths.get(i).get(1);
            if (!s.contains(b)) {
                return b;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    string destCity(vector<vector<string>>& paths) {
        unordered_set<string> s;
        for (auto& p : paths) {
            s.insert(p[0]);
        }
        for (int i = 0;; ++i) {
            auto b = paths[i][1];
            if (!s.contains(b)) {
                return b;
            }
        }
    }
};
```

#### Go

```go
func destCity(paths [][]string) string {
	s := map[string]bool{}
	for _, p := range paths {
		s[p[0]] = true
	}
	for _, p := range paths {
		if !s[p[1]] {
			return p[1]
		}
	}
	return ""
}
```

#### TypeScript

```ts
function destCity(paths: string[][]): string {
    const s = new Set<string>(paths.map(([a, _]) => a));
    return paths.find(([_, b]) => !s.has(b))![1];
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn dest_city(paths: Vec<Vec<String>>) -> String {
        let s = paths
            .iter()
            .map(|p| p[0].clone())
            .collect::<HashSet<String>>();
        paths.into_iter().find(|p| !s.contains(&p[1])).unwrap()[1].clone()
    }
}
```

#### JavaScript

```js
/**
 * @param {string[][]} paths
 * @return {string}
 */
var destCity = function (paths) {
    const s = new Set(paths.map(([a, _]) => a));
    return paths.find(([_, b]) => !s.has(b))[1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
