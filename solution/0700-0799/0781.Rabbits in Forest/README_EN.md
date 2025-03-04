---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0781.Rabbits%20in%20Forest/README_EN.md
tags:
    - Greedy
    - Array
    - Hash Table
    - Math
---

<!-- problem:start -->

# [781. Rabbits in Forest](https://leetcode.com/problems/rabbits-in-forest)

[中文文档](/solution/0700-0799/0781.Rabbits%20in%20Forest/README.md)

## Description

<!-- description:start -->

<p>There is a forest with an unknown number of rabbits. We asked n rabbits <strong>&quot;How many rabbits have the same color as you?&quot;</strong> and collected the answers in an integer array <code>answers</code> where <code>answers[i]</code> is the answer of the <code>i<sup>th</sup></code> rabbit.</p>

<p>Given the array <code>answers</code>, return <em>the minimum number of rabbits that could be in the forest</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> answers = [1,1,2]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The two rabbits that answered &quot;1&quot; could both be the same color, say red.
The rabbit that answered &quot;2&quot; can&#39;t be red or the answers would be inconsistent.
Say the rabbit that answered &quot;2&quot; was blue.
Then there should be 2 other blue rabbits in the forest that didn&#39;t answer into the array.
The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn&#39;t.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> answers = [10,10,10]
<strong>Output:</strong> 11
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= answers.length &lt;= 1000</code></li>
	<li><code>0 &lt;= answers[i] &lt; 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Hash Map

According to the problem description, rabbits that give the same answer may belong to the same color, while rabbits that give different answers cannot belong to the same color.

Therefore, we use a hash map $\textit{cnt}$ to record the number of occurrences of each answer. For each answer $x$ and its occurrence $v$, we calculate the minimum number of rabbits based on the principle that each color has $x + 1$ rabbits, and add it to the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $\textit{answers}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numRabbits(self, answers: List[int]) -> int:
        cnt = Counter(answers)
        ans = 0
        for x, v in cnt.items():
            group = x + 1
            ans += (v + group - 1) // group * group
        return ans
```

#### Java

```java
class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : answers) {
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int group = e.getKey() + 1;
            ans += (e.getValue() + group - 1) / group * group;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numRabbits(vector<int>& answers) {
        unordered_map<int, int> cnt;
        for (int x : answers) {
            ++cnt[x];
        }
        int ans = 0;
        for (auto& [x, v] : cnt) {
            int group = x + 1;
            ans += (v + group - 1) / group * group;
        }
        return ans;
    }
};
```

#### Go

```go
func numRabbits(answers []int) (ans int) {
	cnt := map[int]int{}
	for _, x := range answers {
		cnt[x]++
	}
	for x, v := range cnt {
		group := x + 1
		ans += (v + group - 1) / group * group
	}
	return
}
```

#### TypeScript

```ts
function numRabbits(answers: number[]): number {
    const cnt = new Map<number, number>();
    for (const x of answers) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    let ans = 0;
    for (const [x, v] of cnt.entries()) {
        const group = x + 1;
        ans += Math.floor((v + group - 1) / group) * group;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
