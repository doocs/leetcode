---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0624.Maximum%20Distance%20in%20Arrays/README.md
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [624. 数组列表中的最大距离](https://leetcode.cn/problems/maximum-distance-in-arrays)

[English Version](/solution/0600-0699/0624.Maximum%20Distance%20in%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定&nbsp;<code>m</code>&nbsp;个数组，每个数组都已经按照升序排好序了。</p>

<p>现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数&nbsp;<code>a</code>&nbsp;和&nbsp;<code>b</code>&nbsp;之间的距离定义为它们差的绝对值&nbsp;<code>|a-b|</code>&nbsp;。</p>

<p>返回最大距离。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[[1,2,3],[4,5],[1,2,3]]
<strong>输出：</strong>4
<strong>解释：</strong>
一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>arrays = [[1],[1]]
<b>输出：</b>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == arrays.length</code></li>
	<li><code>2 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arrays[i].length &lt;= 500</code></li>
	<li><code>-10<sup>4</sup> &lt;= arrays[i][j] &lt;= 10<sup>4</sup></code></li>
	<li><code>arrays[i]</code>&nbsp;以&nbsp;<strong>升序</strong>&nbsp;排序。</li>
	<li>所有数组中最多有&nbsp;<code>10<sup>5</sup></code> 个整数。</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：维护最大值和最小值

我们注意到，最大距离一定是两个数组中的一个最大值和另一个最小值之间的距离。因此，我们可以维护两个变量 $\textit{mi}$ 和 $\textit{mx}$，分别表示已经遍历过的数组中的最小值和最大值。初始时 $\textit{mi}$ 和 $\textit{mx}$ 分别为第一个数组的第一个元素和最后一个元素。

接下来，我们从第二个数组开始遍历，对于每个数组，我们首先计算当前数组的第一个元素和 $\textit{mx}$ 之间的距离，以及当前数组的最后一个元素和 $\textit{mi}$ 之间的距离，然后更新最大距离。同时，我们更新 $\textit{mi} = \min(\textit{mi}, \textit{arr}[0])$ 和 $\textit{mx} = \max(\textit{mx}, \textit{arr}[\textit{size} - 1])$。

遍历结束后，即可得到最大距离。

时间复杂度 $O(m)$，其中 $m$ 为数组的个数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, arrays: List[List[int]]) -> int:
        ans = 0
        mi, mx = arrays[0][0], arrays[0][-1]
        for arr in arrays[1:]:
            a, b = abs(arr[0] - mx), abs(arr[-1] - mi)
            ans = max(ans, a, b)
            mi = min(mi, arr[0])
            mx = max(mx, arr[-1])
        return ans
```

#### Java

```java
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int mi = arrays.get(0).get(0);
        int mx = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < arrays.size(); ++i) {
            var arr = arrays.get(i);
            int a = Math.abs(arr.get(0) - mx);
            int b = Math.abs(arr.get(arr.size() - 1) - mi);
            ans = Math.max(ans, Math.max(a, b));
            mi = Math.min(mi, arr.get(0));
            mx = Math.max(mx, arr.get(arr.size() - 1));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDistance(vector<vector<int>>& arrays) {
        int ans = 0;
        int mi = arrays[0][0], mx = arrays[0][arrays[0].size() - 1];
        for (int i = 1; i < arrays.size(); ++i) {
            auto& arr = arrays[i];
            int a = abs(arr[0] - mx), b = abs(arr[arr.size() - 1] - mi);
            ans = max({ans, a, b});
            mi = min(mi, arr[0]);
            mx = max(mx, arr[arr.size() - 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func maxDistance(arrays [][]int) (ans int) {
	mi, mx := arrays[0][0], arrays[0][len(arrays[0])-1]
	for _, arr := range arrays[1:] {
		a, b := abs(arr[0]-mx), abs(arr[len(arr)-1]-mi)
		ans = max(ans, max(a, b))
		mi = min(mi, arr[0])
		mx = max(mx, arr[len(arr)-1])
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function maxDistance(arrays: number[][]): number {
    let ans = 0;
    let [mi, mx] = [arrays[0][0], arrays[0].at(-1)!];
    for (let i = 1; i < arrays.length; ++i) {
        const arr = arrays[i];
        const a = Math.abs(arr[0] - mx);
        const b = Math.abs(arr.at(-1)! - mi);
        ans = Math.max(ans, a, b);
        mi = Math.min(mi, arr[0]);
        mx = Math.max(mx, arr.at(-1)!);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_distance(arrays: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let mut mi = arrays[0][0];
        let mut mx = arrays[0][arrays[0].len() - 1];

        for i in 1..arrays.len() {
            let arr = &arrays[i];
            let a = (arr[0] - mx).abs();
            let b = (arr[arr.len() - 1] - mi).abs();
            ans = ans.max(a).max(b);

            mi = mi.min(arr[0]);
            mx = mx.max(arr[arr.len() - 1]);
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} arrays
 * @return {number}
 */
var maxDistance = function (arrays) {
    let ans = 0;
    let [mi, mx] = [arrays[0][0], arrays[0].at(-1)];
    for (let i = 1; i < arrays.length; ++i) {
        const arr = arrays[i];
        const a = Math.abs(arr[0] - mx);
        const b = Math.abs(arr.at(-1) - mi);
        ans = Math.max(ans, a, b);
        mi = Math.min(mi, arr[0]);
        mx = Math.max(mx, arr.at(-1));
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
