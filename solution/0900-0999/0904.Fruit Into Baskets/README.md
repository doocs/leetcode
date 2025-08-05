---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0904.Fruit%20Into%20Baskets/README.md
tags:
    - 数组
    - 哈希表
    - 滑动窗口
---

<!-- problem:start -->

# [904. 水果成篮](https://leetcode.cn/problems/fruit-into-baskets)

[English Version](/solution/0900-0999/0904.Fruit%20Into%20Baskets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 <code>fruits</code> 表示，其中 <code>fruits[i]</code> 是第 <code>i</code> 棵树上的水果 <strong>种类</strong> 。</p>

<p>你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：</p>

<ul>
	<li>你只有 <strong>两个</strong> 篮子，并且每个篮子只能装 <strong>单一类型</strong> 的水果。每个篮子能够装的水果总量没有限制。</li>
	<li>你可以选择任意一棵树开始采摘，你必须从 <strong>每棵</strong> 树（包括开始采摘的树）上 <strong>恰好摘一个水果</strong> 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。</li>
	<li>一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。</li>
</ul>

<p>给你一个整数数组 <code>fruits</code> ，返回你可以收集的水果的 <strong>最大</strong> 数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>fruits = [<em><strong>1,2,1</strong></em>]
<strong>输出：</strong>3
<strong>解释：</strong>可以采摘全部 3 棵树。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>fruits = [0,<em><strong>1,2,2</strong></em>]
<strong>输出：</strong>3
<strong>解释：</strong>可以采摘 [1,2,2] 这三棵树。
如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>fruits = [1,<em><strong>2,3,2,2</strong></em>]
<strong>输出：</strong>4
<strong>解释：</strong>可以采摘 [2,3,2,2] 这四棵树。
如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>fruits = [3,3,3,<em><strong>1,2,1,1,2</strong></em>,3,3,4]
<strong>输出：</strong>5
<strong>解释：</strong>可以采摘 [1,2,1,1,2] 这五棵树。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= fruits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= fruits[i] &lt; fruits.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 滑动窗口

我们用哈希表 $\textit{cnt}$ 维护当前窗口内的水果种类以及对应的数量，用双指针 $j$ 和 $i$ 维护窗口的左右边界。

遍历数组 $\textit{fruits}$，将当前水果 $x$ 加入窗口，即 $\textit{cnt}[x]++$，然后判断当前窗口内的水果种类是否超过了 $2$ 种，如果超过了 $2$ 种，就需要将窗口的左边界 $j$ 右移，直到窗口内的水果种类不超过 $2$ 种为止。然后更新答案，即 $\textit{ans} = \max(\textit{ans}, i - j + 1)$。

遍历结束后，即可得到最终的答案。

```
1 2 3 2 2 1 4
^   ^
j   i


1 2 3 2 2 1 4
  ^ ^
  j i


1 2 3 2 2 1 4
  ^     ^
  j     i
```

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{fruits}$ 的长度。空间复杂度 $O(1)$，因为哈希表 $\textit{cnt}$ 中的键值对数量最多为 $2$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        cnt = Counter()
        ans = j = 0
        for i, x in enumerate(fruits):
            cnt[x] += 1
            while len(cnt) > 2:
                y = fruits[j]
                cnt[y] -= 1
                if cnt[y] == 0:
                    cnt.pop(y)
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

#### Java

```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.length; ++i) {
            int x = fruits[i];
            cnt.merge(x, 1, Integer::sum);
            while (cnt.size() > 2) {
                int y = fruits[j++];
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.size(); ++i) {
            int x = fruits[i];
            ++cnt[x];
            while (cnt.size() > 2) {
                int y = fruits[j++];
                if (--cnt[y] == 0) {
                    cnt.erase(y);
                }
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func totalFruit(fruits []int) int {
	cnt := map[int]int{}
	ans, j := 0, 0
	for i, x := range fruits {
		cnt[x]++
		for ; len(cnt) > 2; j++ {
			y := fruits[j]
			cnt[y]--
			if cnt[y] == 0 {
				delete(cnt, y)
			}
		}
		ans = max(ans, i-j+1)
	}
	return ans
}
```

#### TypeScript

```ts
function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let i = 0, j = 0; i < n; ++i) {
        cnt.set(fruits[i], (cnt.get(fruits[i]) || 0) + 1);
        for (; cnt.size > 2; ++j) {
            cnt.set(fruits[j], cnt.get(fruits[j])! - 1);
            if (!cnt.get(fruits[j])) {
                cnt.delete(fruits[j]);
            }
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        let mut ans = 0;
        let mut j = 0;

        for (i, &x) in fruits.iter().enumerate() {
            *cnt.entry(x).or_insert(0) += 1;

            while cnt.len() > 2 {
                let y = fruits[j];
                j += 1;
                *cnt.get_mut(&y).unwrap() -= 1;
                if cnt[&y] == 0 {
                    cnt.remove(&y);
                }
            }

            ans = ans.max(i - j + 1);
        }

        ans as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int TotalFruit(int[] fruits) {
        var cnt = new Dictionary<int, int>();
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.Length; ++i) {
            int x = fruits[i];
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }
            while (cnt.Count > 2) {
                int y = fruits[j++];
                if (cnt.ContainsKey(y)) {
                    cnt[y]--;
                    if (cnt[y] == 0) {
                        cnt.Remove(y);
                    }
                }
            }
            ans = Math.Max(ans, i - j + 1);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：单调变长滑动窗口

在方法一中，我们发现，窗口大小会时而变大，时而变小，这就需要我们每一次更新答案。

但本题实际上求的是水果的最大数目，也就是“最大”的窗口，我们没有必要缩小窗口，只需要让窗口单调增大。于是代码就少了每次更新答案的操作，只需要在遍历结束后将此时的窗口大小作为答案返回即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{fruits}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        cnt = Counter()
        j = 0
        for x in fruits:
            cnt[x] += 1
            if len(cnt) > 2:
                y = fruits[j]
                cnt[y] -= 1
                if cnt[y] == 0:
                    cnt.pop(y)
                j += 1
        return len(fruits) - j
```

#### Java

```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int j = 0, n = fruits.length;
        for (int x : fruits) {
            cnt.merge(x, 1, Integer::sum);
            if (cnt.size() > 2) {
                int y = fruits[j++];
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            }
        }
        return n - j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        unordered_map<int, int> cnt;
        int j = 0, n = fruits.size();
        for (int& x : fruits) {
            ++cnt[x];
            if (cnt.size() > 2) {
                int y = fruits[j++];
                if (--cnt[y] == 0) {
                    cnt.erase(y);
                }
            }
        }
        return n - j;
    }
};
```

#### Go

```go
func totalFruit(fruits []int) int {
	cnt := map[int]int{}
	j := 0
	for _, x := range fruits {
		cnt[x]++
		if len(cnt) > 2 {
			y := fruits[j]
			cnt[y]--
			if cnt[y] == 0 {
				delete(cnt, y)
			}
			j++
		}
	}
	return len(fruits) - j
}
```

#### TypeScript

```ts
function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const cnt: Map<number, number> = new Map();
    let j = 0;
    for (const x of fruits) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
        if (cnt.size > 2) {
            cnt.set(fruits[j], cnt.get(fruits[j])! - 1);
            if (!cnt.get(fruits[j])) {
                cnt.delete(fruits[j]);
            }
            ++j;
        }
    }
    return n - j;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        let mut j = 0;
        let n = fruits.len();

        for &x in &fruits {
            *cnt.entry(x).or_insert(0) += 1;
            if cnt.len() > 2 {
                let y = fruits[j];
                j += 1;
                *cnt.get_mut(&y).unwrap() -= 1;
                if cnt[&y] == 0 {
                    cnt.remove(&y);
                }
            }
        }

        (n - j) as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int TotalFruit(int[] fruits) {
        var cnt = new Dictionary<int, int>();
        int j = 0, n = fruits.Length;
        foreach (int x in fruits) {
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }

            if (cnt.Count > 2) {
                int y = fruits[j++];
                if (cnt.ContainsKey(y)) {
                    cnt[y]--;
                    if (cnt[y] == 0) {
                        cnt.Remove(y);
                    }
                }
            }
        }
        return n - j;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
