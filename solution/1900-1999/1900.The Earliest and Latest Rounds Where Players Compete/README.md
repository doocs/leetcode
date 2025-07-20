---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1900.The%20Earliest%20and%20Latest%20Rounds%20Where%20Players%20Compete/README.md
rating: 2454
source: 第 245 场周赛 Q4
tags:
    - 记忆化搜索
    - 动态规划
---

<!-- problem:start -->

# [1900. 最佳运动员的比拼回合](https://leetcode.cn/problems/the-earliest-and-latest-rounds-where-players-compete)

[English Version](/solution/1900-1999/1900.The%20Earliest%20and%20Latest%20Rounds%20Where%20Players%20Compete/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>n</code> 名运动员参与一场锦标赛，所有运动员站成一排，并根据 <strong>最开始的</strong> 站位从 <code>1</code> 到 <code>n</code> 编号（运动员 <code>1</code> 是这一排中的第一个运动员，运动员 <code>2</code> 是第二个运动员，依此类推）。</p>

<p>锦标赛由多个回合组成（从回合 <code>1</code> 开始）。每一回合中，这一排从前往后数的第 <code>i</code> 名运动员需要与从后往前数的第 <code>i</code> 名运动员比拼，获胜者将会进入下一回合。如果当前回合中运动员数目为奇数，那么中间那位运动员将轮空晋级下一回合。</p>

<ul>
	<li>例如，当前回合中，运动员 <code>1, 2, 4, 6, 7</code> 站成一排

    <ul>
    	<li>运动员 <code>1</code> 需要和运动员 <code>7</code> 比拼</li>
    	<li>运动员 <code>2</code> 需要和运动员 <code>6</code> 比拼</li>
    	<li>运动员 <code>4</code> 轮空晋级下一回合</li>
    </ul>
    </li>

</ul>

<p>每回合结束后，获胜者将会基于最开始分配给他们的原始顺序（升序）重新排成一排。</p>

<p>编号为 <code>firstPlayer</code> 和 <code>secondPlayer</code> 的运动员是本场锦标赛中的最佳运动员。在他们开始比拼之前，完全可以战胜任何其他运动员。而任意两个其他运动员进行比拼时，其中任意一个都有获胜的可能，因此你可以 <strong>裁定</strong> 谁是这一回合的获胜者。</p>

<p>给你三个整数 <code>n</code>、<code>firstPlayer</code> 和 <code>secondPlayer</code> 。返回一个由两个值组成的整数数组，分别表示两位最佳运动员在本场锦标赛中比拼的 <strong>最早</strong> 回合数和 <strong>最晚</strong> 回合数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 11, firstPlayer = 2, secondPlayer = 4
<strong>输出：</strong>[3,4]
<strong>解释：</strong>
一种能够产生最早回合数的情景是：
回合 1：1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
回合 2：2, 3, 4, 5, 6, 11
回合 3：2, 3, 4
一种能够产生最晚回合数的情景是：
回合 1：1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
回合 2：1, 2, 3, 4, 5, 6
回合 3：1, 2, 4
回合 4：2, 4
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 5, firstPlayer = 1, secondPlayer = 5
<strong>输出：</strong>[1,1]
<strong>解释：</strong>两名最佳运动员 1 和 5 将会在回合 1 进行比拼。
不存在使他们在其他回合进行比拼的可能。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 28</code></li>
	<li><code>1 &lt;= firstPlayer &lt; secondPlayer &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索 + 二进制枚举

我们定义一个函数 $\text{dfs}(l, r, n)$，表示在当前回合中，编号为 $l$ 和 $r$ 的运动员在 $n$ 名运动员中比拼的最早和最晚回合数。

函数 $\text{dfs}(l, r, n)$ 的执行逻辑如下：

1. 如果 $l + r = n - 1$，说明两名运动员在当前回合中比拼，返回 $[1, 1]$。
2. 如果 $f[l][r][n] \neq 0$，说明之前已经计算过这个状态，直接返回结果。
3. 初始化最早回合数为正无穷大，最晚回合数为负无穷大。
4. 计算当前回合中前半部分的运动员数目 $m = n / 2$。
5. 枚举前半部分的所有可能的胜者组合（使用二进制枚举），对于每一种组合：
    - 根据当前组合确定哪些运动员获胜。
    - 确定当前回合中编号为 $l$ 和 $r$ 的运动员在当前回合中的位置。
    - 统计当前回合中编号为 $l$ 和 $r$ 的运动员在剩余运动员中的位置，记为 $a$ 和 $b$，以及剩余运动员的总数 $c$。
    - 递归调用 $\text{dfs}(a, b, c)$，获取当前状态的最早和最晚回合数。
    - 更新最早回合数和最晚回合数。
6. 将计算结果存储在 $f[l][r][n]$ 中，并返回最早和最晚回合数。

答案为 $\text{dfs}(\text{firstPlayer} - 1, \text{secondPlayer} - 1, n)$。

<!-- tabs:start -->

#### Python3

```python
@cache
def dfs(l: int, r: int, n: int):
    if l + r == n - 1:
        return [1, 1]
    res = [inf, -inf]
    m = n >> 1
    for i in range(1 << m):
        win = [False] * n
        for j in range(m):
            if i >> j & 1:
                win[j] = True
            else:
                win[n - 1 - j] = True
        if n & 1:
            win[m] = True
        win[n - 1 - l] = win[n - 1 - r] = False
        win[l] = win[r] = True
        a = b = c = 0
        for j in range(n):
            if j == l:
                a = c
            if j == r:
                b = c
            if win[j]:
                c += 1
        x, y = dfs(a, b, c)
        res[0] = min(res[0], x + 1)
        res[1] = max(res[1], y + 1)
    return res


class Solution:
    def earliestAndLatest(
        self, n: int, firstPlayer: int, secondPlayer: int
    ) -> List[int]:
        return dfs(firstPlayer - 1, secondPlayer - 1, n)
```

#### Java

```java
class Solution {
    static int[][][] f = new int[30][30][31];

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        return dfs(firstPlayer - 1, secondPlayer - 1, n);
    }

    private int[] dfs(int l, int r, int n) {
        if (f[l][r][n] != 0) {
            return decode(f[l][r][n]);
        }
        if (l + r == n - 1) {
            f[l][r][n] = encode(1, 1);
            return new int[] {1, 1};
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int m = n >> 1;
        for (int i = 0; i < (1 << m); i++) {
            boolean[] win = new boolean[n];
            for (int j = 0; j < m; j++) {
                if (((i >> j) & 1) == 1) {
                    win[j] = true;
                } else {
                    win[n - 1 - j] = true;
                }
            }
            if ((n & 1) == 1) {
                win[m] = true;
            }
            win[n - 1 - l] = win[n - 1 - r] = false;
            win[l] = win[r] = true;
            int a = 0, b = 0, c = 0;
            for (int j = 0; j < n; j++) {
                if (j == l) {
                    a = c;
                }
                if (j == r) {
                    b = c;
                }
                if (win[j]) {
                    c++;
                }
            }
            int[] t = dfs(a, b, c);
            min = Math.min(min, t[0] + 1);
            max = Math.max(max, t[1] + 1);
        }
        f[l][r][n] = encode(min, max);
        return new int[] {min, max};
    }

    private int encode(int x, int y) {
        return (x << 8) | y;
    }

    private int[] decode(int val) {
        return new int[] {val >> 8, val & 255};
    }
}
```

#### C++

```cpp
int f[30][30][31];
class Solution {
public:
    vector<int> earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        return dfs(firstPlayer - 1, secondPlayer - 1, n);
    }

private:
    vector<int> dfs(int l, int r, int n) {
        if (f[l][r][n] != 0) {
            return decode(f[l][r][n]);
        }
        if (l + r == n - 1) {
            f[l][r][n] = encode(1, 1);
            return {1, 1};
        }

        int min = INT_MAX, max = INT_MIN;
        int m = n >> 1;

        for (int i = 0; i < (1 << m); i++) {
            vector<bool> win(n, false);
            for (int j = 0; j < m; j++) {
                if ((i >> j) & 1) {
                    win[j] = true;
                } else {
                    win[n - 1 - j] = true;
                }
            }
            if (n & 1) {
                win[m] = true;
            }

            win[n - 1 - l] = false;
            win[n - 1 - r] = false;
            win[l] = true;
            win[r] = true;

            int a = 0, b = 0, c = 0;
            for (int j = 0; j < n; j++) {
                if (j == l) a = c;
                if (j == r) b = c;
                if (win[j]) c++;
            }

            vector<int> t = dfs(a, b, c);
            min = std::min(min, t[0] + 1);
            max = std::max(max, t[1] + 1);
        }

        f[l][r][n] = encode(min, max);
        return {min, max};
    }

    int encode(int x, int y) {
        return (x << 8) | y;
    }

    vector<int> decode(int val) {
        return {val >> 8, val & 255};
    }
};
```

#### Go

```go
var f [30][30][31]int

func earliestAndLatest(n int, firstPlayer int, secondPlayer int) []int {
	return dfs(firstPlayer-1, secondPlayer-1, n)
}

func dfs(l, r, n int) []int {
	if f[l][r][n] != 0 {
		return decode(f[l][r][n])
	}
	if l+r == n-1 {
		f[l][r][n] = encode(1, 1)
		return []int{1, 1}
	}

	min, max := 1<<30, -1<<31
	m := n >> 1

	for i := 0; i < (1 << m); i++ {
		win := make([]bool, n)
		for j := 0; j < m; j++ {
			if (i>>j)&1 == 1 {
				win[j] = true
			} else {
				win[n-1-j] = true
			}
		}
		if n&1 == 1 {
			win[m] = true
		}
		win[n-1-l] = false
		win[n-1-r] = false
		win[l] = true
		win[r] = true

		a, b, c := 0, 0, 0
		for j := 0; j < n; j++ {
			if j == l {
				a = c
			}
			if j == r {
				b = c
			}
			if win[j] {
				c++
			}
		}

		t := dfs(a, b, c)
		if t[0]+1 < min {
			min = t[0] + 1
		}
		if t[1]+1 > max {
			max = t[1] + 1
		}
	}

	f[l][r][n] = encode(min, max)
	return []int{min, max}
}

func encode(x, y int) int {
	return (x << 8) | y
}

func decode(val int) []int {
	return []int{val >> 8, val & 255}
}
```

#### TypeScript

```ts
function earliestAndLatest(n: number, firstPlayer: number, secondPlayer: number): number[] {
    return dfs(firstPlayer - 1, secondPlayer - 1, n);
}

const f: number[][][] = Array.from({ length: 30 }, () =>
    Array.from({ length: 30 }, () => Array(31).fill(0)),
);

function dfs(l: number, r: number, n: number): number[] {
    if (f[l][r][n] !== 0) {
        return decode(f[l][r][n]);
    }
    if (l + r === n - 1) {
        f[l][r][n] = encode(1, 1);
        return [1, 1];
    }

    let min = Number.MAX_SAFE_INTEGER;
    let max = Number.MIN_SAFE_INTEGER;
    const m = n >> 1;

    for (let i = 0; i < 1 << m; i++) {
        const win: boolean[] = Array(n).fill(false);
        for (let j = 0; j < m; j++) {
            if ((i >> j) & 1) {
                win[j] = true;
            } else {
                win[n - 1 - j] = true;
            }
        }

        if (n & 1) {
            win[m] = true;
        }

        win[n - 1 - l] = false;
        win[n - 1 - r] = false;
        win[l] = true;
        win[r] = true;

        let a = 0,
            b = 0,
            c = 0;
        for (let j = 0; j < n; j++) {
            if (j === l) a = c;
            if (j === r) b = c;
            if (win[j]) c++;
        }

        const t = dfs(a, b, c);
        min = Math.min(min, t[0] + 1);
        max = Math.max(max, t[1] + 1);
    }

    f[l][r][n] = encode(min, max);
    return [min, max];
}

function encode(x: number, y: number): number {
    return (x << 8) | y;
}

function decode(val: number): number[] {
    return [val >> 8, val & 255];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
