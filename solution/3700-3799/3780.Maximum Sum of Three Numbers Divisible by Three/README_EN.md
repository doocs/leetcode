---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3780.Maximum%20Sum%20of%20Three%20Numbers%20Divisible%20by%20Three/README_EN.md
rating: 1584
source: Biweekly Contest 172 Q2
tags:
    - Greedy
    - Array
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3780. Maximum Sum of Three Numbers Divisible by Three](https://leetcode.com/problems/maximum-sum-of-three-numbers-divisible-by-three)

[中文文档](/solution/3700-3799/3780.Maximum%20Sum%20of%20Three%20Numbers%20Divisible%20by%20Three/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Your task is to choose <strong>exactly three</strong> integers from <code>nums</code> such that their sum is divisible by three.</p>

<p>Return the <strong>maximum</strong> possible sum of such a triplet. If no such triplet exists, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid triplets whose sum is divisible by 3 are:</p>

<ul>
	<li><code>(4, 2, 3)</code> with a sum of <code>4 + 2 + 3 = 9</code>.</li>
	<li><code>(2, 3, 1)</code> with a sum of <code>2 + 3 + 1 = 6</code>.</li>
</ul>

<p>Thus, the answer is 9.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No triplet forms a sum divisible by 3, so the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Grouping + Enumeration

We first sort the array $\textit{nums}$, then divide the elements in the array into three groups based on their modulo $3$ results, denoted as $\textit{g}[0]$, $\textit{g}[1]$, and $\textit{g}[2]$. Where $\textit{g}[i]$ stores all elements that satisfy $\textit{nums}[j] \bmod 3 = i$.

Next, we enumerate the cases of selecting one element each from $\textit{g}[a]$ and $\textit{g}[b]$, where $a, b \in \{0, 1, 2\}$. Based on the modulo $3$ results of the two selected elements, we can determine which group the third element should be selected from to ensure that the sum of the triplet is divisible by $3$. Specifically, the third element should be selected from $\textit{g}[c]$, where $c = (3 - (a + b) \bmod 3) \bmod 3$.

For each combination of $(a, b)$, we try to take out the largest element from both $\textit{g}[a]$ and $\textit{g}[b]$, then take out the largest element from $\textit{g}[c]$, calculate the sum of these three elements, and update the answer.

The time complexity is $O(n \log n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        nums.sort()
        g = [[] for _ in range(3)]
        for x in nums:
            g[x % 3].append(x)
        ans = 0
        for a in range(3):
            if g[a]:
                x = g[a].pop()
                for b in range(3):
                    if g[b]:
                        y = g[b].pop()
                        c = (3 - (a + b) % 3) % 3
                        if g[c]:
                            z = g[c][-1]
                            ans = max(ans, x + y + z)
                        g[b].append(y)
                g[a].append(x)
        return ans
```

#### Java

```java
class Solution {
    public int maximumSum(int[] nums) {
        Arrays.sort(nums);
        List<Integer>[] g = new ArrayList[3];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int x : nums) {
            g[x % 3].add(x);
        }
        int ans = 0;
        for (int a = 0; a < 3; a++) {
            if (!g[a].isEmpty()) {
                int x = g[a].remove(g[a].size() - 1);
                for (int b = 0; b < 3; b++) {
                    if (!g[b].isEmpty()) {
                        int y = g[b].remove(g[b].size() - 1);
                        int c = (3 - (a + b) % 3) % 3;
                        if (!g[c].isEmpty()) {
                            int z = g[c].get(g[c].size() - 1);
                            ans = Math.max(ans, x + y + z);
                        }
                        g[b].add(y);
                    }
                }
                g[a].add(x);
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
    int maximumSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> g(3);
        for (int x : nums) {
            g[x % 3].push_back(x);
        }
        int ans = 0;
        for (int a = 0; a < 3; a++) {
            if (!g[a].empty()) {
                int x = g[a].back();
                g[a].pop_back();
                for (int b = 0; b < 3; b++) {
                    if (!g[b].empty()) {
                        int y = g[b].back();
                        g[b].pop_back();
                        int c = (3 - (a + b) % 3) % 3;
                        if (!g[c].empty()) {
                            int z = g[c].back();
                            ans = max(ans, x + y + z);
                        }
                        g[b].push_back(y);
                    }
                }
                g[a].push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumSum(nums []int) int {
	sort.Ints(nums)
	g := make([][]int, 3)
	for _, x := range nums {
		g[x%3] = append(g[x%3], x)
	}
	ans := 0
	for a := 0; a < 3; a++ {
		if len(g[a]) > 0 {
			x := g[a][len(g[a])-1]
			g[a] = g[a][:len(g[a])-1]
			for b := 0; b < 3; b++ {
				if len(g[b]) > 0 {
					y := g[b][len(g[b])-1]
					g[b] = g[b][:len(g[b])-1]
					c := (3 - (a+b)%3) % 3
					if len(g[c]) > 0 {
						z := g[c][len(g[c])-1]
						ans = max(ans, x+y+z)
					}
					g[b] = append(g[b], y)
				}
			}
			g[a] = append(g[a], x)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maximumSum(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const g: number[][] = Array.from({ length: 3 }, () => []);
    for (const x of nums) {
        g[x % 3].push(x);
    }
    let ans = 0;
    for (let a = 0; a < 3; a++) {
        if (g[a].length > 0) {
            const x = g[a].pop()!;
            for (let b = 0; b < 3; b++) {
                if (g[b].length > 0) {
                    const y = g[b].pop()!;
                    const c = (3 - ((a + b) % 3)) % 3;
                    if (g[c].length > 0) {
                        const z = g[c][g[c].length - 1];
                        ans = Math.max(ans, x + y + z);
                    }
                    g[b].push(y);
                }
            }
            g[a].push(x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
