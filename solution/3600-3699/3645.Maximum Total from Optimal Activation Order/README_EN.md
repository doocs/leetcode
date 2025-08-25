---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3645.Maximum%20Total%20from%20Optimal%20Activation%20Order/README_EN.md
rating: 2018
source: Weekly Contest 462 Q3
tags:
    - Greedy
    - Array
    - Two Pointers
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3645. Maximum Total from Optimal Activation Order](https://leetcode.com/problems/maximum-total-from-optimal-activation-order)

[中文文档](/solution/3600-3699/3645.Maximum%20Total%20from%20Optimal%20Activation%20Order/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>value</code> and <code>limit</code>, both of length <code>n</code>.</p>

<p>Initially, all elements are <strong>inactive</strong>. You may activate them in any order.</p>

<ul>
	<li>To activate an inactive element at index <code>i</code>, the number of <strong>currently</strong> active elements must be <strong>strictly less</strong> than <code>limit[i]</code>.</li>
	<li>When you activate the element at index <code>i</code>, it adds <code>value[i]</code> to the <strong>total</strong> activation value (i.e., the sum of <code>value[i]</code> for all elements that have undergone activation operations).</li>
	<li>After each activation, if the number of <strong>currently</strong> active elements becomes <code>x</code>, then <strong>all</strong> elements <code>j</code> with <code>limit[j] &lt;= x</code> become <strong>permanently</strong> inactive, even if they are already active.</li>
</ul>

<p>Return the <strong>maximum</strong> <strong>total</strong> you can obtain by choosing the activation order optimally.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">value = [3,5,8], limit = [2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal activation order is:</p>

<table>
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Step</th>
			<th align="center" style="border: 1px solid black;">Activated <code>i</code></th>
			<th align="center" style="border: 1px solid black;"><code>value[i]</code></th>
			<th align="center" style="border: 1px solid black;">Active Before <code>i</code></th>
			<th align="center" style="border: 1px solid black;">Active After <code>i</code></th>
			<th align="center" style="border: 1px solid black;">Becomes Inactive <code>j</code></th>
			<th align="center" style="border: 1px solid black;">Inactive Elements</th>
			<th align="center" style="border: 1px solid black;">Total</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">5</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;"><code>j = 1</code> as <code>limit[1] = 1</code></td>
			<td align="center" style="border: 1px solid black;">[1]</td>
			<td align="center" style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">[1]</td>
			<td align="center" style="border: 1px solid black;">8</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">8</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;"><code>j = 0</code> as <code>limit[0] = 2</code></td>
			<td align="center" style="border: 1px solid black;">[0, 1]</td>
			<td align="center" style="border: 1px solid black;">16</td>
		</tr>
	</tbody>
</table>

<p>Thus, the maximum possible total is 16.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">value = [4,2,6], limit = [1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal activation order is:</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Step</th>
			<th align="center" style="border: 1px solid black;">Activated <code>i</code></th>
			<th align="center" style="border: 1px solid black;"><code>value[i]</code></th>
			<th align="center" style="border: 1px solid black;">Active Before <code>i</code></th>
			<th align="center" style="border: 1px solid black;">Active After <code>i</code></th>
			<th align="center" style="border: 1px solid black;">Becomes Inactive <code>j</code></th>
			<th align="center" style="border: 1px solid black;">Inactive Elements</th>
			<th align="center" style="border: 1px solid black;">Total</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">6</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;"><code>j = 0, 1, 2</code> as <code>limit[j] = 1</code></td>
			<td align="center" style="border: 1px solid black;">[0, 1, 2]</td>
			<td align="center" style="border: 1px solid black;">6</td>
		</tr>
	</tbody>
</table>

<p>Thus, the maximum possible total is 6.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">value = [4,1,5,2], limit = [3,3,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal activation order is:​​​​​​​<strong>​​​​​​​</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Step</th>
			<th align="center" style="border: 1px solid black;">Activated <code>i</code></th>
			<th align="center" style="border: 1px solid black;"><code>value[i]</code></th>
			<th align="center" style="border: 1px solid black;">Active Before <code>i</code></th>
			<th align="center" style="border: 1px solid black;">Active After <code>i</code></th>
			<th align="center" style="border: 1px solid black;">Becomes Inactive <code>j</code></th>
			<th align="center" style="border: 1px solid black;">Inactive Elements</th>
			<th align="center" style="border: 1px solid black;">Total</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">5</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">[ ]</td>
			<td align="center" style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;"><code>j = 2</code> as <code>limit[2] = 2</code></td>
			<td align="center" style="border: 1px solid black;">[2]</td>
			<td align="center" style="border: 1px solid black;">9</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">[2]</td>
			<td align="center" style="border: 1px solid black;">10</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;"><code>j = 0, 1, 3</code> as <code>limit[j] = 3</code></td>
			<td align="center" style="border: 1px solid black;">[0, 1, 2, 3]</td>
			<td align="center" style="border: 1px solid black;">12</td>
		</tr>
	</tbody>
</table>

<p>Thus, the maximum possible total is 12.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == value.length == limit.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= value[i] &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>1 &lt;= limit[i] &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTotal(self, value: List[int], limit: List[int]) -> int:
        g = defaultdict(list)
        for v, lim in zip(value, limit):
            g[lim].append(v)
        ans = 0
        for lim, vs in g.items():
            vs.sort()
            ans += sum(vs[-lim:])
        return ans
```

#### Java

```java
class Solution {
    public long maxTotal(int[] value, int[] limit) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < value.length; ++i) {
            g.computeIfAbsent(limit[i], k -> new ArrayList<>()).add(value[i]);
        }
        long ans = 0;
        for (var e : g.entrySet()) {
            int lim = e.getKey();
            var vs = e.getValue();
            vs.sort((a, b) -> b - a);
            for (int i = 0; i < Math.min(lim, vs.size()); ++i) {
                ans += vs.get(i);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxTotal(vector<int>& value, vector<int>& limit) {
        unordered_map<int, vector<int>> g;
        int n = value.size();
        for (int i = 0; i < n; ++i) {
            g[limit[i]].push_back(value[i]);
        }
        long long ans = 0;
        for (auto& [lim, vs] : g) {
            sort(vs.begin(), vs.end(), greater<int>());
            for (int i = 0; i < min(lim, (int) vs.size()); ++i) {
                ans += vs[i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxTotal(value []int, limit []int) (ans int64) {
	g := make(map[int][]int)
	for i := range value {
		g[limit[i]] = append(g[limit[i]], value[i])
	}
	for lim, vs := range g {
		slices.SortFunc(vs, func(a, b int) int { return b - a })
		for i := 0; i < min(lim, len(vs)); i++ {
			ans += int64(vs[i])
		}
	}
	return
}
```

#### TypeScript

```ts
function maxTotal(value: number[], limit: number[]): number {
    const g = new Map<number, number[]>();
    for (let i = 0; i < value.length; i++) {
        if (!g.has(limit[i])) {
            g.set(limit[i], []);
        }
        g.get(limit[i])!.push(value[i]);
    }
    let ans = 0;
    for (const [lim, vs] of g) {
        vs.sort((a, b) => b - a);
        ans += vs.slice(0, lim).reduce((acc, v) => acc + v, 0);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
