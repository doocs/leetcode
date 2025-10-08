---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3494.Find%20the%20Minimum%20Amount%20of%20Time%20to%20Brew%20Potions/README_EN.md
rating: 2042
source: Weekly Contest 442 Q3
tags:
    - Array
    - Prefix Sum
    - Simulation
---

<!-- problem:start -->

# [3494. Find the Minimum Amount of Time to Brew Potions](https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions)

[中文文档](/solution/3400-3499/3494.Find%20the%20Minimum%20Amount%20of%20Time%20to%20Brew%20Potions/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays, <code>skill</code> and <code><font face="monospace">mana</font></code>, of length <code>n</code> and <code>m</code>, respectively.</p>

<p>In a laboratory, <code>n</code> wizards must brew <code>m</code> potions <em>in order</em>. Each potion has a mana capacity <code>mana[j]</code> and <strong>must</strong> pass through <strong>all</strong> the wizards sequentially to be brewed properly. The time taken by the <code>i<sup>th</sup></code> wizard on the <code>j<sup>th</sup></code> potion is <code>time<sub>ij</sub> = skill[i] * mana[j]</code>.</p>

<p>Since the brewing process is delicate, a potion <strong>must</strong> be passed to the next wizard immediately after the current wizard completes their work. This means the timing must be <em>synchronized</em> so that each wizard begins working on a potion <strong>exactly</strong> when it arrives. ​</p>

<p>Return the <strong>minimum</strong> amount of time required for the potions to be brewed properly.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = [1,5,2,4], mana = [5,1,4,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">110</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Potion Number</th>
			<th style="border: 1px solid black;">Start time</th>
			<th style="border: 1px solid black;">Wizard 0 done by</th>
			<th style="border: 1px solid black;">Wizard 1 done by</th>
			<th style="border: 1px solid black;">Wizard 2 done by</th>
			<th style="border: 1px solid black;">Wizard 3 done by</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">30</td>
			<td style="border: 1px solid black;">40</td>
			<td style="border: 1px solid black;">60</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">52</td>
			<td style="border: 1px solid black;">53</td>
			<td style="border: 1px solid black;">58</td>
			<td style="border: 1px solid black;">60</td>
			<td style="border: 1px solid black;">64</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">54</td>
			<td style="border: 1px solid black;">58</td>
			<td style="border: 1px solid black;">78</td>
			<td style="border: 1px solid black;">86</td>
			<td style="border: 1px solid black;">102</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">86</td>
			<td style="border: 1px solid black;">88</td>
			<td style="border: 1px solid black;">98</td>
			<td style="border: 1px solid black;">102</td>
			<td style="border: 1px solid black;">110</td>
		</tr>
	</tbody>
</table>

<p>As an example for why wizard 0 cannot start working on the 1<sup>st</sup> potion before time <code>t = 52</code>, consider the case where the wizards started preparing the 1<sup>st</sup> potion at time <code>t = 50</code>. At time <code>t = 58</code>, wizard 2 is done with the 1<sup>st</sup> potion, but wizard 3 will still be working on the 0<sup>th</sup> potion till time <code>t = 60</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = [1,1,1], mana = [1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ol>
	<li>Preparation of the 0<sup>th</sup> potion begins at time <code>t = 0</code>, and is completed by time <code>t = 3</code>.</li>
	<li>Preparation of the 1<sup>st</sup> potion begins at time <code>t = 1</code>, and is completed by time <code>t = 4</code>.</li>
	<li>Preparation of the 2<sup>nd</sup> potion begins at time <code>t = 2</code>, and is completed by time <code>t = 5</code>.</li>
</ol>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = [1,2,3,4], mana = [1,2]</span></p>

<p><strong>Output:</strong> 21</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == skill.length</code></li>
	<li><code>m == mana.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 5000</code></li>
	<li><code>1 &lt;= mana[i], skill[i] &lt;= 5000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i]$ as the time when wizard $i$ completes the previous potion.

For the current potion $x$, we need to calculate the completion time for each wizard. Let $\textit{tot}$ represent the completion time of the current potion, initially $\textit{tot} = 0$.

For each wizard $i$, the time he starts processing the current potion is $\max(\textit{tot}, f[i])$, and the time required to process this potion is $skill[i] \times mana[x]$. Therefore, the time he completes this potion is $\max(\textit{tot}, f[i]) + skill[i] \times mana[x]$. We update $\textit{tot}$ to this value.

Since the brewing process requires that the potion must be immediately passed to the next wizard and processing must start immediately after the current wizard completes their work, we need to update the completion time $f[i]$ for each wizard's previous potion. For the last wizard $n-1$, we directly update $f[n-1]$ to $\textit{tot}$. For other wizards $i$, we can update $f[i]$ by traversing in reverse order, specifically, $f[i] = f[i+1] - skill[i+1] \times mana[x]$.

Finally, $f[n-1]$ is the minimum total time required to complete brewing all potions.

The time complexity is $O(n \times m)$ and the space complexity is $O(n)$, where $n$ and $m$ are the number of wizards and potions respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTime(self, skill: List[int], mana: List[int]) -> int:
        max = lambda a, b: a if a > b else b
        n = len(skill)
        f = [0] * n
        for x in mana:
            tot = 0
            for i in range(n):
                tot = max(tot, f[i]) + skill[i] * x
            f[-1] = tot
            for i in range(n - 2, -1, -1):
                f[i] = f[i + 1] - skill[i + 1] * x
        return f[-1]
```

#### Java

```java
class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        long[] f = new long[n];
        for (int x : mana) {
            long tot = 0;
            for (int i = 0; i < n; ++i) {
                tot = Math.max(tot, f[i]) + skill[i] * x;
            }
            f[n - 1] = tot;
            for (int i = n - 2; i >= 0; --i) {
                f[i] = f[i + 1] - skill[i + 1] * x;
            }
        }
        return f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minTime(vector<int>& skill, vector<int>& mana) {
        int n = skill.size();
        vector<long long> f(n);
        for (int x : mana) {
            long long tot = 0;
            for (int i = 0; i < n; ++i) {
                tot = max(tot, f[i]) + 1LL * skill[i] * x;
            }
            f[n - 1] = tot;
            for (int i = n - 2; i >= 0; --i) {
                f[i] = f[i + 1] - 1LL * skill[i + 1] * x;
            }
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func minTime(skill []int, mana []int) int64 {
	n := len(skill)
	f := make([]int64, n)
	for _, x := range mana {
		var tot int64
		for i := 0; i < n; i++ {
			tot = max(tot, f[i]) + int64(skill[i])*int64(x)
		}
		f[n-1] = tot
		for i := n - 2; i >= 0; i-- {
			f[i] = f[i+1] - int64(skill[i+1])*int64(x)
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function minTime(skill: number[], mana: number[]): number {
    const n = skill.length;
    const f: number[] = Array(n).fill(0);
    for (const x of mana) {
        let tot = 0;
        for (let i = 0; i < n; ++i) {
            tot = Math.max(tot, f[i]) + skill[i] * x;
        }
        f[n - 1] = tot;
        for (let i = n - 2; i >= 0; --i) {
            f[i] = f[i + 1] - skill[i + 1] * x;
        }
    }
    return f[n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
