---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1561.Maximum%20Number%20of%20Coins%20You%20Can%20Get/README.md
rating: 1405
source: 第 203 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 数学
    - 博弈
    - 排序
---

<!-- problem:start -->

# [1561. 你可以获得的最大硬币数目](https://leetcode.cn/problems/maximum-number-of-coins-you-can-get)

[English Version](/solution/1500-1599/1561.Maximum%20Number%20of%20Coins%20You%20Can%20Get/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：</p>

<ul>
	<li>每一轮中，你将会选出 <strong>任意</strong> 3 堆硬币（不一定连续）。</li>
	<li>Alice 将会取走硬币数量最多的那一堆。</li>
	<li>你将会取走硬币数量第二多的那一堆。</li>
	<li>Bob 将会取走最后一堆。</li>
	<li>重复这个过程，直到没有更多硬币。</li>
</ul>

<p>给你一个整数数组 <code>piles</code> ，其中 <code>piles[i]</code> 是第 <code>i</code> 堆中硬币的数目。</p>

<p>返回你可以获得的最大硬币数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>piles = [2,4,1,2,7,8]
<strong>输出：</strong>9
<strong>解释：</strong>选出 (2, 7, 8) ，Alice 取走 8 枚硬币的那堆，你取走 <strong>7</strong> 枚硬币的那堆，Bob 取走最后一堆。
选出 (1, 2, 4) , Alice 取走 4 枚硬币的那堆，你取走 <strong>2</strong> 枚硬币的那堆，Bob 取走最后一堆。
你可以获得的最大硬币数目：7 + 2 = 9.
考虑另外一种情况，如果选出的是 (1, <strong>2</strong>, 8) 和 (2, <strong>4</strong>, 7) ，你就只能得到 2 + 4 = 6 枚硬币，这不是最优解。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>piles = [2,4,5]
<strong>输出：</strong>4
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>piles = [9,8,7,6,5,1,2,3,4]
<strong>输出：</strong>18
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= piles.length &lt;= 10^5</code></li>
	<li><code>piles.length % 3 == 0</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10^4</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

为了让我们获得的硬币数量最多，我们可以贪心地让 Bob 拿走最少的 $n$ 堆硬币。我们每次先让 Alice 拿走最多的一堆硬币，然后让我们拿走第二多的一堆硬币，依次循环，直到没有硬币可拿。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是硬币堆数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCoins(self, piles: List[int]) -> int:
        piles.sort()
        return sum(piles[len(piles) // 3 :][::2])
```

#### Java

```java
class Solution {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int ans = 0;
        for (int i = piles.length / 3; i < piles.length; i += 2) {
            ans += piles[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxCoins(vector<int>& piles) {
        ranges::sort(piles);
        int ans = 0;
        for (int i = piles.size() / 3; i < piles.size(); i += 2) {
            ans += piles[i];
        }
        return ans;
    }
};
```

#### Go

```go
func maxCoins(piles []int) (ans int) {
	sort.Ints(piles)
	for i := len(piles) / 3; i < len(piles); i += 2 {
		ans += piles[i]
	}
	return
}
```

#### TypeScript

```ts
function maxCoins(piles: number[]): number {
    piles.sort((a, b) => a - b);
    let ans = 0;
    for (let i = piles.length / 3; i < piles.length; i += 2) {
        ans += piles[i];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_coins(mut piles: Vec<i32>) -> i32 {
        piles.sort();
        let mut ans = 0;
        for i in (piles.len() / 3..piles.len()).step_by(2) {
            ans += piles[i];
        }
        ans
    }
}
```

#### C

```c
int compare(const void* a, const void* b) {
    return (*(int*) a - *(int*) b);
}

int maxCoins(int* piles, int pilesSize) {
    qsort(piles, pilesSize, sizeof(int), compare);
    int ans = 0;
    for (int i = pilesSize / 3; i < pilesSize; i += 2) {
        ans += piles[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
