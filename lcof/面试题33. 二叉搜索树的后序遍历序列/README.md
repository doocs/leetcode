---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [面试题 33. 二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

## 题目描述

<!-- description:start -->

<p>输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>。假设输入的数组的任意两个数字都互不相同。</p>

<p>&nbsp;</p>

<p>参考以下这颗二叉搜索树：</p>

<pre>     5
    / \
   2   6
  / \
 1   3</pre>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>[1,6,3,2,5]
<strong>输出: </strong>false</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入: </strong>[1,3,2,6,5]
<strong>输出: </strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>数组长度 &lt;= 1000</code></li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：显式栈

<!-- thinking:start -->

> **思考**
>
> 后序末元为根，其左段应小于根、右段应大于根。数组长度可以到 $1000$。递增序列里第一个不小于根的位置总在根的前一位，第一次调用就是 $\mathrm{dfs}(l, r-1)$，深度等于 $n$，会超出默认递归上限。
>
> 左右两段是否合法互不依赖调用顺序，只要两段都检查过即可。
>
> 因此把待查区间放进显式栈。弹出 $[l, r]$ 后找到第一个不小于根的位置 $i$，右段若出现小于根的值就失败，否则把右段 $[i, r-1]$ 和左段 $[l, i-1]$ 压栈。空区间直接跳过。

<!-- thinking:end -->

后序遍历的最后一个元素为根节点，根据二叉搜索树的性质，根节点左边的元素都小于根节点，根节点右边的元素都大于根节点。因此，我们找到第一个大于等于根节点的位置 $i$，那么 $[i, r)$ 中的元素都应该大于等于根节点，否则返回 `false`。

左右区间放进显式栈继续检查，而不是递归。初始区间是 $[0, n - 1]$。栈空时返回 `true`。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        stk = [(0, len(postorder) - 1)]
        while stk:
            l, r = stk.pop()
            if l >= r:
                continue
            v = postorder[r]
            i = l
            while i < r and postorder[i] < v:
                i += 1
            if any(x < v for x in postorder[i:r]):
                return False
            stk.append((i, r - 1))
            stk.append((l, i - 1))
        return True
```

#### Java

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        int n = postorder.length;
        int[] stk = new int[(n + 2) * 2];
        int top = 0;
        stk[top++] = 0;
        stk[top++] = n - 1;
        while (top > 0) {
            int r = stk[--top];
            int l = stk[--top];
            if (l >= r) {
                continue;
            }
            int v = postorder[r];
            int i = l;
            while (i < r && postorder[i] < v) {
                ++i;
            }
            for (int j = i; j < r; ++j) {
                if (postorder[j] < v) {
                    return false;
                }
            }
            stk[top++] = i;
            stk[top++] = r - 1;
            stk[top++] = l;
            stk[top++] = i - 1;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        int n = postorder.size();
        vector<pair<int, int>> stk{{0, n - 1}};
        while (!stk.empty()) {
            auto [l, r] = stk.back();
            stk.pop_back();
            if (l >= r) {
                continue;
            }
            int v = postorder[r];
            int i = l;
            while (i < r && postorder[i] < v) {
                ++i;
            }
            for (int j = i; j < r; ++j) {
                if (postorder[j] < v) {
                    return false;
                }
            }
            stk.emplace_back(i, r - 1);
            stk.emplace_back(l, i - 1);
        }
        return true;
    }
};
```

#### Go

```go
func verifyPostorder(postorder []int) bool {
	n := len(postorder)
	stk := [][2]int{{0, n - 1}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		l, r := cur[0], cur[1]
		if l >= r {
			continue
		}
		v := postorder[r]
		i := l
		for i < r && postorder[i] < v {
			i++
		}
		for j := i; j < r; j++ {
			if postorder[j] < v {
				return false
			}
		}
		stk = append(stk, [2]int{i, r - 1}, [2]int{l, i - 1})
	}
	return true
}
```

#### TypeScript

```ts
function verifyPostorder(postorder: number[]): boolean {
    const stk: number[][] = [[0, postorder.length - 1]];
    while (stk.length) {
        const [l, r] = stk.pop()!;
        if (l >= r) {
            continue;
        }
        const v = postorder[r];
        let i = l;
        while (i < r && postorder[i] < v) {
            ++i;
        }
        for (let j = i; j < r; ++j) {
            if (postorder[j] < v) {
                return false;
            }
        }
        stk.push([i, r - 1]);
        stk.push([l, i - 1]);
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn verify_postorder(postorder: Vec<i32>) -> bool {
        let n = postorder.len() as i32;
        let mut stk = vec![(0, n - 1)];
        while let Some((l, r)) = stk.pop() {
            if l >= r {
                continue;
            }
            let v = postorder[r as usize];
            let mut i = l;
            while i < r && postorder[i as usize] < v {
                i += 1;
            }
            for j in i..r {
                if postorder[j as usize] < v {
                    return false;
                }
            }
            stk.push((i, r - 1));
            stk.push((l, i - 1));
        }
        true
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} postorder
 * @return {boolean}
 */
var verifyPostorder = function (postorder) {
    const stk = [[0, postorder.length - 1]];
    while (stk.length) {
        const [l, r] = stk.pop();
        if (l >= r) {
            continue;
        }
        const v = postorder[r];
        let i = l;
        while (i < r && postorder[i] < v) {
            ++i;
        }
        for (let j = i; j < r; ++j) {
            if (postorder[j] < v) {
                return false;
            }
        }
        stk.push([i, r - 1]);
        stk.push([l, i - 1]);
    }
    return true;
};
```

#### C#

```cs
public class Solution {
    public bool VerifyPostorder(int[] postorder) {
        int n = postorder.Length;
        int[] stk = new int[(n + 2) * 2];
        int top = 0;
        stk[top++] = 0;
        stk[top++] = n - 1;
        while (top > 0) {
            int r = stk[--top];
            int l = stk[--top];
            if (l >= r) {
                continue;
            }
            int v = postorder[r];
            int i = l;
            while (i < r && postorder[i] < v) {
                ++i;
            }
            for (int j = i; j < r; ++j) {
                if (postorder[j] < v) {
                    return false;
                }
            }
            stk[top++] = i;
            stk[top++] = r - 1;
            stk[top++] = l;
            stk[top++] = i - 1;
        }
        return true;
    }
}
```

#### Swift

```swift
class Solution {
    func verifyPostorder(_ postorder: [Int]) -> Bool {
        var stk = [(0, postorder.count - 1)]
        while let (l, r) = stk.popLast() {
            if l >= r {
                continue
            }
            let v = postorder[r]
            var i = l
            while i < r && postorder[i] < v {
                i += 1
            }
            if i < r && postorder[i..<r].contains(where: { $0 < v }) {
                return false
            }
            stk.append((i, r - 1))
            stk.append((l, i - 1))
        }
        return true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：单调栈

<!-- thinking:start -->

> **思考**
>
> 方法一已经用显式栈分段，但每段仍要线性扫描，最坏是平方时间。从右往左看后序相当于“根、右、左”，值应先升后降。单调栈维护递减候选，弹出时更新父结点上界，若出现大于上界的值则非法。一次遍历即可。

<!-- thinking:end -->

后序遍历的顺序为“左、右、根”，如果我们从右往左遍历数组，那么顺序就变成“根、右、左”，根据二叉搜索树的性质，右子树所有节点值均大于根节点值。

因此，从右往左遍历数组，就是从根节点往右子树走，此时值逐渐变大，直到遇到一个递减的节点，此时的节点应该属于左子树节点。我们找到该节点的直接父节点，那么此后其它节点都应该小于该父节点，否则返回 `false`。然后继续遍历，直到遍历完整个数组。

此过程，我们借助栈来实现，具体步骤如下：

我们首先初始化一个无穷大的父节点值 $mx$，然后初始化一个空栈。

接下来，我们从右往左遍历数组，对于每个遍历到的元素 $x$：

- 如果 $x$ 大于 $mx$，说明当前节点不满足二叉搜索树的性质，返回 `false`。
- 否则，如果当前栈不为空，且栈顶元素大于 $x$，说明当前节点为左子树节点，我们循环将栈顶元素出栈并赋值给 $mx$，直到栈为空或者栈顶元素小于等于 $x$，然后将 $x$ 入栈。

遍历结束后，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        mx = inf
        stk = []
        for x in postorder[::-1]:
            if x > mx:
                return False
            while stk and stk[-1] > x:
                mx = stk.pop()
            stk.append(x)
        return True
```

#### Java

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        int mx = 1 << 30;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = postorder.length - 1; i >= 0; --i) {
            int x = postorder[i];
            if (x > mx) {
                return false;
            }
            while (!stk.isEmpty() && stk.peek() > x) {
                mx = stk.pop();
            }
            stk.push(x);
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        stack<int> stk;
        int mx = 1 << 30;
        reverse(postorder.begin(), postorder.end());
        for (int& x : postorder) {
            if (x > mx) {
                return false;
            }
            while (!stk.empty() && stk.top() > x) {
                mx = stk.top();
                stk.pop();
            }
            stk.push(x);
        }
        return true;
    }
};
```

#### Go

```go
func verifyPostorder(postorder []int) bool {
	mx := 1 << 30
	stk := []int{}
	for i := len(postorder) - 1; i >= 0; i-- {
		x := postorder[i]
		if x > mx {
			return false
		}
		for len(stk) > 0 && stk[len(stk)-1] > x {
			mx = stk[len(stk)-1]
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, x)
	}
	return true
}
```

#### TypeScript

```ts
function verifyPostorder(postorder: number[]): boolean {
    let mx = 1 << 30;
    const stk: number[] = [];
    for (let i = postorder.length - 1; i >= 0; --i) {
        const x = postorder[i];
        if (x > mx) {
            return false;
        }
        while (stk.length && stk[stk.length - 1] > x) {
            mx = stk.pop();
        }
        stk.push(x);
    }
    return true;
}
```

#### JavaScript

```js
/**
 * @param {number[]} postorder
 * @return {boolean}
 */
var verifyPostorder = function (postorder) {
    let mx = 1 << 30;
    const stk = [];
    for (let i = postorder.length - 1; i >= 0; --i) {
        const x = postorder[i];
        if (x > mx) {
            return false;
        }
        while (stk.length && stk[stk.length - 1] > x) {
            mx = stk.pop();
        }
        stk.push(x);
    }
    return true;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
