---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0825.Friends%20Of%20Appropriate%20Ages/README_EN.md
tags:
    - Array
    - Two Pointers
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [825. Friends Of Appropriate Ages](https://leetcode.com/problems/friends-of-appropriate-ages)

[中文文档](/solution/0800-0899/0825.Friends%20Of%20Appropriate%20Ages/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> persons on a social media website. You are given an integer array <code>ages</code> where <code>ages[i]</code> is the age of the <code>i<sup>th</sup></code> person.</p>

<p>A Person <code>x</code> will not send a friend request to a person <code>y</code> (<code>x != y</code>) if any of the following conditions is true:</p>

<ul>
	<li><code>age[y] &lt;= 0.5 * age[x] + 7</code></li>
	<li><code>age[y] &gt; age[x]</code></li>
	<li><code>age[y] &gt; 100 &amp;&amp; age[x] &lt; 100</code></li>
</ul>

<p>Otherwise, <code>x</code> will send a friend request to <code>y</code>.</p>

<p>Note that if <code>x</code> sends a request to <code>y</code>, <code>y</code> will not necessarily send a request to <code>x</code>. Also, a person will not send a friend request to themself.</p>

<p>Return <em>the total number of friend requests made</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ages = [16,16]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 2 people friend request each other.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ages = [16,17,18]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Friend requests are made 17 -&gt; 16, 18 -&gt; 17.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> ages = [20,30,100,110,120]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Friend requests are made 110 -&gt; 100, 120 -&gt; 110, 120 -&gt; 100.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == ages.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= ages[i] &lt;= 120</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Enumeration

We can use an array $\textit{cnt}$ of length $121$ to record the number of people of each age.

Next, we enumerate all possible age pairs $(\textit{ax}, \textit{ay})$. If $\textit{ax}$ and $\textit{ay}$ satisfy the conditions given in the problem, these age pairs $(\textit{ax}, \textit{ay})$ can send friend requests to each other.

If $\textit{ax} = \textit{ay}$, meaning the ages are the same, then the number of friend requests between $\textit{ax}$ and $\textit{ay}$ is $\textit{cnt}[\textit{ax}] \times (\textit{cnt}[\textit{ax}] - 1)$. Otherwise, if the ages are different, the number of friend requests between $\textit{ax}$ and $\textit{ay}$ is $\textit{cnt}[\textit{ax}] \times \textit{cnt}[\textit{ay}]$. We accumulate these friend request counts into the answer.

The time complexity is $O(n + m^2)$, where $n$ is the length of the array $\textit{ages}$, and $m$ is the maximum age, which is $121$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numFriendRequests(self, ages: List[int]) -> int:
        cnt = [0] * 121
        for x in ages:
            cnt[x] += 1
        ans = 0
        for ax, x in enumerate(cnt):
            for ay, y in enumerate(cnt):
                if not (ay <= 0.5 * ax + 7 or ay > ax or (ay > 100 and ax < 100)):
                    ans += x * (y - int(ax == ay))
        return ans
```

#### Java

```java
class Solution {
    public int numFriendRequests(int[] ages) {
        final int m = 121;
        int[] cnt = new int[m];
        for (int x : ages) {
            ++cnt[x];
        }
        int ans = 0;
        for (int ax = 1; ax < m; ++ax) {
            for (int ay = 1; ay < m; ++ay) {
                if (!(ay <= 0.5 * ax + 7 || ay > ax || (ay > 100 && ax < 100))) {
                    ans += cnt[ax] * (cnt[ay] - (ax == ay ? 1 : 0));
                }
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
    int numFriendRequests(vector<int>& ages) {
        const int m = 121;
        vector<int> cnt(m);
        for (int x : ages) {
            ++cnt[x];
        }
        int ans = 0;
        for (int ax = 1; ax < m; ++ax) {
            for (int ay = 1; ay < m; ++ay) {
                if (!(ay <= 0.5 * ax + 7 || ay > ax || (ay > 100 && ax < 100))) {
                    ans += cnt[ax] * (cnt[ay] - (ax == ay ? 1 : 0));
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numFriendRequests(ages []int) (ans int) {
	cnt := [121]int{}
	for _, x := range ages {
		cnt[x]++
	}
	for ax, x := range cnt {
		for ay, y := range cnt {
			if ay <= ax/2+7 || ay > ax || (ay > 100 && ax < 100) {
				continue
			}
			if ax == ay {
				ans += x * (x - 1)
			} else {
				ans += x * y
			}
		}
	}

	return
}
```

#### TypeScript

```ts
function numFriendRequests(ages: number[]): number {
    const m = 121;
    const cnt = Array(m).fill(0);
    for (const x of ages) {
        cnt[x]++;
    }

    let ans = 0;
    for (let ax = 0; ax < m; ax++) {
        for (let ay = 0; ay < m; ay++) {
            if (ay <= 0.5 * ax + 7 || ay > ax || (ay > 100 && ax < 100)) {
                continue;
            }
            ans += cnt[ax] * (cnt[ay] - (ax === ay ? 1 : 0));
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
