---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3827.Count%20Monobit%20Integers/README_EN.md
---

<!-- problem:start -->

# [3827. Count Monobit Integers](https://leetcode.com/problems/count-monobit-integers)

[中文文档](/solution/3800-3899/3827.Count%20Monobit%20Integers/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>An integer is called <strong>Monobit</strong> if all bits in its binary representation are the same.</p>

<p>Return the count of <strong>Monobit</strong> integers in the range <code>[0, n]</code> (inclusive).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The integers in the range <code>[0, 1]</code> have binary representations <code>&quot;0&quot;</code> and <code>&quot;1&quot;</code>.</li>
	<li>Each representation consists of identical bits. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The integers in the range <code>[0, 4]</code> include binaries <code>&quot;0&quot;</code>, <code>&quot;1&quot;</code>, <code>&quot;10&quot;</code>, <code>&quot;11&quot;</code>, and <code>&quot;100&quot;</code>.</li>
	<li>Only 0, 1 and 3 satisfy the Monobit condition. Thus, the answer is 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
