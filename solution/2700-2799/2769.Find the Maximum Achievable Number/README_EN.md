---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2769.Find%20the%20Maximum%20Achievable%20Number/README_EN.md
rating: 1191
source: Weekly Contest 353 Q1
tags:
    - Math
---

<!-- problem:start -->

# [2769. Find the Maximum Achievable Number](https://leetcode.com/problems/find-the-maximum-achievable-number)

[中文文档](/solution/2700-2799/2769.Find%20the%20Maximum%20Achievable%20Number/README.md)

## Description

<p>You are given two integers, <code>num</code> and <code>t</code>.</p>

<p>An integer <code>x</code> is called <b>achievable</b> if it can become equal to <code>num</code> after applying the following operation no more than <code>t</code> times:</p>

<ul>
	<li>Increase or decrease <code>x</code> by <code>1</code>, and simultaneously increase or decrease <code>num</code> by <code>1</code>.</li>
</ul>

<p>Return <em>the maximum possible achievable number</em>. It can be proven that there exists at least one achievable number.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 4, t = 1
<strong>Output:</strong> 6
<strong>Explanation:</strong> The maximum achievable number is x = 6; it can become equal to num after performing this operation:
1- Decrease x by 1, and increase num by 1. Now, x = 5 and num = 5. 
It can be proven that there is no achievable number larger than 6.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 3, t = 2
<strong>Output:</strong> 7
<strong>Explanation:</strong> The maximum achievable number is x = 7; after performing these operations, x will equal num: 
1- Decrease x by 1, and increase num by 1. Now, x = 6 and num = 4.
2- Decrease x by 1, and increase num by 1. Now, x = 5 and num = 5.
It can be proven that there is no achievable number larger than 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num, t&nbsp;&lt;= 50</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

Notice that every time we can decrease $x$ by $1$ and increase $num$ by $1$, the difference between $x$ and $num$ will decrease by $2$, and we can do this operation at most $t$ times, so the maximum reachable number is $num + t \times 2$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def theMaximumAchievableX(self, num: int, t: int) -> int:
        return num + t * 2
```

```java
class Solution {
    public int theMaximumAchievableX(int num, int t) {
        return num + t * 2;
    }
}
```

```cpp
class Solution {
public:
    int theMaximumAchievableX(int num, int t) {
        return num + t * 2;
    }
};
```

```go
func theMaximumAchievableX(num int, t int) int {
	return num + t*2
}
```

```ts
function theMaximumAchievableX(num: number, t: number): number {
    return num + t * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
