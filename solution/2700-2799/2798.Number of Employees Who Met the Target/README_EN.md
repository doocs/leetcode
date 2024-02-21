# [2798. Number of Employees Who Met the Target](https://leetcode.com/problems/number-of-employees-who-met-the-target)

[中文文档](/solution/2700-2799/2798.Number%20of%20Employees%20Who%20Met%20the%20Target/README.md)

<!-- tags:Array -->

## Description

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

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def numberOfEmployeesWhoMetTarget(self, hours: List[int], target: int) -> int:
        return sum(x >= target for x in hours)
```

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

```cpp
class Solution {
public:
    int numberOfEmployeesWhoMetTarget(vector<int>& hours, int target) {
        int ans = 0;
        for (int x : hours) {
            ans += x >= target;
        }
        return ans;
    }
};
```

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

```ts
function numberOfEmployeesWhoMetTarget(hours: number[], target: number): number {
    let ans = 0;
    for (const x of hours) {
        if (x >= target) {
            ++ans;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn number_of_employees_who_met_target(hours: Vec<i32>, target: i32) -> i32 {
        let mut ans = 0;
        for &v in hours.iter() {
            if v >= target {
                ans += 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
