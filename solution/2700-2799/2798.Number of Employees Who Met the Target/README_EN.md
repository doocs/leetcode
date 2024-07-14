---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2798.Number%20of%20Employees%20Who%20Met%20the%20Target/README_EN.md
rating: 1142
source: Weekly Contest 356 Q1
tags:
    - Array
---

<!-- problem:start -->

# [2798. Number of Employees Who Met the Target](https://leetcode.com/problems/number-of-employees-who-met-the-target)

[中文文档](/solution/2700-2799/2798.Number%20of%20Employees%20Who%20Met%20the%20Target/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> employees in a company, numbered from <code>0</code> to <code>n - 1</code>. Each employee <code>i</code> has worked for <code>hours[i]</code> hours in the company.</p>

<p>The company requires each employee to work for <strong>at least</strong> <code>target</code> hours.</p>

<p>You are given a <strong>0-indexed</strong> array of non-negative integers <code>hours</code> of length <code>n</code> and a non-negative integer <code>target</code>.</p>

<p>Return <em>the integer denoting the number of employees who worked at least</em> <code>target</code> <em>hours</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> hours = [0,1,2,3,4], target = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The company wants each employee to work for at least 2 hours.
- Employee 0 worked for 0 hours and didn&#39;t meet the target.
- Employee 1 worked for 1 hours and didn&#39;t meet the target.
- Employee 2 worked for 2 hours and met the target.
- Employee 3 worked for 3 hours and met the target.
- Employee 4 worked for 4 hours and met the target.
There are 3 employees who met the target.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> hours = [5,1,4,2,2], target = 6
<strong>Output:</strong> 0
<strong>Explanation:</strong> The company wants each employee to work for at least 6 hours.
There are 0 employees who met the target.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == hours.length &lt;= 50</code></li>
	<li><code>0 &lt;=&nbsp;hours[i], target &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Iteration and Counting

We can iterate through the array $hours$. For each employee, if their working hours $x$ is greater than or equal to $target$, then we increment the counter $ans$ by one.

After the iteration, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $hours$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfEmployeesWhoMetTarget(self, hours: List[int], target: int) -> int:
        return sum(x >= target for x in hours)
```

#### Java

```java
class Solution {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int ans = 0;
        for (int x : hours) {
            if (x >= target) {
                ++ans;
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
    int numberOfEmployeesWhoMetTarget(vector<int>& hours, int target) {
        return count_if(hours.begin(), hours.end(), [target](int h) { return h >= target; });
    }
};
```

#### Go

```go
func numberOfEmployeesWhoMetTarget(hours []int, target int) (ans int) {
	for _, x := range hours {
		if x >= target {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfEmployeesWhoMetTarget(hours: number[], target: number): number {
    return hours.filter(x => x >= target).length;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_employees_who_met_target(hours: Vec<i32>, target: i32) -> i32 {
        hours.iter().filter(|&x| *x >= target).count() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
