---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3741.Minimum%20Distance%20Between%20Three%20Equal%20Elements%20II/README_EN.md
---

<!-- problem:start -->

# [3741. Minimum Distance Between Three Equal Elements II](https://leetcode.com/problems/minimum-distance-between-three-equal-elements-ii)

[中文文档](/solution/3700-3799/3741.Minimum%20Distance%20Between%20Three%20Equal%20Elements%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A tuple <code>(i, j, k)</code> of 3 <strong>distinct</strong> indices is <strong>good</strong> if <code>nums[i] == nums[j] == nums[k]</code>.</p>

<p>The <strong>distance</strong> of a <strong>good</strong> tuple is <code>abs(i - j) + abs(j - k) + abs(k - i)</code>, where <code>abs(x)</code> denotes the <strong>absolute value</strong> of <code>x</code>.</p>

<p>Return an integer denoting the <strong>minimum</strong> possible <strong>distance</strong> of a <strong>good</strong> tuple. If no <strong>good</strong> tuples exist, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum distance is achieved by the good tuple <code>(0, 2, 3)</code>.</p>

<p><code>(0, 2, 3)</code> is a good tuple because <code>nums[0] == nums[2] == nums[3] == 1</code>. Its distance is <code>abs(0 - 2) + abs(2 - 3) + abs(3 - 0) = 2 + 1 + 3 = 6</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,3,2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum distance is achieved by the good tuple <code>(2, 4, 6)</code>.</p>

<p><code>(2, 4, 6)</code> is a good tuple because <code>nums[2] == nums[4] == nums[6] == 2</code>. Its distance is <code>abs(2 - 4) + abs(4 - 6) + abs(6 - 2) = 2 + 2 + 4 = 8</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no good tuples. Therefore, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{g}$ to store the list of indices for each number in the array. While traversing the array, we add each number's index to its corresponding list in the hash table. Define a variable $\textit{ans}$ to store the answer, with an initial value of infinity $\infty$.

Next, we iterate through each index list in the hash table. If the length of an index list for a particular number is greater than or equal to $3$, it means there exists a valid triplet. To minimize the distance, we can choose three consecutive indices $i$, $j$, and $k$ from that number's index list, where $i < j < k$. The distance of this triplet is $j - i + k - j + k - i = 2 \times (k - i)$. We traverse all combinations of three consecutive indices in the list, calculate the distance, and update the answer.

Finally, if the answer is still the initial value $\infty$, it means no valid triplet exists, so we return $-1$; otherwise, we return the calculated minimum distance.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumDistance(self, nums: List[int]) -> int:
        g = defaultdict(list)
        for i, x in enumerate(nums):
            g[x].append(i)
        ans = inf
        for ls in g.values():
            for h in range(len(ls) - 2):
                i, k = ls[h], ls[h + 2]
                ans = min(ans, (k - i) * 2)
        return -1 if ans == inf else ans
```

#### Java

```java
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            g.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        final int inf = 1 << 30;
        int ans = inf;
        for (var ls : g.values()) {
            int m = ls.size();
            for (int h = 0; h < m - 2; ++h) {
                int i = ls.get(h);
                int k = ls.get(h + 2);
                int t = (k - i) * 2;
                ans = Math.min(ans, t);
            }
        }
        return ans == inf ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumDistance(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < n; ++i) {
            g[nums[i]].push_back(i);
        }
        const int inf = 1 << 30;
        int ans = inf;
        for (auto& [_, ls] : g) {
            int m = ls.size();
            for (int h = 0; h < m - 2; ++h) {
                int i = ls[h];
                int k = ls[h + 2];
                int t = (k - i) * 2;
                ans = min(ans, t);
            }
        }
        return ans == inf ? -1 : ans;
    }
};
```

#### Go

```go
func minimumDistance(nums []int) int {
	g := make(map[int][]int)
	for i, x := range nums {
		g[x] = append(g[x], i)
	}

	inf := 1 << 30
	ans := inf

	for _, ls := range g {
		m := len(ls)
		for h := 0; h < m-2; h++ {
			i := ls[h]
			k := ls[h+2]
			t := (k - i) * 2
			ans = min(ans, t)
		}
	}

	if ans == inf {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minimumDistance(nums: number[]): number {
    const n = nums.length;
    const g = new Map<number, number[]>();

    for (let i = 0; i < n; i++) {
        if (!g.has(nums[i])) {
            g.set(nums[i], []);
        }
        g.get(nums[i])!.push(i);
    }

    const inf = 1 << 30;
    let ans = inf;

    for (const ls of g.values()) {
        const m = ls.length;
        for (let h = 0; h < m - 2; h++) {
            const i = ls[h];
            const k = ls[h + 2];
            const t = (k - i) * 2;
            ans = Math.min(ans, t);
        }
    }

    return ans === inf ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
