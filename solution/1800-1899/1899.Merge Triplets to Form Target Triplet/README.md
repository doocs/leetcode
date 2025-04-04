---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1899.Merge%20Triplets%20to%20Form%20Target%20Triplet/README.md
rating: 1635
source: 第 245 场周赛 Q3
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [1899. 合并若干三元组以形成目标三元组](https://leetcode.cn/problems/merge-triplets-to-form-target-triplet)

[English Version](/solution/1800-1899/1899.Merge%20Triplets%20to%20Form%20Target%20Triplet/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>三元组</strong> 是一个由三个整数组成的数组。给你一个二维整数数组 <code>triplets</code> ，其中 <code>triplets[i] = [a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>]</code> 表示第 <code>i</code> 个 <strong>三元组</strong> 。同时，给你一个整数数组 <code>target = [x, y, z]</code> ，表示你想要得到的 <strong>三元组</strong> 。</p>

<p>为了得到 <code>target</code> ，你需要对 <code>triplets</code> 执行下面的操作 <strong>任意次</strong>（可能 <strong>零</strong> 次）：</p>

<ul>
	<li>选出两个下标（下标 <strong>从 0 开始</strong> 计数）<code>i</code> 和 <code>j</code>（<code>i != j</code>），并 <strong>更新</strong> <code>triplets[j]</code> 为 <code>[max(a<sub>i</sub>, a<sub>j</sub>), max(b<sub>i</sub>, b<sub>j</sub>), max(c<sub>i</sub>, c<sub>j</sub>)]</code> 。

    <ul>
    	<li>例如，<code>triplets[i] = [2, 5, 3]</code> 且 <code>triplets[j] = [1, 7, 5]</code>，<code>triplets[j]</code> 将会更新为 <code>[max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5]</code> 。</li>
    </ul>
    </li>

</ul>

<p>如果通过以上操作我们可以使得目标 <strong>三元组</strong> <code>target</code> 成为 <code>triplets</code> 的一个 <strong>元素</strong> ，则返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
<strong>输出：</strong>true
<strong>解释：</strong>执行下述操作：
- 选择第一个和最后一个三元组 [<strong>[2,5,3]</strong>,[1,8,4],<strong>[1,7,5]</strong>] 。更新最后一个三元组为 [max(2,1), max(5,7), max(3,5)] = [2,7,5] 。triplets = [[2,5,3],[1,8,4],<strong>[2,7,5]</strong>]
目标三元组 [2,7,5] 现在是 triplets 的一个元素。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>triplets = [[1,3,4],[2,5,8]], target = [2,5,8]
<strong>输出：</strong>true
<strong>解释：</strong>目标三元组 [2,5,8] 已经是 triplets 的一个元素。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
<strong>输出：</strong>true
<strong>解释：</strong>执行下述操作：
- 选择第一个和第三个三元组 [<strong>[2,5,3]</strong>,[2,3,4],<strong>[1,2,5]</strong>,[5,2,3]] 。更新第三个三元组为 [max(2,1), max(5,2), max(3,5)] = [2,5,5] 。triplets = [[2,5,3],[2,3,4],<strong>[2,5,5]</strong>,[5,2,3]] 。
- 选择第三个和第四个三元组 [[2,5,3],[2,3,4],<strong>[2,5,5]</strong>,<strong>[5,2,3]</strong>] 。更新第四个三元组为 [max(2,5), max(5,2), max(5,3)] = [5,5,5] 。triplets = [[2,5,3],[2,3,4],[2,5,5],<strong>[5,5,5]</strong>] 。
目标三元组 [5,5,5] 现在是 triplets 的一个元素。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>triplets = [[3,4,5],[4,5,6]], target = [3,2,5]
<strong>输出：</strong>false
<strong>解释：</strong>无法得到 [3,2,5] ，因为 triplets 不含 2 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= triplets.length <= 10<sup>5</sup></code></li>
	<li><code>triplets[i].length == target.length == 3</code></li>
	<li><code>1 <= a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>, x, y, z <= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们记 $\textit{target} = [x, y, z]$，我们需要判断是否存在三元组 $[a, b, c]$ 使得 $a \le x, b \le y, c \le z$。

我们可以将所有的三元组分为两类：

1. 满足 $a \le x, b \le y, c \le z$ 的三元组。
2. 不满足 $a \le x, b \le y, c \le z$ 的三元组。

对于第一类三元组，我们可以将它们的 $a, b, c$ 分别取最大值，得到一个新的三元组 $[d, e, f]$。

对于第二类三元组，我们可以忽略它们，因为它们无法帮助我们得到目标三元组。

最后，我们只需要判断 $[d, e, f]$ 是否等于 $\textit{target}$ 即可。如果等于，返回 $\textit{true}$，否则返回 $\textit{false}$。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{triplets}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mergeTriplets(self, triplets: List[List[int]], target: List[int]) -> bool:
        x, y, z = target
        d = e = f = 0
        for a, b, c in triplets:
            if a <= x and b <= y and c <= z:
                d = max(d, a)
                e = max(e, b)
                f = max(f, c)
        return [d, e, f] == target
```

#### Java

```java
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int x = target[0], y = target[1], z = target[2];
        int d = 0, e = 0, f = 0;
        for (var t : triplets) {
            int a = t[0], b = t[1], c = t[2];
            if (a <= x && b <= y && c <= z) {
                d = Math.max(d, a);
                e = Math.max(e, b);
                f = Math.max(f, c);
            }
        }
        return d == x && e == y && f == z;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool mergeTriplets(vector<vector<int>>& triplets, vector<int>& target) {
        int x = target[0], y = target[1], z = target[2];
        int d = 0, e = 0, f = 0;
        for (auto& t : triplets) {
            int a = t[0], b = t[1], c = t[2];
            if (a <= x && b <= y && c <= z) {
                d = max(d, a);
                e = max(e, b);
                f = max(f, c);
            }
        }
        return d == x && e == y && f == z;
    }
};
```

#### Go

```go
func mergeTriplets(triplets [][]int, target []int) bool {
	x, y, z := target[0], target[1], target[2]
	d, e, f := 0, 0, 0
	for _, t := range triplets {
		a, b, c := t[0], t[1], t[2]
		if a <= x && b <= y && c <= z {
			d = max(d, a)
			e = max(e, b)
			f = max(f, c)
		}
	}
	return d == x && e == y && f == z
}
```

#### TypeScript

```ts
function mergeTriplets(triplets: number[][], target: number[]): boolean {
    const [x, y, z] = target;
    let [d, e, f] = [0, 0, 0];
    for (const [a, b, c] of triplets) {
        if (a <= x && b <= y && c <= z) {
            d = Math.max(d, a);
            e = Math.max(e, b);
            f = Math.max(f, c);
        }
    }
    return d === x && e === y && f === z;
}
```

#### Rust

```rust
impl Solution {
    pub fn merge_triplets(triplets: Vec<Vec<i32>>, target: Vec<i32>) -> bool {
        let [x, y, z]: [i32; 3] = target.try_into().unwrap();
        let (mut d, mut e, mut f) = (0, 0, 0);

        for triplet in triplets {
            if let [a, b, c] = triplet[..] {
                if a <= x && b <= y && c <= z {
                    d = d.max(a);
                    e = e.max(b);
                    f = f.max(c);
                }
            }
        }

        [d, e, f] == [x, y, z]
    }
}
```

#### Scala

```scala
object Solution {
    def mergeTriplets(triplets: Array[Array[Int]], target: Array[Int]): Boolean = {
        val Array(x, y, z) = target
        var (d, e, f) = (0, 0, 0)

        for (Array(a, b, c) <- triplets) {
            if (a <= x && b <= y && c <= z) {
                d = d.max(a)
                e = e.max(b)
                f = f.max(c)
            }
        }

        d == x && e == y && f == z
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
