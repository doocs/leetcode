---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/README.md
rating: 1234
source: 第 153 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [1184. 公交站间的距离](https://leetcode.cn/problems/distance-between-bus-stops)

[English Version](/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/README_EN.md)

## 题目描述

<!-- description:start -->

<p>环形公交路线上有&nbsp;<code>n</code>&nbsp;个站，按次序从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;进行编号。我们已知每一对相邻公交站之间的距离，<code>distance[i]</code>&nbsp;表示编号为&nbsp;<code>i</code>&nbsp;的车站和编号为&nbsp;<code>(i + 1) % n</code>&nbsp;的车站之间的距离。</p>

<p>环线上的公交车都可以按顺时针和逆时针的方向行驶。</p>

<p>返回乘客从出发点&nbsp;<code>start</code>&nbsp;到目的地&nbsp;<code>destination</code>&nbsp;之间的最短距离。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1.jpg" style="height: 240px; width: 388px;"></p>

<pre><strong>输入：</strong>distance = [1,2,3,4], start = 0, destination = 1
<strong>输出：</strong>1
<strong>解释：</strong>公交站 0 和 1 之间的距离是 1 或 9，最小值是 1。</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1-1.jpg" style="height: 240px; width: 388px;"></p>

<pre><strong>输入：</strong>distance = [1,2,3,4], start = 0, destination = 2
<strong>输出：</strong>3
<strong>解释：</strong>公交站 0 和 2 之间的距离是 3 或 7，最小值是 3。
</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1184.Distance%20Between%20Bus%20Stops/images/untitled-diagram-1-2.jpg" style="height: 240px; width: 388px;"></p>

<pre><strong>输入：</strong>distance = [1,2,3,4], start = 0, destination = 3
<strong>输出：</strong>4
<strong>解释：</strong>公交站 0 和 3 之间的距离是 6 或 4，最小值是 4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n&nbsp;&lt;= 10^4</code></li>
	<li><code>distance.length == n</code></li>
	<li><code>0 &lt;= start, destination &lt; n</code></li>
	<li><code>0 &lt;= distance[i] &lt;= 10^4</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以先统计出公交车的总行驶距离 $s$，然后模拟公交车的行驶过程，从出发点开始，每次向右移动一站，直到到达目的地为止，记录下这个过程中的行驶距离 $t$，最后返回 $t$ 和 $s - t$ 中的最小值即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{distance}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def distanceBetweenBusStops(
        self, distance: List[int], start: int, destination: int
    ) -> int:
        s = sum(distance)
        t, n = 0, len(distance)
        while start != destination:
            t += distance[start]
            start = (start + 1) % n
        return min(t, s - t)
```

#### Java

```java
class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int s = Arrays.stream(distance).sum();
        int n = distance.length, t = 0;
        while (start != destination) {
            t += distance[start];
            start = (start + 1) % n;
        }
        return Math.min(t, s - t);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int distanceBetweenBusStops(vector<int>& distance, int start, int destination) {
        int s = accumulate(distance.begin(), distance.end(), 0);
        int t = 0, n = distance.size();
        while (start != destination) {
            t += distance[start];
            start = (start + 1) % n;
        }
        return min(t, s - t);
    }
};
```

#### Go

```go
func distanceBetweenBusStops(distance []int, start int, destination int) int {
	s, t := 0, 0
	for _, x := range distance {
		s += x
	}
	for start != destination {
		t += distance[start]
		start = (start + 1) % len(distance)
	}
	return min(t, s-t)
}
```

#### TypeScript

```ts
function distanceBetweenBusStops(distance: number[], start: number, destination: number): number {
    const s = distance.reduce((a, b) => a + b, 0);
    const n = distance.length;
    let t = 0;
    while (start !== destination) {
        t += distance[start];
        start = (start + 1) % n;
    }
    return Math.min(t, s - t);
}
```

#### Rust

```rust
impl Solution {
    pub fn distance_between_bus_stops(distance: Vec<i32>, start: i32, destination: i32) -> i32 {
        let s: i32 = distance.iter().sum();
        let mut t = 0;
        let n = distance.len();
        let mut start = start as usize;
        let destination = destination as usize;

        while start != destination {
            t += distance[start];
            start = (start + 1) % n;
        }

        t.min(s - t)
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} distance
 * @param {number} start
 * @param {number} destination
 * @return {number}
 */
var distanceBetweenBusStops = function (distance, start, destination) {
    const s = distance.reduce((a, b) => a + b, 0);
    const n = distance.length;
    let t = 0;
    while (start !== destination) {
        t += distance[start];
        start = (start + 1) % n;
    }
    return Math.min(t, s - t);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
