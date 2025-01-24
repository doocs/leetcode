---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2178.Maximum%20Split%20of%20Positive%20Even%20Integers/README_EN.md
rating: 1538
source: Biweekly Contest 72 Q3
tags:
    - Greedy
    - Math
    - Backtracking
---

<!-- problem:start -->

# [2178. Maximum Split of Positive Even Integers](https://leetcode.com/problems/maximum-split-of-positive-even-integers)

[中文文档](/solution/2100-2199/2178.Maximum%20Split%20of%20Positive%20Even%20Integers/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>finalSum</code>. Split it into a sum of a <strong>maximum</strong> number of <strong>unique</strong> positive even integers.</p>

<ul>
	<li>For example, given <code>finalSum = 12</code>, the following splits are <strong>valid</strong> (unique positive even integers summing up to <code>finalSum</code>): <code>(12)</code>, <code>(2 + 10)</code>, <code>(2 + 4 + 6)</code>, and <code>(4 + 8)</code>. Among them, <code>(2 + 4 + 6)</code> contains the maximum number of integers. Note that <code>finalSum</code> cannot be split into <code>(2 + 2 + 4 + 4)</code> as all the numbers should be unique.</li>
</ul>

<p>Return <em>a list of integers that represent a valid split containing a <strong>maximum</strong> number of integers</em>. If no valid split exists for <code>finalSum</code>, return <em>an <strong>empty</strong> list</em>. You may return the integers in <strong>any</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> finalSum = 12
<strong>Output:</strong> [2,4,6]
<strong>Explanation:</strong> The following are valid splits: <code>(12)</code>, <code>(2 + 10)</code>, <code>(2 + 4 + 6)</code>, and <code>(4 + 8)</code>.
(2 + 4 + 6) has the maximum number of integers, which is 3. Thus, we return [2,4,6].
Note that [2,6,4], [6,2,4], etc. are also accepted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> finalSum = 7
<strong>Output:</strong> []
<strong>Explanation:</strong> There are no valid splits for the given finalSum.
Thus, we return an empty array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> finalSum = 28
<strong>Output:</strong> [6,8,2,12]
<strong>Explanation:</strong> The following are valid splits: <code>(2 + 26)</code>, <code>(6 + 8 + 2 + 12)</code>, and <code>(4 + 24)</code>. 
<code>(6 + 8 + 2 + 12)</code> has the maximum number of integers, which is 4. Thus, we return [6,8,2,12].
Note that [10,2,4,12], [6,2,4,16], etc. are also accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= finalSum &lt;= 10<sup>10</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

If $\textit{finalSum}$ is odd, it cannot be split into the sum of several distinct positive even integers, so we directly return an empty array.

Otherwise, we can greedily split $\textit{finalSum}$ in the order of $2, 4, 6, \cdots$, until $\textit{finalSum}$ can no longer be split into a different positive even integer. At this point, we add the remaining $\textit{finalSum}$ to the last positive even integer.

The time complexity is $O(\sqrt{\textit{finalSum}})$, and ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumEvenSplit(self, finalSum: int) -> List[int]:
        if finalSum & 1:
            return []
        ans = []
        i = 2
        while i <= finalSum:
            finalSum -= i
            ans.append(i)
            i += 2
        ans[-1] += finalSum
        return ans
```

#### Java

```java
class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if (finalSum % 2 == 1) {
            return ans;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            ans.add(i);
            finalSum -= i;
        }
        ans.add(ans.remove(ans.size() - 1) + finalSum);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> maximumEvenSplit(long long finalSum) {
        vector<long long> ans;
        if (finalSum % 2) {
            return ans;
        }
        for (long long i = 2; i <= finalSum; i += 2) {
            ans.push_back(i);
            finalSum -= i;
        }
        ans.back() += finalSum;
        return ans;
    }
};
```

#### Go

```go
func maximumEvenSplit(finalSum int64) (ans []int64) {
	if finalSum%2 == 1 {
		return
	}
	for i := int64(2); i <= finalSum; i += 2 {
		ans = append(ans, i)
		finalSum -= i
	}
	ans[len(ans)-1] += finalSum
	return
}
```

#### TypeScript

```ts
function maximumEvenSplit(finalSum: number): number[] {
    const ans: number[] = [];
    if (finalSum % 2 === 1) {
        return ans;
    }
    for (let i = 2; i <= finalSum; i += 2) {
        ans.push(i);
        finalSum -= i;
    }
    ans[ans.length - 1] += finalSum;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_even_split(mut final_sum: i64) -> Vec<i64> {
        let mut ans = Vec::new();
        if final_sum % 2 != 0 {
            return ans;
        }
        let mut i = 2;
        while i <= final_sum {
            ans.push(i);
            final_sum -= i;
            i += 2;
        }
        if let Some(last) = ans.last_mut() {
            *last += final_sum;
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public IList<long> MaximumEvenSplit(long finalSum) {
        IList<long> ans = new List<long>();
        if (finalSum % 2 == 1) {
            return ans;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            ans.Add(i);
            finalSum -= i;
        }
        ans[ans.Count - 1] += finalSum;
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
