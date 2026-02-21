---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3842.Toggle%20Light%20Bulbs/README_EN.md
rating: 1160
source: Weekly Contest 489 Q1
---

<!-- problem:start -->

# [3842. Toggle Light Bulbs](https://leetcode.com/problems/toggle-light-bulbs)

[中文文档](/solution/3800-3899/3842.Toggle%20Light%20Bulbs/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>bulbs</code> of integers between 1 and 100.</p>

<p>There are 100 light bulbs numbered from 1 to 100. All of them are switched off initially.</p>

<p>For each element <code>bulbs[i]</code> in the array <code>bulbs</code>:</p>

<ul>
	<li>If the <code>bulbs[i]<sup>th</sup></code> light bulb is currently off, switch it on.</li>
	<li>Otherwise, switch it off.</li>
</ul>

<p>Return the list of integers denoting the light bulbs that are on in the end, <strong>sorted</strong> in <strong>ascending</strong> order. If no bulb is on, return an empty list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> bulbs<span class="example-io"> = [10,30,20,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">[20,30]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The <code>bulbs[0] = 10<sup>th</sup></code> light bulb is currently off. We switch it on.</li>
	<li>The <code>bulbs[1] = 30<sup>th</sup></code> light bulb is currently off. We switch it on.</li>
	<li>The <code>bulbs[2] = 20<sup>th</sup></code> light bulb is currently off. We switch it on.</li>
	<li>The <code>bulbs[3] = 10<sup>th</sup></code> light bulb is currently on. We switch it off.</li>
	<li>In the end, the 20<sup>th</sup> and the 30<sup>th</sup> light bulbs are on.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> bulbs<span class="example-io"> = [100,100]</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The <code>bulbs[0] = 100<sup>th</sup></code> light bulb is currently off. We switch it on.</li>
	<li>The <code>bulbs[1] = 100<sup>th</sup></code> light bulb is currently on. We switch it off.</li>
	<li>In the end, no light bulb is on.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= bulbs.length &lt;= 100</code></li>
	<li><code>1 &lt;= bulbs[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use an array $\textit{st}$ of length $101$ to record the state of each light bulb. Initially, all elements are $0$, indicating that all light bulbs are in the off state. For each element $\textit{bulbs}[i]$ in the array $\textit{bulbs}$, we toggle the value of $\textit{st}[\textit{bulbs}[i]]$ (i.e., $0$ becomes $1$, and $1$ becomes $0$). Finally, we traverse the $\textit{st}$ array, add the indices with a value of $1$ to the result list, and return the result.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{bulbs}$. The space complexity is $O(M)$, where $M$ is the maximum bulb number.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def toggleLightBulbs(self, bulbs: list[int]) -> list[int]:
        st = [0] * 101
        for x in bulbs:
            st[x] ^= 1
        return [i for i, x in enumerate(st) if x]
```

#### Java

```java
class Solution {
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        int[] st = new int[101];
        for (int x : bulbs) {
            st[x] ^= 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < st.length; ++i) {
            if (st[i] == 1) {
                ans.add(i);
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
    vector<int> toggleLightBulbs(vector<int>& bulbs) {
        vector<int> st(101, 0);
        for (int x : bulbs) {
            st[x] ^= 1;
        }
        vector<int> ans;
        for (int i = 0; i < 101; ++i) {
            if (st[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func toggleLightBulbs(bulbs []int) []int {
	st := make([]int, 101)
	for _, x := range bulbs {
		st[x] ^= 1
	}
	ans := make([]int, 0)
	for i := 0; i < 101; i++ {
		if st[i] == 1 {
			ans = append(ans, i)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function toggleLightBulbs(bulbs: number[]): number[] {
    const st: number[] = new Array(101).fill(0);
    for (const x of bulbs) {
        st[x] ^= 1;
    }
    const ans: number[] = [];
    for (let i = 0; i < 101; i++) {
        if (st[i] === 1) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
