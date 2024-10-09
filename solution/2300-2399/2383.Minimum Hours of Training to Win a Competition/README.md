---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2383.Minimum%20Hours%20of%20Training%20to%20Win%20a%20Competition/README.md
rating: 1413
source: 第 307 场周赛 Q1
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [2383. 赢得比赛需要的最少训练时长](https://leetcode.cn/problems/minimum-hours-of-training-to-win-a-competition)

[English Version](/solution/2300-2399/2383.Minimum%20Hours%20of%20Training%20to%20Win%20a%20Competition/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在参加一场比赛，给你两个 <strong>正</strong> 整数 <code>initialEnergy</code> 和 <code>initialExperience</code> 分别表示你的初始精力和初始经验。</p>

<p>另给你两个下标从 <strong>0</strong> 开始的整数数组 <code>energy</code> 和 <code>experience</code>，长度均为 <code>n</code> 。</p>

<p>你将会 <strong>依次</strong> 对上 <code>n</code> 个对手。第 <code>i</code> 个对手的精力和经验分别用 <code>energy[i]</code> 和 <code>experience[i]</code> 表示。当你对上对手时，需要在经验和精力上都 <strong>严格</strong> 超过对手才能击败他们，然后在可能的情况下继续对上下一个对手。</p>

<p>击败第 <code>i</code> 个对手会使你的经验 <strong>增加</strong> <code>experience[i]</code>，但会将你的精力 <strong>减少</strong>&nbsp; <code>energy[i]</code> 。</p>

<p>在开始比赛前，你可以训练几个小时。每训练一个小时，你可以选择将增加经验增加 1 <strong>或者</strong> 将精力增加 1 。</p>

<p>返回击败全部 <code>n</code> 个对手需要训练的 <strong>最少</strong> 小时数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
<strong>输出：</strong>8
<strong>解释：</strong>在 6 小时训练后，你可以将精力提高到 11 ，并且再训练 2 个小时将经验提高到 5 。
按以下顺序与对手比赛：
- 你的精力与经验都超过第 0 个对手，所以获胜。
  精力变为：11 - 1 = 10 ，经验变为：5 + 2 = 7 。
- 你的精力与经验都超过第 1 个对手，所以获胜。
  精力变为：10 - 4 = 6 ，经验变为：7 + 6 = 13 。
- 你的精力与经验都超过第 2 个对手，所以获胜。
  精力变为：6 - 3 = 3 ，经验变为：13 + 3 = 16 。
- 你的精力与经验都超过第 3 个对手，所以获胜。
  精力变为：3 - 2 = 1 ，经验变为：16 + 1 = 17 。
在比赛前进行了 8 小时训练，所以返回 8 。
可以证明不存在更小的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>initialEnergy = 2, initialExperience = 4, energy = [1], experience = [3]
<strong>输出：</strong>0
<strong>解释：</strong>你不需要额外的精力和经验就可以赢得比赛，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == energy.length == experience.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= initialEnergy, initialExperience, energy[i], experience[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 模拟

我们不妨记当前的精力为 $x$，经验为 $y$。

接下来，我们遍历每个对手，对于第 $i$ 个对手，记其精力为 $dx$，经验为 $dy$。

-   如果 $x \leq dx$，那么我们需要训练 $dx + 1 - x$ 小时，将精力提升到 $dx + 1$；
-   如果 $y \leq dy$，那么我们需要训练 $dy + 1 - y$ 小时，将经验提升到 $dy + 1$；
-   然后我们将精力减去 $dx$，经验加上 $dy$。

最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为对手的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minNumberOfHours(
        self, x: int, y: int, energy: List[int], experience: List[int]
    ) -> int:
        ans = 0
        for dx, dy in zip(energy, experience):
            if x <= dx:
                ans += dx + 1 - x
                x = dx + 1
            if y <= dy:
                ans += dy + 1 - y
                y = dy + 1
            x -= dx
            y += dy
        return ans
```

#### Java

```java
class Solution {
    public int minNumberOfHours(int x, int y, int[] energy, int[] experience) {
        int ans = 0;
        for (int i = 0; i < energy.length; ++i) {
            int dx = energy[i], dy = experience[i];
            if (x <= dx) {
                ans += dx + 1 - x;
                x = dx + 1;
            }
            if (y <= dy) {
                ans += dy + 1 - y;
                y = dy + 1;
            }
            x -= dx;
            y += dy;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minNumberOfHours(int x, int y, vector<int>& energy, vector<int>& experience) {
        int ans = 0;
        for (int i = 0; i < energy.size(); ++i) {
            int dx = energy[i], dy = experience[i];
            if (x <= dx) {
                ans += dx + 1 - x;
                x = dx + 1;
            }
            if (y <= dy) {
                ans += dy + 1 - y;
                y = dy + 1;
            }
            x -= dx;
            y += dy;
        }
        return ans;
    }
};
```

#### Go

```go
func minNumberOfHours(x int, y int, energy []int, experience []int) (ans int) {
	for i, dx := range energy {
		dy := experience[i]
		if x <= dx {
			ans += dx + 1 - x
			x = dx + 1
		}
		if y <= dy {
			ans += dy + 1 - y
			y = dy + 1
		}
		x -= dx
		y += dy
	}
	return
}
```

#### TypeScript

```ts
function minNumberOfHours(x: number, y: number, energy: number[], experience: number[]): number {
    let ans = 0;
    for (let i = 0; i < energy.length; ++i) {
        const [dx, dy] = [energy[i], experience[i]];
        if (x <= dx) {
            ans += dx + 1 - x;
            x = dx + 1;
        }
        if (y <= dy) {
            ans += dy + 1 - y;
            y = dy + 1;
        }
        x -= dx;
        y += dy;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_number_of_hours(
        mut x: i32,
        mut y: i32,
        energy: Vec<i32>,
        experience: Vec<i32>,
    ) -> i32 {
        let mut ans = 0;

        for (&dx, &dy) in energy.iter().zip(experience.iter()) {
            if x <= dx {
                ans += dx + 1 - x;
                x = dx + 1;
            }
            if y <= dy {
                ans += dy + 1 - y;
                y = dy + 1;
            }
            x -= dx;
            y += dy;
        }

        ans
    }
}
```

#### C

```c
int minNumberOfHours(int x, int y, int* energy, int energySize, int* experience, int experienceSize) {
    int ans = 0;
    for (int i = 0; i < energySize; ++i) {
        int dx = energy[i], dy = experience[i];
        if (x <= dx) {
            ans += dx + 1 - x;
            x = dx + 1;
        }
        if (y <= dy) {
            ans += dy + 1 - y;
            y = dy + 1;
        }
        x -= dx;
        y += dy;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
