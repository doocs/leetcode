---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0517.Super%20Washing%20Machines/README_EN.md
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [517. Super Washing Machines](https://leetcode.com/problems/super-washing-machines)

[中文文档](/solution/0500-0599/0517.Super%20Washing%20Machines/README.md)

## Description

<p>You have <code>n</code> super washing machines on a line. Initially, each washing machine has some dresses or is empty.</p>

<p>For each move, you could choose any <code>m</code> (<code>1 &lt;= m &lt;= n</code>) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time.</p>

<p>Given an integer array <code>machines</code> representing the number of dresses in each washing machine from left to right on the line, return <em>the minimum number of moves to make all the washing machines have the same number of dresses</em>. If it is not possible to do it, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> machines = [1,0,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
1st move:    1     0 &lt;-- 5    =&gt;    1     1     4
2nd move:    1 &lt;-- 1 &lt;-- 4    =&gt;    2     1     3
3rd move:    2     1 &lt;-- 3    =&gt;    2     2     2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> machines = [0,3,0]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
1st move:    0 &lt;-- 3     0    =&gt;    1     2     0
2nd move:    1     2 --&gt; 0    =&gt;    1     1     1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> machines = [0,2,0]
<strong>Output:</strong> -1
<strong>Explanation:</strong>
It&#39;s impossible to make all three washing machines have the same number of dresses.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == machines.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= machines[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

If the total number of clothes in the washing machines cannot be divided evenly by the number of washing machines, it is impossible to make the number of clothes in each washing machine equal, so we directly return $-1$.

Otherwise, suppose the total number of clothes in the washing machines is $s$, then the number of clothes in each washing machine will eventually become $k = s / n$.

We define $a_i$ as the difference between the number of clothes in the $i$-th washing machine and $k$, that is, $a_i = \text{machines}[i] - k$. If $a_i > 0$, it means that the $i$-th washing machine has extra clothes and needs to pass them to the adjacent washing machine; if $a_i < 0$, it means that the $i$-th washing machine lacks clothes and needs to get them from the adjacent washing machine.

We define the sum of the differences in the number of clothes in the first $i$ washing machines as $s_i = \sum_{j=0}^{i-1} a_j$. If we regard the first $i$ washing machines as one group and the remaining washing machines as another group. Then if $s_i$ is a positive number, it means that the first group of washing machines has extra clothes and needs to pass them to the second group of washing machines; if $s_i$ is a negative number, it means that the first group of washing machines lacks clothes and needs to get them from the second group of washing machines.

Then there are the following two situations:

1. The maximum number of times clothes need to be moved between the two groups is $\max_{i=0}^{n-1} \lvert s_i \rvert$;
1. A washing machine in the group has too many clothes and needs to move clothes to both sides, the maximum number of times clothes need to be moved is $\max_{i=0}^{n-1} a_i$.

We take the maximum of the two.

The time complexity is $O(n)$, where $n$ is the number of washing machines. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findMinMoves(self, machines: List[int]) -> int:
        n = len(machines)
        k, mod = divmod(sum(machines), n)
        if mod:
            return -1
        ans = s = 0
        for x in machines:
            x -= k
            s += x
            ans = max(ans, abs(s), x)
        return ans
```

```java
class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int s = 0;
        for (int x : machines) {
            s += x;
        }
        if (s % n != 0) {
            return -1;
        }
        int k = s / n;
        s = 0;
        int ans = 0;
        for (int x : machines) {
            x -= k;
            s += x;
            ans = Math.max(ans, Math.max(Math.abs(s), x));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findMinMoves(vector<int>& machines) {
        int n = machines.size();
        int s = accumulate(machines.begin(), machines.end(), 0);
        if (s % n) {
            return -1;
        }
        int k = s / n;
        s = 0;
        int ans = 0;
        for (int x : machines) {
            x -= k;
            s += x;
            ans = max({ans, abs(s), x});
        }
        return ans;
    }
};
```

```go
func findMinMoves(machines []int) (ans int) {
	n := len(machines)
	s := 0
	for _, x := range machines {
		s += x
	}
	if s%n != 0 {
		return -1
	}
	k := s / n
	s = 0
	for _, x := range machines {
		x -= k
		s += x
		ans = max(ans, max(abs(s), x))
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function findMinMoves(machines: number[]): number {
    const n = machines.length;
    let s = machines.reduce((a, b) => a + b);
    if (s % n !== 0) {
        return -1;
    }
    const k = Math.floor(s / n);
    s = 0;
    let ans = 0;
    for (let x of machines) {
        x -= k;
        s += x;
        ans = Math.max(ans, Math.abs(s), x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
