---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0735.Asteroid%20Collision/README.md
tags:
    - 栈
    - 数组
    - 模拟
---

<!-- problem:start -->

# [735. 小行星碰撞](https://leetcode.cn/problems/asteroid-collision)

[English Version](/solution/0700-0799/0735.Asteroid%20Collision/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>asteroids</code>，表示在同一行的小行星。数组中小行星的索引表示它们在空间中的相对位置。</p>

<p>对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。</p>

<p>找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远不会发生碰撞。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>asteroids = [5,10,-5]
<strong>输出：</strong>[5,10]
<b>解释：</b>10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>asteroids = [8,-8]
<strong>输出：</strong>[]
<b>解释：</b>8 和 -8 碰撞后，两者都发生爆炸。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>asteroids = [10,2,-5]
<strong>输出：</strong>[10]
<b>解释：</b>2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。</pre>

<p><strong class="example">示例 4：</strong></p>

<pre>
<b>输入：</b>asteroids = [3,5,-6,2,-1,4]
<b>输出：</b>[-6,2,4]
<b>解释：</b>小行星 -6 使小行星 3 和 5 爆炸，然后继续向左移动。在另一边，小行星 2 使小行星 -1 爆炸，然后继续向右移动，没有碰撞小行星 4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= asteroids.length&nbsp;&lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= asteroids[i] &lt;= 1000</code></li>
	<li><code>asteroids[i] != 0</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈

我们从左到右遍历每个小行星 $x$，由于每个小行星可能与之前的多个小行星发生碰撞，考虑用栈来存储。

-   对于当前小行星，如果 $x \gt 0$，那么它一定不会跟前面的小行星发生碰撞，我们可以直接将 $x$ 入栈。
-   否则，如果栈不为空并且栈顶元素大于 $0$，且栈顶元素小于 $-x$，那么栈顶元素对应的小行星会发生爆炸，我们循环将栈顶元素出栈，直到不满足条件。此时如果栈顶元素等于 $-x$，那么两个小行星会发生爆炸，只需要将栈顶元素出栈即可；如果栈为空，或者栈顶元素小于 $0$，那么当前小行星不会发生碰撞，我们将 $x$ 入栈。

最后我们返回栈中的元素即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $asteroids$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        stk = []
        for x in asteroids:
            if x > 0:
                stk.append(x)
            else:
                while stk and stk[-1] > 0 and stk[-1] < -x:
                    stk.pop()
                if stk and stk[-1] == -x:
                    stk.pop()
                elif not stk or stk[-1] < 0:
                    stk.append(x)
        return stk
```

#### Java

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (int x : asteroids) {
            if (x > 0) {
                stk.offerLast(x);
            } else {
                while (!stk.isEmpty() && stk.peekLast() > 0 && stk.peekLast() < -x) {
                    stk.pollLast();
                }
                if (!stk.isEmpty() && stk.peekLast() == -x) {
                    stk.pollLast();
                } else if (stk.isEmpty() || stk.peekLast() < 0) {
                    stk.offerLast(x);
                }
            }
        }
        return stk.stream().mapToInt(Integer::valueOf).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {
        vector<int> stk;
        for (int x : asteroids) {
            if (x > 0) {
                stk.push_back(x);
            } else {
                while (stk.size() && stk.back() > 0 && stk.back() < -x) {
                    stk.pop_back();
                }
                if (stk.size() && stk.back() == -x) {
                    stk.pop_back();
                } else if (stk.empty() || stk.back() < 0) {
                    stk.push_back(x);
                }
            }
        }
        return stk;
    }
};
```

#### Go

```go
func asteroidCollision(asteroids []int) (stk []int) {
	for _, x := range asteroids {
		if x > 0 {
			stk = append(stk, x)
		} else {
			for len(stk) > 0 && stk[len(stk)-1] > 0 && stk[len(stk)-1] < -x {
				stk = stk[:len(stk)-1]
			}
			if len(stk) > 0 && stk[len(stk)-1] == -x {
				stk = stk[:len(stk)-1]
			} else if len(stk) == 0 || stk[len(stk)-1] < 0 {
				stk = append(stk, x)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function asteroidCollision(asteroids: number[]): number[] {
    const stk: number[] = [];
    for (const x of asteroids) {
        if (x > 0) {
            stk.push(x);
        } else {
            while (stk.length && stk.at(-1) > 0 && stk.at(-1) < -x) {
                stk.pop();
            }
            if (stk.length && stk.at(-1) === -x) {
                stk.pop();
            } else if (!stk.length || stk.at(-1) < 0) {
                stk.push(x);
            }
        }
    }
    return stk;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn asteroid_collision(asteroids: Vec<i32>) -> Vec<i32> {
        let mut stk = Vec::new();
        for &x in &asteroids {
            if x > 0 {
                stk.push(x);
            } else {
                while !stk.is_empty() && *stk.last().unwrap() > 0 && *stk.last().unwrap() < -x {
                    stk.pop();
                }
                if !stk.is_empty() && *stk.last().unwrap() == -x {
                    stk.pop();
                } else if stk.is_empty() || *stk.last().unwrap() < 0 {
                    stk.push(x);
                }
            }
        }
        stk
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
