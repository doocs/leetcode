---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2021.Brightest%20Position%20on%20Street/README.md
tags:
    - 数组
    - 有序集合
    - 前缀和
    - 排序
---

<!-- problem:start -->

# [2021. 街上最亮的位置 🔒](https://leetcode.cn/problems/brightest-position-on-street)

[English Version](/solution/2000-2099/2021.Brightest%20Position%20on%20Street/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一条街上有很多的路灯，路灯的坐标由数组&nbsp;<code>lights&nbsp;</code>的形式给出。&nbsp;每个&nbsp;<code>lights[i] = [position<sub>i</sub>, range<sub>i</sub>]</code>&nbsp;代表坐标为&nbsp;<code>position<sub>i</sub></code>&nbsp;的路灯照亮的范围为&nbsp;<code>[position<sub>i</sub> - range<sub>i</sub>, position<sub>i</sub> + range<sub>i</sub>]</code>&nbsp;<strong>（包括顶点）。</strong></p>

<p>位置&nbsp;<code>p</code>&nbsp;的亮度由能够照到&nbsp;<code>p</code>的路灯的数量来决定的。</p>

<p>给出&nbsp;<code>lights</code>, 返回<strong>最亮</strong>的位置&nbsp;。如果有很多，返回坐标最小的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2021.Brightest%20Position%20on%20Street/images/image-20210928155140-1.png" style="width: 700px; height: 165px;">
<pre><strong>输入:</strong> lights = [[-3,2],[1,2],[3,3]]
<strong>输出:</strong> -1
<strong>解释:</strong>
第一个路灯照亮的范围是[(-3) - 2, (-3) + 2] = [-5, -1].
第二个路灯照亮的范围是 [1 - 2, 1 + 2] = [-1, 3].
第三个路灯照亮的范围是 [3 - 3, 3 + 3] = [0, 6].

坐标-1被第一个和第二个路灯照亮，亮度为2
坐标0，1，2都被第二个和第三个路灯照亮，亮度为2.
对于以上坐标，-1最小，所以返回-1</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> lights = [[1,0],[0,1]]
<strong>输出:</strong> 1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> lights = [[1,2]]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= lights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>lights[i].length == 2</code></li>
	<li><code>-10<sup>8</sup> &lt;= position<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= range<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组 + 哈希表 + 排序

我们可以将每个路灯照亮的范围看作是一个区间，区间左端点 $l = position_i - range_i$，区间右端点 $r = position_i + range_i$。我们可以利用差分数组的思想，对于每个区间 $[l, r]$，将位置 $l$ 的值加 $1$，将位置 $r + 1$ 的值减 $1$，用哈希表维护每个位置的变化值。

然后从小到大遍历每个位置，计算当前位置的亮度 $s$，如果此前的最大亮度 $mx \lt s$，则更新最大亮度 $mx = s$，并记录当前位置 $ans = i$。

最后返回 $ans$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为 $lights$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def brightestPosition(self, lights: List[List[int]]) -> int:
        d = defaultdict(int)
        for i, j in lights:
            l, r = i - j, i + j
            d[l] += 1
            d[r + 1] -= 1
        ans = s = mx = 0
        for k in sorted(d):
            s += d[k]
            if mx < s:
                mx = s
                ans = k
        return ans
```

#### Java

```java
class Solution {
    public int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (var x : lights) {
            int l = x[0] - x[1], r = x[0] + x[1];
            d.merge(l, 1, Integer::sum);
            d.merge(r + 1, -1, Integer::sum);
        }
        int ans = 0, s = 0, mx = 0;
        for (var x : d.entrySet()) {
            int v = x.getValue();
            s += v;
            if (mx < s) {
                mx = s;
                ans = x.getKey();
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
    int brightestPosition(vector<vector<int>>& lights) {
        map<int, int> d;
        for (auto& x : lights) {
            int l = x[0] - x[1], r = x[0] + x[1];
            ++d[l];
            --d[r + 1];
        }
        int ans = 0, s = 0, mx = 0;
        for (auto& [i, v] : d) {
            s += v;
            if (mx < s) {
                mx = s;
                ans = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func brightestPosition(lights [][]int) (ans int) {
	d := map[int]int{}
	for _, x := range lights {
		l, r := x[0]-x[1], x[0]+x[1]
		d[l]++
		d[r+1]--
	}
	keys := make([]int, 0, len(d))
	for i := range d {
		keys = append(keys, i)
	}
	sort.Ints(keys)
	mx, s := 0, 0
	for _, i := range keys {
		s += d[i]
		if mx < s {
			mx = s
			ans = i
		}
	}
	return
}
```

#### JavaScript

```js
/**
 * @param {number[][]} lights
 * @return {number}
 */
var brightestPosition = function (lights) {
    const d = new Map();
    for (const [i, j] of lights) {
        const l = i - j;
        const r = i + j;
        d.set(l, (d.get(l) ?? 0) + 1);
        d.set(r + 1, (d.get(r + 1) ?? 0) - 1);
    }
    const keys = [];
    for (const k of d.keys()) {
        keys.push(k);
    }
    keys.sort((a, b) => a - b);
    let ans = 0;
    let s = 0;
    let mx = 0;
    for (const i of keys) {
        s += d.get(i);
        if (mx < s) {
            mx = s;
            ans = i;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
