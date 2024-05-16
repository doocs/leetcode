---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2660.Determine%20the%20Winner%20of%20a%20Bowling%20Game/README.md
rating: 1324
source: 第 343 场周赛 Q1
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [2660. 保龄球游戏的获胜者](https://leetcode.cn/problems/determine-the-winner-of-a-bowling-game)

[English Version](/solution/2600-2699/2660.Determine%20the%20Winner%20of%20a%20Bowling%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong> 开始的整数数组 <code>player1</code> 和 <code>player2</code> ，分别表示玩家 1 和玩家 2 击中的瓶数。</p>

<p>保龄球比赛由 <code>n</code> 轮组成，每轮的瓶数恰好为 <code>10</code> 。</p>

<p>假设玩家在第 <code>i</code> 轮中击中&nbsp;<code>x<sub>i</sub></code> 个瓶子。玩家第 <code>i</code> 轮的价值为：</p>

<ul>
	<li>如果玩家在该轮的前两轮的任何一轮中击中了 <code>10</code> 个瓶子，则为 <code>2x<sub>i</sub></code> 。</li>
	<li>否则，为&nbsp;<code>x<sub>i</sub></code> 。</li>
</ul>

<p>玩家的得分是其 <code>n</code> 轮价值的总和。</p>

<p>返回</p>

<ul>
	<li>如果玩家 1 的得分高于玩家 2 的得分，则为 <code>1</code> ；</li>
	<li>如果玩家 2 的得分高于玩家 1 的得分，则为 <code>2</code> ；</li>
	<li>如果平局，则为 <code>0</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>player1 = [4,10,7,9], player2 = [6,5,2,3]
<strong>输出：</strong>1
<strong>解释：</strong>player1 的得分是 4 + 10 + 2*7 + 2*9 = 46 。
player2 的得分是 6 + 5 + 2 + 3 = 16 。
player1 的得分高于 player2 的得分，所以 play1 在比赛中获胜，答案为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>player1 = [3,5,7,6], player2 = [8,10,10,2]
<strong>输出：</strong>2
<strong>解释：</strong>player1 的得分是 3 + 5 + 7 + 6 = 21 。
player2 的得分是 8 + 10 + 2*10 + 2*2 = 42 。
player2 的得分高于 player1 的得分，所以 play2 在比赛中获胜，答案为 2 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>player1 = [2,3], player2 = [4,1]
<strong>输出：</strong>0
<strong>解释：</strong>player1 的得分是 2 + 3 = 5 。
player2 的得分是 4 + 1 = 5 。
player1 的得分等于 player2 的得分，所以这一场比赛平局，答案为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == player1.length == player2.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= player1[i], player2[i] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以用一个函数 $f(arr)$ 计算出两个玩家的得分，分别记为 $a$ 和 $b$，然后根据 $a$ 和 $b$ 的大小关系返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def isWinner(self, player1: List[int], player2: List[int]) -> int:
        def f(arr: List[int]) -> int:
            s = 0
            for i, x in enumerate(arr):
                k = 2 if (i and arr[i - 1] == 10) or (i > 1 and arr[i - 2] == 10) else 1
                s += k * x
            return s

        a, b = f(player1), f(player2)
        return 1 if a > b else (2 if b > a else 0)
```

```java
class Solution {
    public int isWinner(int[] player1, int[] player2) {
        int a = f(player1), b = f(player2);
        return a > b ? 1 : b > a ? 2 : 0;
    }

    private int f(int[] arr) {
        int s = 0;
        for (int i = 0; i < arr.length; ++i) {
            int k = (i > 0 && arr[i - 1] == 10) || (i > 1 && arr[i - 2] == 10) ? 2 : 1;
            s += k * arr[i];
        }
        return s;
    }
}
```

```cpp
class Solution {
public:
    int isWinner(vector<int>& player1, vector<int>& player2) {
        auto f = [](vector<int>& arr) {
            int s = 0;
            for (int i = 0, n = arr.size(); i < n; ++i) {
                int k = (i && arr[i - 1] == 10) || (i > 1 && arr[i - 2] == 10) ? 2 : 1;
                s += k * arr[i];
            }
            return s;
        };
        int a = f(player1), b = f(player2);
        return a > b ? 1 : (b > a ? 2 : 0);
    }
};
```

```go
func isWinner(player1 []int, player2 []int) int {
	f := func(arr []int) int {
		s := 0
		for i, x := range arr {
			k := 1
			if (i > 0 && arr[i-1] == 10) || (i > 1 && arr[i-2] == 10) {
				k = 2
			}
			s += k * x
		}
		return s
	}
	a, b := f(player1), f(player2)
	if a > b {
		return 1
	}
	if b > a {
		return 2
	}
	return 0
}
```

```ts
function isWinner(player1: number[], player2: number[]): number {
    const f = (arr: number[]): number => {
        let s = 0;
        for (let i = 0; i < arr.length; ++i) {
            s += arr[i];
            if ((i && arr[i - 1] === 10) || (i > 1 && arr[i - 2] === 10)) {
                s += arr[i];
            }
        }
        return s;
    };
    const a = f(player1);
    const b = f(player2);
    return a > b ? 1 : a < b ? 2 : 0;
}
```

```rust
impl Solution {
    pub fn is_winner(player1: Vec<i32>, player2: Vec<i32>) -> i32 {
        let f = |arr: &Vec<i32>| -> i32 {
            let mut s = 0;
            for i in 0..arr.len() {
                let mut k = 1;
                if (i > 0 && arr[i - 1] == 10) || (i > 1 && arr[i - 2] == 10) {
                    k = 2;
                }
                s += k * arr[i];
            }
            s
        };

        let a = f(&player1);
        let b = f(&player2);
        if a > b {
            1
        } else if a < b {
            2
        } else {
            0
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
