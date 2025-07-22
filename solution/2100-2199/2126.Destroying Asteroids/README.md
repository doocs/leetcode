---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2126.Destroying%20Asteroids/README.md
rating: 1334
source: 第 274 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [2126. 摧毁小行星](https://leetcode.cn/problems/destroying-asteroids)

[English Version](/solution/2100-2199/2126.Destroying%20Asteroids/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>mass</code>&nbsp;，它表示一颗行星的初始质量。再给你一个整数数组&nbsp;<code>asteroids</code>&nbsp;，其中&nbsp;<code>asteroids[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;颗小行星的质量。</p>

<p>你可以按 <strong>任意顺序</strong>&nbsp;重新安排小行星的顺序，然后让行星跟它们发生碰撞。如果行星碰撞时的质量 <strong>大于等于</strong>&nbsp;小行星的质量，那么小行星被 <strong>摧毁</strong>&nbsp;，并且行星会 <strong>获得</strong>&nbsp;这颗小行星的质量。否则，行星将被摧毁。</p>

<p>如果所有小行星 <strong>都</strong>&nbsp;能被摧毁，请返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>mass = 10, asteroids = [3,9,19,5,21]
<b>输出：</b>true
<b>解释：</b>一种安排小行星的方式为 [9,19,5,3,21] ：
- 行星与质量为 9 的小行星碰撞。新的行星质量为：10 + 9 = 19
- 行星与质量为 19 的小行星碰撞。新的行星质量为：19 + 19 = 38
- 行星与质量为 5 的小行星碰撞。新的行星质量为：38 + 5 = 43
- 行星与质量为 3 的小行星碰撞。新的行星质量为：43 + 3 = 46
- 行星与质量为 21 的小行星碰撞。新的行星质量为：46 + 21 = 67
所有小行星都被摧毁。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>mass = 5, asteroids = [4,9,23,4]
<b>输出：</b>false
<b>解释：</b>
行星无论如何没法获得足够质量去摧毁质量为 23 的小行星。
行星把别的小行星摧毁后，质量为 5 + 4 + 9 + 4 = 22 。
它比 23 小，所以无法摧毁最后一颗小行星。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= mass &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= asteroids.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= asteroids[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心

根据题目描述，我们可以将小行星按质量从小到大排序，然后依次遍历小行星，如果行星的质量小于小行星的质量，那么行星将被摧毁，返回 `false`，否则行星将获得这颗小行星的质量。

如果所有小行星都能被摧毁，返回 `true`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是小行星的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def asteroidsDestroyed(self, mass: int, asteroids: List[int]) -> bool:
        asteroids.sort()
        for x in asteroids:
            if mass < x:
                return False
            mass += x
        return True
```

#### Java

```java
class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long m = mass;
        for (int x : asteroids) {
            if (m < x) {
                return false;
            }
            m += x;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool asteroidsDestroyed(int mass, vector<int>& asteroids) {
        ranges::sort(asteroids);
        long long m = mass;
        for (int x : asteroids) {
            if (m < x) {
                return false;
            }
            m += x;
        }
        return true;
    }
};
```

#### Go

```go
func asteroidsDestroyed(mass int, asteroids []int) bool {
	sort.Ints(asteroids)
	for _, x := range asteroids {
		if mass < x {
			return false
		}
		mass += x
	}
	return true
}
```

#### TypeScript

```ts
function asteroidsDestroyed(mass: number, asteroids: number[]): boolean {
    asteroids.sort((a, b) => a - b);
    for (const x of asteroids) {
        if (mass < x) {
            return false;
        }
        mass += x;
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn asteroids_destroyed(mass: i32, mut asteroids: Vec<i32>) -> bool {
        let mut mass = mass as i64;
        asteroids.sort_unstable();
        for &x in &asteroids {
            if mass < x as i64 {
                return false;
            }
            mass += x as i64;
        }
        true
    }
}
```

#### JavaScript

```js
/**
 * @param {number} mass
 * @param {number[]} asteroids
 * @return {boolean}
 */
var asteroidsDestroyed = function (mass, asteroids) {
    asteroids.sort((a, b) => a - b);
    for (const x of asteroids) {
        if (mass < x) {
            return false;
        }
        mass += x;
    }
    return true;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
