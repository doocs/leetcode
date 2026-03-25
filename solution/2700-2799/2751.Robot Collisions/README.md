---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2751.Robot%20Collisions/README.md
rating: 2091
source: 第 351 场周赛 Q4
tags:
    - 栈
    - 数组
    - 排序
    - 模拟
---

<!-- problem:start -->

# [2751. 机器人碰撞](https://leetcode.cn/problems/robot-collisions)

[English Version](/solution/2700-2799/2751.Robot%20Collisions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现有 <code>n</code> 个机器人，编号从 <strong>1</strong> 开始，每个机器人包含在路线上的位置、健康度和移动方向。</p>

<p>给你下标从 <strong>0</strong> 开始的两个整数数组 <code>positions</code>、<code>healths</code> 和一个字符串 <code>directions</code>（<code>directions[i]</code> 为 <strong>'L'</strong> 表示 <strong>向左</strong> 或 <strong>'R'</strong> 表示 <strong>向右</strong>）。 <code>positions</code> 中的所有整数 <strong>互不相同</strong> 。</p>

<p>所有机器人以 <strong>相同速度</strong> <strong>同时</strong> 沿给定方向在路线上移动。如果两个机器人移动到相同位置，则会发生 <strong>碰撞</strong> 。</p>

<p>如果两个机器人发生碰撞，则将 <strong>健康度较低</strong> 的机器人从路线中 <strong>移除</strong> ，并且另一个机器人的健康度 <strong>减少 1</strong> 。幸存下来的机器人将会继续沿着与之前 <strong>相同</strong> 的方向前进。如果两个机器人的健康度相同，则将二者都从路线中移除。</p>

<p>请你确定全部碰撞后幸存下的所有机器人的 <strong>健康度</strong> ，并按照原来机器人编号的顺序排列。即机器人 1 （如果幸存）的最终健康度，机器人 2 （如果幸存）的最终健康度等。 如果不存在幸存的机器人，则返回空数组。</p>

<p>在不再发生任何碰撞后，请你以数组形式，返回所有剩余机器人的健康度（按机器人输入中的编号顺序）。</p>

<p><strong>注意：</strong>位置&nbsp; <code>positions</code> 可能是乱序的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img height="169" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516011718-12.png" width="808" /></p>

<pre>
<strong>输入：</strong>positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
<strong>输出：</strong>[2,17,9,15,10]
<strong>解释：</strong>在本例中不存在碰撞，因为所有机器人向同一方向移动。所以，从第一个机器人开始依序返回健康度，[2, 17, 9, 15, 10] 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img height="176" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516004433-7.png" width="717" /></p>

<pre>
<strong>输入：</strong>positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
<strong>输出：</strong>[14]
<strong>解释：</strong>本例中发生 2 次碰撞。首先，机器人 1 和机器人 2 将会碰撞，因为二者健康度相同，二者都将被从路线中移除。接下来，机器人 3 和机器人 4 将会发生碰撞，由于机器人 4 的健康度更小，则它会被移除，而机器人 3 的健康度变为 15 - 1 = 14 。仅剩机器人 3 ，所以返回 [14] 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img height="172" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516005114-9.png" width="732" /></p>

<pre>
<strong>输入：</strong>positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
<strong>输出：</strong>[]
<strong>解释：</strong>机器人 1 和机器人 2 将会碰撞，因为二者健康度相同，二者都将被从路线中移除。机器人 3 和机器人 4 将会碰撞，因为二者健康度相同，二者都将被从路线中移除。所以返回空数组 [] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= positions.length == healths.length == directions.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= positions[i], healths[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>directions[i] == 'L'</code> 或 <code>directions[i] == 'R'</code></li>
	<li><code>positions</code> 中的所有值互不相同</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈模拟

我们首先将机器人按照位置从小到大排序，用一个数组 $\textit{idx}$ 存储排序后的机器人编号。然后我们使用一个栈来模拟碰撞过程：

1. 从左到右遍历 $\textit{idx}$ 中的机器人编号 $i$，如果 $directions[i]$ 是向右移动，则将 $i$ 入栈。
2. 如果 $directions[i]$ 是向左移动，则与栈顶的向右移动的机器人发生碰撞，直到栈为空或当前机器人被移除。
    - 如果栈顶机器人健康度大于当前机器人，则当前机器人被移除，栈顶机器人健康度减 1。
    - 如果栈顶机器人健康度小于当前机器人，则栈顶机器人被移除，当前机器人健康度减 1，并继续与新的栈顶机器人发生碰撞。
    - 如果两者健康度相同，则两者都被移除。

最后我们返回所有健康度大于 0 的机器人健康度。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是机器人的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def survivedRobotsHealths(self, positions, healths, directions):
        idx = sorted(range(len(positions)), key=lambda i: positions[i])
        stk = []

        for i in idx:
            if directions[i] == "R":
                stk.append(i)
                continue

            while stk and healths[i]:
                j = stk[-1]
                if healths[j] > healths[i]:
                    healths[j] -= 1
                    healths[i] = 0
                elif healths[j] < healths[i]:
                    healths[i] -= 1
                    healths[j] = 0
                    stk.pop()
                else:
                    healths[i] = healths[j] = 0
                    stk.pop()
                    break

        return [h for h in healths if h > 0]
```

#### Java

```java
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (a, b) -> positions[a] - positions[b]);

        Deque<Integer> stk = new ArrayDeque<>();

        for (int i : idx) {
            if (directions.charAt(i) == 'R') {
                stk.push(i);
                continue;
            }

            while (!stk.isEmpty() && healths[i] > 0) {
                int j = stk.peek();

                if (healths[j] > healths[i]) {
                    healths[j]--;
                    healths[i] = 0;
                } else if (healths[j] < healths[i]) {
                    healths[i]--;
                    healths[j] = 0;
                    stk.pop();
                } else {
                    healths[i] = healths[j] = 0;
                    stk.pop();
                    break;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                ans.add(h);
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
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string directions) {
        int n = positions.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);

        sort(idx.begin(), idx.end(), [&](int a, int b) {
            return positions[a] < positions[b];
        });

        vector<int> stk;

        for (int i : idx) {
            if (directions[i] == 'R') {
                stk.push_back(i);
                continue;
            }

            while (!stk.empty() && healths[i] > 0) {
                int j = stk.back();

                if (healths[j] > healths[i]) {
                    healths[j]--;
                    healths[i] = 0;
                } else if (healths[j] < healths[i]) {
                    healths[i]--;
                    healths[j] = 0;
                    stk.pop_back();
                } else {
                    healths[i] = healths[j] = 0;
                    stk.pop_back();
                    break;
                }
            }
        }

        vector<int> ans;
        for (int h : healths) {
            if (h > 0) {
                ans.push_back(h);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func survivedRobotsHealths(positions []int, healths []int, directions string) []int {
	n := len(positions)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}

	sort.Slice(idx, func(i, j int) bool {
		return positions[idx[i]] < positions[idx[j]]
	})

	stk := []int{}

	for _, i := range idx {
		if directions[i] == 'R' {
			stk = append(stk, i)
			continue
		}

		for len(stk) > 0 && healths[i] > 0 {
			j := stk[len(stk)-1]

			if healths[j] > healths[i] {
				healths[j]--
				healths[i] = 0
			} else if healths[j] < healths[i] {
				healths[i]--
				healths[j] = 0
				stk = stk[:len(stk)-1]
			} else {
				healths[i], healths[j] = 0, 0
				stk = stk[:len(stk)-1]
				break
			}
		}
	}

	ans := []int{}
	for _, h := range healths {
		if h > 0 {
			ans = append(ans, h)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function survivedRobotsHealths(
    positions: number[],
    healths: number[],
    directions: string,
): number[] {
    const n = positions.length;
    const idx = Array.from({ length: n }, (_, i) => i).sort((a, b) => positions[a] - positions[b]);

    const stk: number[] = [];

    for (const i of idx) {
        if (directions[i] === 'R') {
            stk.push(i);
            continue;
        }

        while (stk.length && healths[i] > 0) {
            const j = stk[stk.length - 1];

            if (healths[j] > healths[i]) {
                healths[j]--;
                healths[i] = 0;
            } else if (healths[j] < healths[i]) {
                healths[i]--;
                healths[j] = 0;
                stk.pop();
            } else {
                healths[i] = healths[j] = 0;
                stk.pop();
                break;
            }
        }
    }

    return healths.filter(h => h > 0);
}
```

#### JavaScript

```js
/**
 * @param {number[]} positions
 * @param {number[]} healths
 * @param {string} directions
 * @return {number[]}
 */
var survivedRobotsHealths = function (positions, healths, directions) {
    const n = positions.length;
    const idx = Array.from({ length: n }, (_, i) => i).sort((a, b) => positions[a] - positions[b]);

    const stk = [];

    for (const i of idx) {
        if (directions[i] === 'R') {
            stk.push(i);
            continue;
        }

        while (stk.length && healths[i] > 0) {
            const j = stk[stk.length - 1];

            if (healths[j] > healths[i]) {
                healths[j]--;
                healths[i] = 0;
            } else if (healths[j] < healths[i]) {
                healths[i]--;
                healths[j] = 0;
                stk.pop();
            } else {
                healths[i] = healths[j] = 0;
                stk.pop();
                break;
            }
        }
    }

    return healths.filter(h => h > 0);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
