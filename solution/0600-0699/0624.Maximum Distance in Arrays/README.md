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

我们注意到，最大距离一定是两个数组中的一个最大值和另一个最小值之间的距离。因此，我们可以维护两个变量，分别表示当前数组中的最大值和最小值，然后遍历数组，更新最大距离，同时更新最大值和最小值。

遍历结束后，即可得到最大距离。

时间复杂度 $O(m)$，空间复杂度 $O(1)$。其中 $m$ 为数组的个数。

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
    const n = arrays.length;
    let res = 0;
    let [min, max] = [Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY];

    for (let i = 0; i < n; i++) {
        const a = arrays[i];
        res = Math.max(Math.max(a.at(-1)! - min, max - a[0]), res);
        min = Math.min(min, a[0]);
        max = Math.max(max, a.at(-1)!);
    }

    return res;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} arrays
 * @return {number}
 */
var maxDistance = function (arrays) {
    const n = arrays.length;
    let res = 0;
    let [min, max] = [Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY];

    for (let i = 0; i < n; i++) {
        const a = arrays[i];
        res = Math.max(Math.max(a.at(-1) - min, max - a[0]), res);
        min = Math.min(min, a[0]);
        max = Math.max(max, a.at(-1));
    }

    return res;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：一行

<!-- tabs:start -->

#### TypeScript

```ts
const maxDistance = (arrays: number[][]): number =>
    arrays.reduce(
        ([res, min, max], a) => [
            Math.max(Math.max(a.at(-1)! - min, max - a[0]), res),
            Math.min(min, a[0]),
            Math.max(max, a.at(-1)!),
        ],
        [0, Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY],
    )[0];
```

#### JavaScript

```js
/**
 * @param {number[][]} arrays
 * @return {number}
 */
var maxDistance = arrays =>
    arrays.reduce(
        ([res, min, max], a) => [
            Math.max(Math.max(a.at(-1) - min, max - a[0]), res),
            Math.min(min, a[0]),
            Math.max(max, a.at(-1)),
        ],
        [0, Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY],
    )[0];
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
