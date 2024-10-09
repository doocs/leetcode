---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1436.Destination%20City/README.md
rating: 1192
source: 第 187 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [1436. 旅行终点站](https://leetcode.cn/problems/destination-city)

[English Version](/solution/1400-1499/1436.Destination%20City/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一份旅游线路图，该线路图中的旅行线路用数组 <code>paths</code> 表示，其中 <code>paths[i] = [cityA<sub>i</sub>, cityB<sub>i</sub>]</code> 表示该线路将会从 <code>cityA<sub>i</sub></code> 直接前往 <code>cityB<sub>i</sub></code> 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市<em>。</em></p>

<p>题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
<strong>输出：</strong>"Sao Paulo" 
<strong>解释：</strong>从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -&gt; "New York" -&gt; "Lima" -&gt; "Sao Paulo" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>paths = [["B","C"],["D","B"],["C","A"]]
<strong>输出：</strong>"A"
<strong>解释：</strong>所有可能的线路是：
"D" -&gt; "B" -&gt; "C" -&gt; "A".&nbsp;
"B" -&gt; "C" -&gt; "A".&nbsp;
"C" -&gt; "A".&nbsp;
"A".&nbsp;
显然，旅行终点站是 "A" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>paths = [["A","Z"]]
<strong>输出：</strong>"Z"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 100</code></li>
	<li><code>paths[i].length == 2</code></li>
	<li><code>1 &lt;=&nbsp;cityA<sub>i</sub>.length,&nbsp;cityB<sub>i</sub>.length &lt;= 10</code></li>
	<li><code>cityA<sub>i&nbsp;</sub>!=&nbsp;cityB<sub>i</sub></code></li>
	<li>所有字符串均由大小写英文字母和空格字符组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

根据题目描述，终点一定不会出现在所有 $\textit{cityA}$ 中，因此，我们可以先遍历一遍 $\textit{paths}$，将所有 $\textit{cityA}$ 放入一个集合 $\textit{s}$ 中，然后再遍历一遍 $\textit{paths}$，找到不在 $\textit{s}$ 中的 $\textit{cityB}$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 $\textit{paths}$ 的长度。

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
