---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3827.Count%20Monobit%20Integers/README.md
---

<!-- problem:start -->

# [3827. 统计单比特整数](https://leetcode.cn/problems/count-monobit-integers)

[English Version](/solution/3800-3899/3827.Count%20Monobit%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>n</code>。</p>

<p>如果一个整数的二进制表示中所有位都相同，则称其为<strong>&nbsp;单比特数</strong>（<strong>Monobit</strong>）。</p>

<p>返回范围<code>[0, n]</code>（包括两端）内<strong>&nbsp;单比特数&nbsp;</strong>的个数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>范围<code>[0, 1]</code>内的整数对应的二进制表示为<code>"0"</code>和<code>"1"</code>。</li>
	<li>每个表示都由相同的位组成，因此答案是2。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>范围<code>[0, 4]</code>内的整数对应的二进制表示为<code>"0"</code>、<code>"1"</code>、<code>"10"</code>、<code>"11"</code>和<code>"100"</code>。</li>
	<li>只有<code>0</code>、<code>1</code>和<code>3</code>满足单比特条件。因此答案是3。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

根据题目描述，单比特整数，要么是 $0$，要么其二进制表示中所有位都是 $1$。

因此，我们首先将 $0$ 计入答案中，然后从 $1$ 开始，依次生成二进制表示中所有位都是 $1$ 的整数，直到该整数大于 $n$ 为止。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countMonobit(self, n: int) -> int:
        ans = x = 1
        i = 1
        while x <= n:
            ans += 1
            x += (1 << i)
            i += 1
        return ans
```

#### Java

```java
class Solution {
    public int countMonobit(int n) {
        int ans = 1;
        for (int i = 1, x = 1; x <= n; ++i) {
            ++ans;
            x += (1 << i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countMonobit(int n) {
        int ans = 1;
        for (int i = 1, x = 1; x <= n; ++i) {
            ++ans;
            x += (1 << i);
        }
        return ans;
    }
};
```

#### Go

```go
func countMonobit(n int) (ans int) {
	ans = 1
	for i, x := 1, 1; x <= n; i++ {
		ans++
		x += (1 << i)
	}
	return
}
```

#### TypeScript

```ts
function countMonobit(n: number): number {
    let ans = 1;
    for (let i = 1, x = 1; x <= n; ++i) {
        ++ans;
        x += 1 << i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
