---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3477.Fruits%20Into%20Baskets%20II/README.md
rating: 1295
source: 第 440 场周赛 Q1
tags:
    - 线段树
    - 数组
    - 二分查找
    - 模拟
---

<!-- problem:start -->

# [3477. 水果成篮 II](https://leetcode.cn/problems/fruits-into-baskets-ii)

[English Version](/solution/3400-3499/3477.Fruits%20Into%20Baskets%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度为 <code>n</code>&nbsp;的整数数组，<code>fruits</code> 和 <code>baskets</code>，其中 <code>fruits[i]</code> 表示第 <code>i</code>&nbsp;种水果的 <strong>数量</strong>，<code>baskets[j]</code> 表示第 <code>j</code>&nbsp;个篮子的 <strong>容量</strong>。</p>

<p>你需要对 <code>fruits</code> 数组从左到右按照以下规则放置水果：</p>

<ul>
	<li>每种水果必须放入第一个 <strong>容量大于等于</strong> 该水果数量的 <strong>最左侧可用篮子</strong> 中。</li>
	<li>每个篮子只能装 <b>一种</b> 水果。</li>
	<li>如果一种水果 <b>无法放入</b> 任何篮子，它将保持 <b>未放置</b>。</li>
</ul>

<p>返回所有可能分配完成后，剩余未放置的水果种类的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">fruits = [4,2,5], baskets = [3,5,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>fruits[0] = 4</code> 放入 <code>baskets[1] = 5</code>。</li>
	<li><code>fruits[1] = 2</code> 放入 <code>baskets[0] = 3</code>。</li>
	<li><code>fruits[2] = 5</code> 无法放入 <code>baskets[2] = 4</code>。</li>
</ul>

<p>由于有一种水果未放置，我们返回 1。</p>
</div>

<p><strong class="example">示例 2</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">fruits = [3,6,1], baskets = [6,4,7]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>fruits[0] = 3</code> 放入 <code>baskets[0] = 6</code>。</li>
	<li><code>fruits[1] = 6</code> 无法放入 <code>baskets[1] = 4</code>（容量不足），但可以放入下一个可用的篮子 <code>baskets[2] = 7</code>。</li>
	<li><code>fruits[2] = 1</code> 放入 <code>baskets[1] = 4</code>。</li>
</ul>

<p>由于所有水果都已成功放置，我们返回 0。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>n == fruits.length == baskets.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= fruits[i], baskets[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用一个长度为 $n$ 的布尔数组 $\textit{vis}$ 记录已经被使用的篮子，用一个答案变量 $\textit{ans}$ 记录所有未被放置的水果，初始时 $\textit{ans} = n$。

接下来，我们遍历每一种水果 $x$，对于当前水果，我们遍历所有的篮子，找出第一个未被使用，且容量大于等于 $x$ 的篮子 $i$。如果找到了，那么答案 $\textit{ans}$ 减 $1$。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{fruits}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numOfUnplacedFruits(self, fruits: List[int], baskets: List[int]) -> int:
        n = len(fruits)
        vis = [False] * n
        ans = n
        for x in fruits:
            for i, y in enumerate(baskets):
                if y >= x and not vis[i]:
                    vis[i] = True
                    ans -= 1
                    break
        return ans
```

#### Java

```java
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] vis = new boolean[n];
        int ans = n;
        for (int x : fruits) {
            for (int i = 0; i < n; ++i) {
                if (baskets[i] >= x && !vis[i]) {
                    vis[i] = true;
                    --ans;
                    break;
                }
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
    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        int n = fruits.size();
        vector<bool> vis(n);
        int ans = n;
        for (int x : fruits) {
            for (int i = 0; i < n; ++i) {
                if (baskets[i] >= x && !vis[i]) {
                    vis[i] = true;
                    --ans;
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numOfUnplacedFruits(fruits []int, baskets []int) int {
	n := len(fruits)
	ans := n
	vis := make([]bool, n)
	for _, x := range fruits {
		for i, y := range baskets {
			if y >= x && !vis[i] {
				vis[i] = true
				ans--
				break
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function numOfUnplacedFruits(fruits: number[], baskets: number[]): number {
    const n = fruits.length;
    const vis: boolean[] = Array(n).fill(false);
    let ans = n;
    for (const x of fruits) {
        for (let i = 0; i < n; ++i) {
            if (baskets[i] >= x && !vis[i]) {
                vis[i] = true;
                --ans;
                break;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
