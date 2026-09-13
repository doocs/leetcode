---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4048. Count Values With Equally Spaced Occurrences I](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-i)

[中文文档](/solution/4000-4099/4048.Count%20Values%20With%20Equally%20Spaced%20Occurrences%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>An integer <code>x</code> is called <strong>special</strong> if:</p>

<ul>
	<li><code>x</code> appears <strong>exactly three</strong> times in <code>nums</code>.</li>
	<li><strong>All</strong> three occurrences of <code>x</code> are <strong>equally spaced</strong> in <code>nums</code>. In other words, if all occurrences of <code>x</code> are at indices <code>i<sub>1</sub> &lt; i<sub>2</sub> &lt; i<sub>3</sub></code>, then <code>i<sub>2</sub> - i<sub>1</sub> = i<sub>3</sub> - i<sub>2</sub></code>.</li>
</ul>

<p>Return the number of <strong>distinct</strong> special integers in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,8,1,5,1,5,8,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>1 is special because it occurs exactly three times at equally spaced indices 0, 2, and 4.</li>
	<li>5 is special because it occurs exactly three times at equally spaced indices 3, 5, and 7.</li>
	<li>8 is not special because it occurs only twice.</li>
</ul>

<p>Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,8,8,8]</span></p>

<p><strong>Output:</strong>&nbsp;0</p>

<p><strong>Explanation:</strong></p>

<p>8 is not special because it does not occur exactly three times. Therefore, the answer is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,6,6,8,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>8 occurs at indices 0, 3, and 4, which are not equally spaced. 6 occurs only twice. Therefore, no integer is special.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

<!-- thinking:start -->

> **Thinking**
>
> $n \le 100$, so even scanning the array once per distinct value would pass. A special integer must appear exactly three times, and those three indices must form an arithmetic progression.
>
> After collecting the indices of each value, the check reduces to two facts: the list has length $3$, and the first plus the last index equals twice the middle one.
>
> A hash table groups the indices in a single pass.

<!-- thinking:end -->

We use a hash table to record all indices where each integer appears. Traverse $\textit{nums}$ and append index $i$ to the list of $\textit{nums}[i]$.

Then iterate over each index list $\textit{pos}$ in the hash table. If $\textit{pos}$ has length $3$ and $\textit{pos}[0] + \textit{pos}[2] = 2 \times \textit{pos}[1]$ (the three occurrences are equally spaced), the integer is special and we increment the answer by $1$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialIntegers(self, nums: list[int]) -> int:
        g = defaultdict(list)
        for i, x in enumerate(nums):
            g[x].append(i)
        return sum(
            len(pos) == 3 and pos[0] + pos[2] == pos[1] * 2 for pos in g.values()
        )
```

#### Java

```java
class Solution {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            g.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (List<Integer> pos : g.values()) {
            if (pos.size() == 3 && pos.get(0) + pos.get(2) == pos.get(1) * 2) {
                ans++;
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
    int countSpecialIntegers(vector<int>& nums) {
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < nums.size(); i++) {
            g[nums[i]].push_back(i);
        }

        int ans = 0;
        for (auto& [x, pos] : g) {
            if (pos.size() == 3 && pos[0] + pos[2] == pos[1] * 2) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSpecialIntegers(nums []int) int {
	g := make(map[int][]int)
	for i, x := range nums {
		g[x] = append(g[x], i)
	}

	ans := 0
	for _, pos := range g {
		if len(pos) == 3 && pos[0]+pos[2] == pos[1]*2 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSpecialIntegers(nums: number[]): number {
    const g = new Map<number, number[]>();

    for (let i = 0; i < nums.length; i++) {
        if (!g.has(nums[i])) {
            g.set(nums[i], []);
        }
        g.get(nums[i])!.push(i);
    }

    let ans = 0;
    for (const pos of g.values()) {
        if (pos.length === 3 && pos[0] + pos[2] === pos[1] * 2) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
