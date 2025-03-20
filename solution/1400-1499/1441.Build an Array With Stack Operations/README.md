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

<p>给你一个数组 <code>target</code> 和一个整数 <code>n</code>。每次迭代，需要从&nbsp; <code>list = { 1 , 2 , 3 ..., n }</code> 中依次读取一个数字。</p>

<p>请使用下述操作来构建目标数组 <code>target</code> ：</p>

<ul>
	<li><code>"Push"</code>：从 <code>list</code> 中读取一个新元素， 并将其推入数组中。</li>
	<li><code>"Pop"</code>：删除数组中的最后一个元素。</li>
	<li>如果目标数组构建完成，就停止读取更多元素。</li>
</ul>

<p>题目数据保证目标数组严格递增，并且只包含 <code>1</code> 到 <code>n</code> 之间的数字。</p>

<p>请返回构建目标数组所用的操作序列。如果存在多个可行方案，返回任一即可。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = [1,3], n = 3
<strong>输出：</strong>["Push","Push","Pop","Push"]
<strong>解释： 
</strong>读取 1 并自动推入数组 -&gt; [1]
读取 2 并自动推入数组，然后删除它 -&gt; [1]
读取 3 并自动推入数组 -&gt; [1,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = [1,2,3], n = 3
<strong>输出：</strong>["Push","Push","Push"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = [1,2], n = 4
<strong>输出：</strong>["Push","Push"]
<strong>解释：</strong>只需要读取前 2 个数字就可以停止。
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

-   如果 $\textit{cur} < x$，我们将 $\textit{Push}$ 和 $\textit{Pop}$ 依次加入答案，直到 $\textit{cur} = x$；
-   然后我们将 $\textit{Push}$ 加入答案，表示读取数字 $x$；
-   接着，我们将 $\textit{cur}$ 加一，继续处理下一个数字。

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
