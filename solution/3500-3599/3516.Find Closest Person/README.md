---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3516.Find%20Closest%20Person/README.md
rating: 1164
source: 第 445 场周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [3516. 找到最近的人](https://leetcode.cn/problems/find-closest-person)

[English Version](/solution/3500-3599/3516.Find%20Closest%20Person/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="116" data-start="0">给你三个整数 <code data-end="33" data-start="30">x</code>、<code data-end="38" data-start="35">y</code> 和 <code data-end="47" data-start="44">z</code>，表示数轴上三个人的位置：</p>

<ul data-end="252" data-start="118">
	<li data-end="154" data-start="118"><code data-end="123" data-start="120">x</code> 是第 1 个人的位置。</li>
	<li data-end="191" data-start="155"><code data-end="160" data-start="157">y</code> 是第 2 个人的位置。</li>
	<li data-end="252" data-start="192"><code data-end="197" data-start="194">z</code> 是第 3 个人的位置，第 3 个人&nbsp;<strong>不会移动&nbsp;</strong>。</li>
</ul>

<p data-end="322" data-start="254">第 1 个人和第 2 个人以&nbsp;<strong>相同&nbsp;</strong>的速度向第 3 个人移动。</p>

<p data-end="372" data-start="324">判断谁会&nbsp;<strong>先&nbsp;</strong>到达第 3 个人的位置：</p>

<ul data-end="505" data-start="374">
	<li data-end="415" data-start="374">如果第 1 个人先到达，返回 1 。</li>
	<li data-end="457" data-start="416">如果第 2 个人先到达，返回 2 。</li>
	<li data-end="505" data-start="458">如果两个人同时到达，返回 <strong>0&nbsp;</strong>。</li>
</ul>

<p data-end="537" data-is-last-node="" data-is-only-node="" data-start="507">根据上述规则返回结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">x = 2, y = 7, z = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul data-end="258" data-start="113">
	<li data-end="193" data-start="113">第 1 个人在位置 2，到达第 3 个人（位置 4）需要 2 步。</li>
	<li data-end="258" data-start="194">第 2 个人在位置 7，到达第 3 个人需要 3 步。</li>
</ul>

<p data-end="317" data-is-last-node="" data-is-only-node="" data-start="260">由于第 1 个人先到达，所以输出为 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">x = 2, y = 5, z = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul data-end="245" data-start="92">
	<li data-end="174" data-start="92">第 1 个人在位置 2，到达第 3 个人（位置 6）需要 4 步。</li>
	<li data-end="245" data-start="175">第 2 个人在位置 5，到达第 3 个人需要 1 步。</li>
</ul>

<p data-end="304" data-is-last-node="" data-is-only-node="" data-start="247">由于第 2 个人先到达，所以输出为 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">x = 1, y = 5, z = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul data-end="245" data-start="92">
	<li data-end="174" data-start="92">第 1 个人在位置 1，到达第 3 个人（位置 3）需要 2 步。</li>
	<li data-end="245" data-start="175">第 2 个人在位置 5，到达第 3 个人需要 2 步。</li>
</ul>

<p data-end="304" data-is-last-node="" data-is-only-node="" data-start="247">由于两个人同时到达，所以输出为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x, y, z &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们计算出第 $1$ 个人和第 $3$ 个人的距离 $a$，第 $2$ 个人和第 $3$ 个人的距离 $b$。

- 如果 $a = b$，说明两个人同时到达，返回 $0$；
- 如果 $a \lt b$，说明第 $1$ 个人会先到达，返回 $1$；
- 否则，说明第 $2$ 个人会先到达，返回 $2$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findClosest(self, x: int, y: int, z: int) -> int:
        a = abs(x - z)
        b = abs(y - z)
        return 0 if a == b else (1 if a < b else 2)
```

#### Java

```java
class Solution {
    public int findClosest(int x, int y, int z) {
        int a = Math.abs(x - z);
        int b = Math.abs(y - z);
        return a == b ? 0 : (a < b ? 1 : 2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findClosest(int x, int y, int z) {
        int a = abs(x - z);
        int b = abs(y - z);
        return a == b ? 0 : (a < b ? 1 : 2);
    }
};
```

#### Go

```go
func findClosest(x int, y int, z int) int {
	a, b := abs(x-z), abs(y-z)
	if a == b {
		return 0
	}
	if a < b {
		return 1
	}
	return 2
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
function findClosest(x: number, y: number, z: number): number {
    const a = Math.abs(x - z);
    const b = Math.abs(y - z);
    return a === b ? 0 : a < b ? 1 : 2;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_closest(x: i32, y: i32, z: i32) -> i32 {
        let a = (x - z).abs();
        let b = (y - z).abs();
        if a == b {
            0
        } else if a < b {
            1
        } else {
            2
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number} x
 * @param {number} y
 * @param {number} z
 * @return {number}
 */
var findClosest = function (x, y, z) {
    const a = Math.abs(x - z);
    const b = Math.abs(y - z);
    return a === b ? 0 : a < b ? 1 : 2;
};
```

#### C#

```cs
public class Solution {
    public int FindClosest(int x, int y, int z) {
        int a = Math.Abs(x - z);
        int b = Math.Abs(y - z);
        return a == b ? 0 : (a < b ? 1 : 2);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
