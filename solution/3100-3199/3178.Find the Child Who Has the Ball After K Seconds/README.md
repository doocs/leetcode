---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3178.Find%20the%20Child%20Who%20Has%20the%20Ball%20After%20K%20Seconds/README.md
---

<!-- problem:start -->

# [3178. 找出 K 秒后拿着球的孩子](https://leetcode.cn/problems/find-the-child-who-has-the-ball-after-k-seconds)

[English Version](/solution/3100-3199/3178.Find%20the%20Child%20Who%20Has%20the%20Ball%20After%20K%20Seconds/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个 <strong>正整数 </strong><code>n</code> 和 <code>k</code>。有 <code>n</code> 个编号从 <code>0</code> 到 <code>n - 1</code> 的孩子按顺序从左到右站成一队。</p>

<p>最初，编号为 0 的孩子拿着一个球，并且向右传球。每过一秒，拿着球的孩子就会将球传给他旁边的孩子。一旦球到达队列的 <strong>任一端 </strong>，即编号为 0 的孩子或编号为 <code>n - 1</code> 的孩子处，传球方向就会<strong> 反转 </strong>。</p>

<p>返回 <code>k</code> 秒后接到球的孩子的编号。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 3, k = 5</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>经过的时间</th>
			<th>孩子队列</th>
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

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 5, k = 6</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>经过的时间</th>
			<th>孩子队列</th>
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

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 4, k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>经过的时间</th>
			<th>孩子队列</th>
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

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们注意到，每一轮有 $n - 1$ 次传递，因此我们可以将 $k$ 对 $n - 1$ 取模，得到 $k$ 在当前轮的传递次数 $mod$，然后我们将 $k$ 除以 $n - 1$，得到当前的轮数 $k$。

接下来我们判断当前的轮数 $k$：

-   如果 $k$ 为奇数，那么当前的传递方向是从队尾到队首，因此会传递到编号为 $n - mod - 1$ 的人手中；
-   如果 $k$ 为偶数，那么当前的传递方向是从队首到队尾，因此会传递到编号为 $mod$ 的人手中。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

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
