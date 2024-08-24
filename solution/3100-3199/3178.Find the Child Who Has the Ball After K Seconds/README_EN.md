---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3178.Find%20the%20Child%20Who%20Has%20the%20Ball%20After%20K%20Seconds/README_EN.md
rating: 1255
source: Weekly Contest 401 Q1
tags:
    - Math
    - Simulation
---

<!-- problem:start -->

# [3178. Find the Child Who Has the Ball After K Seconds](https://leetcode.com/problems/find-the-child-who-has-the-ball-after-k-seconds)

[中文文档](/solution/3100-3199/3178.Find%20the%20Child%20Who%20Has%20the%20Ball%20After%20K%20Seconds/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>positive</strong> integers <code>n</code> and <code>k</code>. There are <code>n</code> children numbered from <code>0</code> to <code>n - 1</code> standing in a queue <em>in order</em> from left to right.</p>

<p>Initially, child 0 holds a ball and the direction of passing the ball is towards the right direction. After each second, the child holding the ball passes it to the child next to them. Once the ball reaches <strong>either</strong> end of the line, i.e. child 0 or child <code>n - 1</code>, the direction of passing is <strong>reversed</strong>.</p>

<p>Return the number of the child who receives the ball after <code>k</code> seconds.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Time elapsed</th>
			<th>Children</th>
		</tr>
		<tr>
			<td><code>0</code></td>
			<td><code>[<u>0</u>, 1, 2]</code></td>
		</tr>
		<tr>
			<td><code>1</code></td>
			<td><code>[0, <u>1</u>, 2]</code></td>
		</tr>
		<tr>
			<td><code>2</code></td>
			<td><code>[0, 1, <u>2</u>]</code></td>
		</tr>
		<tr>
			<td><code>3</code></td>
			<td><code>[0, <u>1</u>, 2]</code></td>
		</tr>
		<tr>
			<td><code>4</code></td>
			<td><code>[<u>0</u>, 1, 2]</code></td>
		</tr>
		<tr>
			<td><code>5</code></td>
			<td><code>[0, <u>1</u>, 2]</code></td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Time elapsed</th>
			<th>Children</th>
		</tr>
		<tr>
			<td><code>0</code></td>
			<td><code>[<u>0</u>, 1, 2, 3, 4]</code></td>
		</tr>
		<tr>
			<td><code>1</code></td>
			<td><code>[0, <u>1</u>, 2, 3, 4]</code></td>
		</tr>
		<tr>
			<td><code>2</code></td>
			<td><code>[0, 1, <u>2</u>, 3, 4]</code></td>
		</tr>
		<tr>
			<td><code>3</code></td>
			<td><code>[0, 1, 2, <u>3</u>, 4]</code></td>
		</tr>
		<tr>
			<td><code>4</code></td>
			<td><code>[0, 1, 2, 3, <u>4</u>]</code></td>
		</tr>
		<tr>
			<td><code>5</code></td>
			<td><code>[0, 1, 2, <u>3</u>, 4]</code></td>
		</tr>
		<tr>
			<td><code>6</code></td>
			<td><code>[0, 1, <u>2</u>, 3, 4]</code></td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Time elapsed</th>
			<th>Children</th>
		</tr>
		<tr>
			<td><code>0</code></td>
			<td><code>[<u>0</u>, 1, 2, 3]</code></td>
		</tr>
		<tr>
			<td><code>1</code></td>
			<td><code>[0, <u>1</u>, 2, 3]</code></td>
		</tr>
		<tr>
			<td><code>2</code></td>
			<td><code>[0, 1, <u>2</u>, 3]</code></td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as <a href="https://leetcode.com/problems/pass-the-pillow/description/" target="_blank"> 2582: Pass the Pillow.</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

We notice that there are $n - 1$ passes in each round. Therefore, we can take $k$ modulo $n - 1$ to get the number of passes $mod$ in the current round. Then we divide $k$ by $n - 1$ to get the current round number $k$.

Next, we judge the current round number $k$:

-   If $k$ is odd, then the current passing direction is from the end of the queue to the head, so it will be passed to the person with the number $n - mod - 1$.
-   If $k$ is even, then the current passing direction is from the head of the queue to the end, so it will be passed to the person with the number $mod$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfChild(self, n: int, k: int) -> int:
        k, mod = divmod(k, n - 1)
        return n - mod - 1 if k & 1 else mod
```

#### Java

```java
class Solution {
    public int numberOfChild(int n, int k) {
        int mod = k % (n - 1);
        k /= (n - 1);
        return k % 2 == 1 ? n - mod - 1 : mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfChild(int n, int k) {
        int mod = k % (n - 1);
        k /= (n - 1);
        return k % 2 == 1 ? n - mod - 1 : mod;
    }
};
```

#### Go

```go
func numberOfChild(n int, k int) int {
	mod := k % (n - 1)
	k /= (n - 1)
	if k%2 == 1 {
		return n - mod - 1
	}
	return mod
}
```

#### TypeScript

```ts
function numberOfChild(n: number, k: number): number {
    const mod = k % (n - 1);
    k = (k / (n - 1)) | 0;
    return k % 2 ? n - mod - 1 : mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
