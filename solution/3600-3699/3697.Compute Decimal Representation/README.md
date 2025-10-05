---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3697.Compute%20Decimal%20Representation/README.md
rating: 1250
source: 第 469 场周赛 Q1
---

<!-- problem:start -->

# [3697. 计算十进制表示](https://leetcode.cn/problems/compute-decimal-representation)

[English Version](/solution/3600-3699/3697.Compute%20Decimal%20Representation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<strong>正整数&nbsp;</strong><code>n</code>。</p>

<p>如果一个正整数可以表示为 1 到 9 的单个数字和 10 的非负整数次幂的乘积，则称这个整数是一个&nbsp;<strong>10 进制分量</strong>。例如，500、30 和 7 是&nbsp;<strong>10 进制分量&nbsp;</strong>，而 537、102 和 11 则不是。</p>

<p>请将&nbsp;<code>n</code>&nbsp;表示为若干&nbsp;<strong>仅由&nbsp;</strong>10 进制分量组成的和，且使用的 10 进制分量个数&nbsp;<strong>最少&nbsp;</strong>。</p>

<p>返回一个包含这些&nbsp;<strong>10 进制分量 </strong>的数组，并按分量大小&nbsp;<strong>降序&nbsp;</strong>排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 537</span></p>

<p><strong>输出：</strong><span class="example-io">[500,30,7]</span></p>

<p><strong>解释：</strong></p>

<p>我们可以将 537 表示为<code>500 + 30 + 7</code>。无法用少于 3 个 10 进制分量表示 537。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 102</span></p>

<p><strong>输出：</strong><span class="example-io">[100,2]</span></p>

<p><strong>解释：</strong></p>

<p>我们可以将 102 表示为<code>100 + 2</code>。102 不是一个 10 进制分量，因此需要 2 个 10 进制分量。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 6</span></p>

<p><strong>输出：</strong><span class="example-io">[6]</span></p>

<p><strong>解释：</strong></p>

<p>6 是一个 10 进制分量。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以不断地对 $n$ 进行取模和整除操作，取模得到的值乘以当前的位值 $p$ 就是一个 10 进制分量。如果取模的结果不为 $0$，我们就将这个分量加入答案中。然后我们将 $p$ 乘以 $10$，继续处理下一个位。

最后，我们将答案反转，使其按降序排列。

时间复杂度 $O(\log n)$，其中 $n$ 是输入的正整数。空间复杂度 $O(\log n)$，用于存储答案。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def decimalRepresentation(self, n: int) -> List[int]:
        ans = []
        p = 1
        while n:
            n, v = divmod(n, 10)
            if v:
                ans.append(p * v)
            p *= 10
        ans.reverse()
        return ans
```

#### Java

```java
class Solution {
    public int[] decimalRepresentation(int n) {
        List<Integer> parts = new ArrayList<>();
        int p = 1;
        while (n > 0) {
            int v = n % 10;
            n /= 10;
            if (v != 0) {
                parts.add(p * v);
            }
            p *= 10;
        }
        Collections.reverse(parts);
        int[] ans = new int[parts.size()];
        for (int i = 0; i < parts.size(); ++i) {
            ans[i] = parts.get(i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> decimalRepresentation(int n) {
        vector<int> ans;
        long long p = 1;
        while (n > 0) {
            int v = n % 10;
            n /= 10;
            if (v != 0) {
                ans.push_back(p * v);
            }
            p *= 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

#### Go

```go
func decimalRepresentation(n int) []int {
    ans := []int{}
    p := 1
    for n > 0 {
        v := n % 10
        n /= 10
        if v != 0 {
            ans = append(ans, p*v)
        }
        p *= 10
    }
    slices.Reverse(ans)
    return ans
}
```

#### TypeScript

```ts
function decimalRepresentation(n: number): number[] {
    const ans: number[] = [];
    let p: number = 1;
    while (n) {
        const v = n % 10;
        n = (n / 10) | 0;
        if (v) {
            ans.push(p * v);
        }
        p *= 10;
    }
    ans.reverse();
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
