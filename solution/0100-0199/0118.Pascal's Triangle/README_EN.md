---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0118.Pascal%27s%20Triangle/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [118. Pascal's Triangle](https://leetcode.com/problems/pascals-triangle)

[中文文档](/solution/0100-0199/0118.Pascal%27s%20Triangle/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>numRows</code>, return the first numRows of <strong>Pascal&#39;s triangle</strong>.</p>

<p>In <strong>Pascal&#39;s triangle</strong>, each number is the sum of the two numbers directly above it as shown:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0118.Pascal%27s%20Triangle/images/PascalTriangleAnimated2.gif" style="height:240px; width:260px" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> numRows = 5
<strong>Output:</strong> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> numRows = 1
<strong>Output:</strong> [[1]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numRows &lt;= 30</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We first create an answer array $f$, then set the first row of $f$ to $[1]$. Next, starting from the second row, the first and last elements of each row are $1$, and for other elements $f[i][j] = f[i - 1][j - 1] + f[i - 1][j]$.

The time complexity is $O(n^2)$, where $n$ is the given number of rows. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        f = [[1]]
        for i in range(numRows - 1):
            g = [1] + [a + b for a, b in pairwise(f[-1])] + [1]
            f.append(g)
        return f
```

#### Java

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> f = new ArrayList<>();
        f.add(List.of(1));
        for (int i = 0; i < numRows - 1; ++i) {
            List<Integer> g = new ArrayList<>();
            g.add(1);
            for (int j = 1; j < f.get(i).size(); ++j) {
                g.add(f.get(i).get(j - 1) + f.get(i).get(j));
            }
            g.add(1);
            f.add(g);
        }
        return f;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        vector<vector<int>> f;
        f.push_back(vector<int>(1, 1));
        for (int i = 0; i < numRows - 1; ++i) {
            vector<int> g;
            g.push_back(1);
            for (int j = 1; j < f[i].size(); ++j) {
                g.push_back(f[i][j - 1] + f[i][j]);
            }
            g.push_back(1);
            f.push_back(g);
        }
        return f;
    }
};
```

#### Go

```go
func generate(numRows int) [][]int {
	f := [][]int{[]int{1}}
	for i := 0; i < numRows-1; i++ {
		g := []int{1}
		for j := 1; j < len(f[i]); j++ {
			g = append(g, f[i][j-1]+f[i][j])
		}
		g = append(g, 1)
		f = append(f, g)
	}
	return f
}
```

#### TypeScript

```ts
function generate(numRows: number): number[][] {
    const f: number[][] = [[1]];
    for (let i = 0; i < numRows - 1; ++i) {
        const g: number[] = [1];
        for (let j = 1; j < f[i].length; ++j) {
            g.push(f[i][j - 1] + f[i][j]);
        }
        g.push(1);
        f.push(g);
    }
    return f;
}
```

#### Rust

```rust
impl Solution {
    pub fn generate(num_rows: i32) -> Vec<Vec<i32>> {
        let mut f = vec![vec![1]];
        for i in 1..num_rows {
            let mut g = vec![1];
            for j in 1..f[i as usize - 1].len() {
                g.push(f[i as usize - 1][j - 1] + f[i as usize - 1][j]);
            }
            g.push(1);
            f.push(g);
        }
        f
    }
}
```

#### JavaScript

```js
/**
 * @param {number} numRows
 * @return {number[][]}
 */
var generate = function (numRows) {
    const f = [[1]];
    for (let i = 0; i < numRows - 1; ++i) {
        const g = [1];
        for (let j = 1; j < f[i].length; ++j) {
            g.push(f[i][j - 1] + f[i][j]);
        }
        g.push(1);
        f.push(g);
    }
    return f;
};
```

#### C#

```cs
public class Solution {
    public IList<IList<int>> Generate(int numRows) {
        var f = new List<IList<int>> { new List<int> { 1 } };
        for (int i = 1; i < numRows; ++i) {
            var g = new List<int> { 1 };
            for (int j = 1; j < f[i - 1].Count; ++j) {
                g.Add(f[i - 1][j - 1] + f[i - 1][j]);
            }
            g.Add(1);
            f.Add(g);
        }
        return f;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
