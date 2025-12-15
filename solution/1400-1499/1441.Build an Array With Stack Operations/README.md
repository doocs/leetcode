---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README.md
rating: 1180
source: 第 188 场周赛 Q1
tags:
    - 栈
    - 数组
    - 模拟
---

<!-- problem:start -->

# [1441. 用栈操作构建数组](https://leetcode.cn/problems/build-an-array-with-stack-operations)

[English Version](/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>target</code> 和一个整数 <code>n</code>。</p>

<p>给你一个空栈和两种操作：</p>

<ul>
	<li><code>"Push"</code>：将一个整数加到栈顶。</li>
	<li><code>"Pop"</code>：从栈顶删除一个整数。</li>
</ul>

<p>同时给定一个范围 <code>[1, n]</code> 中的整数流。</p>

<p>使用两个栈操作使栈中的数字（从底部到顶部）等于 <code>target</code>。你应该遵循以下规则：</p>

<ul>
	<li>如果整数流不为空，从流中选取下一个整数并将其推送到栈顶。</li>
	<li>如果栈不为空，弹出栈顶的整数。</li>
	<li>如果，在任何时刻，栈中的元素（从底部到顶部）等于 <code>target</code>，则不要从流中读取新的整数，也不要对栈进行更多操作。</li>
</ul>

<p>请返回遵循上述规则构建&nbsp;<code>target</code> 所用的操作序列。如果存在多个合法答案，返回 <strong>任一</strong> 即可。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = [1,3], n = 3
<strong>输出：</strong>["Push","Push","Pop","Push"]
<strong>解释：</strong>一开始栈为空。最后一个元素是栈顶。<strong>
</strong>从流中读取 1 并推入数组。s = [1]。
从流中读取 2 并推入数组。s = [1,2]。
从栈顶删除整数。s = [1]。
从流中读取 3 并推入数组。s = [1,3]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = [1,2,3], n = 3
<strong>输出：</strong>["Push","Push","Push"]
<strong>解释：</strong>一开始栈为空。最后一个元素是栈顶。
从流中读取 1 并推入数组。s = [1]。
从流中读取 2 并推入数组。s = [1,2]。
从流中读取 3 并推入数组。s = [1,2,3]。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = [1,2], n = 4
<strong>输出：</strong>["Push","Push"]
<strong>解释：</strong>一开始栈为空。最后一个元素是栈顶。
从流中读取 1 并推入数组。s = [1]。
从流中读取 2 并推入数组。s = [1,2]。
由于栈（从底部到顶部）等于 target，我们停止栈操作。
从流中读取整数 3 的答案不被接受。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= target[i] &lt;= n</code></li>
	<li><code>target</code> 严格递增</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们定义一个变量 $\textit{cur}$ 表示当前待读取的数字，初始时 $\textit{cur} = 1$，用一个数组 $\textit{ans}$ 存储答案。

接下来，我们遍历数组 $\textit{target}$ 中的每个数字 $x$：

- 如果 $\textit{cur} < x$，我们将 $\textit{Push}$ 和 $\textit{Pop}$ 依次加入答案，直到 $\textit{cur} = x$；
- 然后我们将 $\textit{Push}$ 加入答案，表示读取数字 $x$；
- 接着，我们将 $\textit{cur}$ 加一，继续处理下一个数字。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{target}$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def buildArray(self, target: List[int], n: int) -> List[str]:
        ans = []
        cur = 1
        for x in target:
            while cur < x:
                ans.extend(["Push", "Pop"])
                cur += 1
            ans.append("Push")
            cur += 1
        return ans
```

#### Java

```java
class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int cur = 1;
        for (int x : target) {
            while (cur < x) {
                ans.addAll(List.of("Push", "Pop"));
                ++cur;
            }
            ans.add("Push");
            ++cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> buildArray(vector<int>& target, int n) {
        vector<string> ans;
        int cur = 1;
        for (int x : target) {
            while (cur < x) {
                ans.push_back("Push");
                ans.push_back("Pop");
                ++cur;
            }
            ans.push_back("Push");
            ++cur;
        }
        return ans;
    }
};
```

#### Go

```go
func buildArray(target []int, n int) (ans []string) {
	cur := 1
	for _, x := range target {
		for ; cur < x; cur++ {
			ans = append(ans, "Push", "Pop")
		}
		ans = append(ans, "Push")
		cur++
	}
	return
}
```

#### TypeScript

```ts
function buildArray(target: number[], n: number): string[] {
    const ans: string[] = [];
    let cur: number = 1;
    for (const x of target) {
        for (; cur < x; ++cur) {
            ans.push('Push', 'Pop');
        }
        ans.push('Push');
        ++cur;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn build_array(target: Vec<i32>, n: i32) -> Vec<String> {
        let mut ans = Vec::new();
        let mut cur = 1;
        for &x in &target {
            while cur < x {
                ans.push("Push".to_string());
                ans.push("Pop".to_string());
                cur += 1;
            }
            ans.push("Push".to_string());
            cur += 1;
        }
        ans
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
char** buildArray(int* target, int targetSize, int n, int* returnSize) {
    char** ans = (char**) malloc(sizeof(char*) * (2 * n));
    *returnSize = 0;
    int cur = 1;
    for (int i = 0; i < targetSize; i++) {
        while (cur < target[i]) {
            ans[(*returnSize)++] = "Push";
            ans[(*returnSize)++] = "Pop";
            cur++;
        }
        ans[(*returnSize)++] = "Push";
        cur++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
