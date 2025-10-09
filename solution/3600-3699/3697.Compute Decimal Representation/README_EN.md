---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3697.Compute%20Decimal%20Representation/README_EN.md
rating: 1250
source: Weekly Contest 469 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [3697. Compute Decimal Representation](https://leetcode.com/problems/compute-decimal-representation)

[中文文档](/solution/3600-3699/3697.Compute%20Decimal%20Representation/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>positive</strong> integer <code>n</code>.</p>

<p>A positive integer is a <strong>base-10 component</strong> if it is the product of a single digit from 1 to 9 and a non-negative power of 10. For example, 500, 30, and 7 are <strong>base-10 components</strong>, while 537, 102, and 11 are not.</p>

<p>Express <code>n</code> as a sum of <strong>only</strong> base-10 components, using the <strong>fewest</strong> base-10 components possible.</p>

<p>Return an array containing these <strong>base-10 components</strong> in <strong>descending</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 537</span></p>

<p><strong>Output:</strong> <span class="example-io">[500,30,7]</span></p>

<p><strong>Explanation:</strong></p>

<p>We can express 537 as <code>500 + 30 + 7</code>. It is impossible to express 537 as a sum using fewer than 3 base-10 components.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 102</span></p>

<p><strong>Output:</strong> <span class="example-io">[100,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>We can express 102 as <code>100 + 2</code>. 102 is not a base-10 component, which means 2 base-10 components are needed.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">[6]</span></p>

<p><strong>Explanation:</strong></p>

<p>6 is a base-10 component.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can repeatedly perform modulo and division operations on $n$. Each modulo result multiplied by the current position value $p$ represents a decimal component. If the modulo result is not $0$, we add this component to our answer. Then we multiply $p$ by $10$ and continue processing the next position.

Finally, we reverse the answer to arrange it in descending order.

The time complexity is $O(\log n)$, where $n$ is the input positive integer. The space complexity is $O(\log n)$ for storing the answer.

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
