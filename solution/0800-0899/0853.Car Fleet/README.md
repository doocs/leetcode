---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0853.Car%20Fleet/README.md
tags:
    - 栈
    - 数组
    - 排序
    - 单调栈
---

<!-- problem:start -->

# [853. 车队](https://leetcode.cn/problems/car-fleet)

[English Version](/solution/0800-0899/0853.Car%20Fleet/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在一条单行道上，有 <code>n</code> 辆车开往同一目的地。目的地是几英里以外的&nbsp;<code>target</code>&nbsp;。</p>

<p>给定两个整数数组&nbsp;<code>position</code>&nbsp;和&nbsp;<code>speed</code>&nbsp;，长度都是 <code>n</code> ，其中&nbsp;<code>position[i]</code>&nbsp;是第 <code>i</code> 辆车的位置，&nbsp;<code>speed[i]</code>&nbsp;是第 <code>i</code> 辆车的速度(单位是英里/小时)。</p>

<p>一辆车永远不会超过前面的另一辆车，但它可以追上去，并以较慢车的速度在另一辆车旁边行驶。</p>

<p><strong>车队 </strong>是指并排行驶的一辆或几辆汽车。车队的速度是车队中 <strong>最慢</strong> 的车的速度。</p>

<p>即便一辆车在&nbsp;<code>target</code> 才赶上了一个车队，它们仍然会被视作是同一个车队。</p>

<p>返回到达目的地的车队数量 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>从 10（速度为 2）和 8（速度为 4）开始的车会组成一个车队，它们在 12 相遇。车队在&nbsp;<code>target</code>&nbsp;形成。</li>
	<li>从 0（速度为 1）开始的车不会追上其它任何车，所以它自己是一个车队。</li>
	<li>从 5（速度为 1） 和 3（速度为 3）开始的车组成一个车队，在 6 相遇。车队以速度 1 移动直到它到达&nbsp;<code>target</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">target = 10, position = [3], speed = [3]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>
只有一辆车，因此只有一个车队。</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">target = 100, position = [0,2,4], speed = [4,2,1]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>从 0（速度为 4） 和 2（速度为 2）开始的车组成一个车队，在 4&nbsp;相遇。从 4 开始的车（速度为 1）移动到了 5。</li>
	<li>然后，在 4（速度为 2）的车队和在 5（速度为 1）的车成为一个车队，在 6 相遇。车队以速度 1 移动直到它到达&nbsp;<code>target</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == position.length == speed.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt; target &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= position[i] &lt; target</code></li>
	<li><code>position</code>&nbsp;中每个值都 <strong>不同</strong></li>
	<li><code>0 &lt; speed[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们将车辆按照位置降序排序，这样我们只需要比较相邻两辆车的到达时间即可。

我们初始化一个变量 $pre$ 表示上一辆车到达终点的时间，如果当前车辆到达终点的时间大于 $pre$，说明当前车辆无法追上前面的车辆，因此需要另外开一个车队，否则当前车辆会与前面的车辆组成一个车队。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是车辆的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        idx = sorted(range(len(position)), key=lambda i: position[i])
        ans = pre = 0
        for i in idx[::-1]:
            t = (target - position[i]) / speed[i]
            if t > pre:
                ans += 1
                pre = t
        return ans
```

#### Java

```java
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> position[j] - position[i]);
        int ans = 0;
        double pre = 0;
        for (int i : idx) {
            double t = 1.0 * (target - position[i]) / speed[i];
            if (t > pre) {
                ++ans;
                pre = t;
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
    int carFleet(int target, vector<int>& position, vector<int>& speed) {
        int n = position.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return position[i] > position[j];
        });
        int ans = 0;
        double pre = 0;
        for (int i : idx) {
            double t = 1.0 * (target - position[i]) / speed[i];
            if (t > pre) {
                ++ans;
                pre = t;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func carFleet(target int, position []int, speed []int) (ans int) {
	n := len(position)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return position[idx[j]] < position[idx[i]] })
	var pre float64
	for _, i := range idx {
		t := float64(target-position[i]) / float64(speed[i])
		if t > pre {
			ans++
			pre = t
		}
	}
	return
}
```

#### TypeScript

```ts
function carFleet(target: number, position: number[], speed: number[]): number {
    const n = position.length;
    const idx = Array(n)
        .fill(0)
        .map((_, i) => i)
        .sort((i, j) => position[j] - position[i]);
    let ans = 0;
    let pre = 0;
    for (const i of idx) {
        const t = (target - position[i]) / speed[i];
        if (t > pre) {
            ++ans;
            pre = t;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
