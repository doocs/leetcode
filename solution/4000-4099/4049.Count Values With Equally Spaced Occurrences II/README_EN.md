---
comments: true
difficulty: Medium
rating: 1405
source: Biweekly Contest 191 Q2
---

<!-- problem:start -->

# [4049. Count Values With Equally Spaced Occurrences II](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-ii)

[中文文档](/solution/4000-4099/4049.Count%20Values%20With%20Equally%20Spaced%20Occurrences%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>An integer <code>x</code> is called <strong>special</strong> if:</p>

<ul>
	<li><code>x</code> appears <strong>at least three</strong> times in <code>nums</code>.</li>
	<li><strong>All</strong> occurrences of <code>x</code> are <strong>equally spaced</strong> in <code>nums</code>. In other words, if all occurrences of <code>x</code> are at indices <code>i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>m</sub></code>, then <code>i<sub>2</sub> - i<sub>1</sub> = i<sub>3</sub> - i<sub>2</sub> = ... = i<sub>m</sub> - i<sub>m-1</sub></code>.</li>
</ul>

<p>Return the number of <strong>distinct</strong> special integers in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,8,1,5,1,5,8,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>1 is special because it occurs at equally spaced indices 0, 2, and 4.</li>
	<li>5 is special because it occurs at equally spaced indices 3, 5, and 7.</li>
	<li>8 is not special because it occurs only twice.</li>
</ul>

<p>Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,8,8,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>8 is special because it occurs at equally spaced indices 0, 1, 2, and 3. Therefore, the answer is 1.</p>
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
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

<!-- thinking:start -->

> **Thinking**
>
> The previous problem only handled exactly three occurrences. Here a value must appear at least three times, and every occurrence must lie on the same common difference. With $n = 10^5$ we cannot rescan the original array for each value.
>
> After grouping indices by value, the lists still have total length $n$. If every adjacent gap equals the first gap, the whole sequence is an arithmetic progression.
>
> Grouping followed by a linear scan of each list is enough.

<!-- thinking:end -->

We use a hash table to record all indices where each integer appears. Traverse $\textit{nums}$ and append index $i$ to the list of $\textit{nums}[i]$.

Then iterate over each index list $\textit{pos}$ in the hash table. Skip it if its length is less than $3$. Otherwise let $d = \textit{pos}[1] - \textit{pos}[0]$ and check whether every adjacent gap equals $d$. If so, the integer is special and we increment the answer by $1$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialIntegers(self, nums: list[int]) -> int:
        g = defaultdict(list)
        for i, x in enumerate(nums):
            g[x].append(i)
        ans = 0
        for pos in g.values():
            if len(pos) < 3:
                continue
            d = pos[1] - pos[0]
            if all(j - i == d for i, j in pairwise(pos)):
                ans += 1
        return ans
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
            if (pos.size() < 3) {
                continue;
            }

            int d = pos.get(1) - pos.get(0);
            boolean ok = true;
            for (int i = 1; i < pos.size(); i++) {
                if (pos.get(i) - pos.get(i - 1) != d) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
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
            if (pos.size() < 3) {
                continue;
            }

            int d = pos[1] - pos[0];
            bool ok = true;
            for (int i = 1; i < pos.size(); i++) {
                if (pos[i] - pos[i - 1] != d) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
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
		if len(pos) < 3 {
			continue
		}

		d := pos[1] - pos[0]
		ok := true
		for i := 1; i < len(pos); i++ {
			if pos[i]-pos[i-1] != d {
				ok = false
				break
			}
		}

		if ok {
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
        if (pos.length < 3) {
            continue;
        }

        const d = pos[1] - pos[0];
        let ok = true;
        for (let i = 1; i < pos.length; i++) {
            if (pos[i] - pos[i - 1] !== d) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans++;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
