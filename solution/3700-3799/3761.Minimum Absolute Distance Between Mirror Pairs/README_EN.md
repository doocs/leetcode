---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3761.Minimum%20Absolute%20Distance%20Between%20Mirror%20Pairs/README_EN.md
---

<!-- problem:start -->

# [3761. Minimum Absolute Distance Between Mirror Pairs](https://leetcode.com/problems/minimum-absolute-distance-between-mirror-pairs)

[中文文档](/solution/3700-3799/3761.Minimum%20Absolute%20Distance%20Between%20Mirror%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong>mirror pair</strong> is a pair of indices <code>(i, j)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; nums.length</code>, and</li>
	<li><code>reverse(nums[i]) == nums[j]</code>, where <code>reverse(x)</code> denotes the integer formed by reversing the digits of <code>x</code>. Leading zeros are omitted after reversing, for example <code>reverse(120) = 21</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> absolute distance between the indices of any mirror pair. The absolute distance between indices <code>i</code> and <code>j</code> is <code>abs(i - j)</code>.</p>

<p>If no mirror pair exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [12,21,45,33,54]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The mirror pairs are:</p>

<ul>
	<li>(0, 1) since <code>reverse(nums[0]) = reverse(12) = 21 = nums[1]</code>, giving an absolute distance <code>abs(0 - 1) = 1</code>.</li>
	<li>(2, 4) since <code>reverse(nums[2]) = reverse(45) = 54 = nums[4]</code>, giving an absolute distance <code>abs(2 - 4) = 2</code>.</li>
</ul>

<p>The minimum absolute distance among all pairs is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [120,21]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one mirror pair (0, 1) since <code>reverse(nums[0]) = reverse(120) = 21 = nums[1]</code>.</p>

<p>The minimum absolute distance is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [21,120]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no mirror pairs in the array.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{pos}$ to record the last occurrence position of each reversed number.

We first initialize the answer $\textit{ans} = n + 1$, where $n$ is the length of the array $\textit{nums}$.

Next, we iterate through the array $\textit{nums}$. For each index $i$ and its corresponding number $x = \textit{nums}[i]$, if the key $x$ exists in $\textit{pos}$, it means there exists an index $j$ such that $\textit{nums}[j]$ reversed equals $x$. In this case, we update the answer to $\min(\textit{ans}, i - \textit{pos}[x])$. Then, we update $\textit{pos}[\text{reverse}(x)]$ to $i$. Continue this process until we finish iterating through the entire array.

Finally, if the answer $\textit{ans}$ is still equal to $n + 1$, it means no mirror pair exists, and we return $-1$; otherwise, we return the answer $\textit{ans}$.

The time complexity is $O(n \times \log M)$, where $n$ is the length of the array $\textit{nums}$, and $M$ is the maximum value in the array. The space complexity is $O(n)$, which is used to store the hash table $\textit{pos}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMirrorPairDistance(self, nums: List[int]) -> int:
        def reverse(x: int) -> int:
            y = 0
            while x:
                v = x % 10
                y = y * 10 + v
                x //= 10
            return y

        pos = {}
        ans = inf
        for i, x in enumerate(nums):
            if x in pos:
                ans = min(ans, i - pos[x])
            pos[reverse(x)] = i
        return -1 if ans == inf else ans
```

#### Java

```java
class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> pos = new HashMap<>(n);
        int ans = n + 1;
        for (int i = 0; i < n; ++i) {
            if (pos.containsKey(nums[i])) {
                ans = Math.min(ans, i - pos.get(nums[i]));
            }
            pos.put(reverse(nums[i]), i);
        }
        return ans > n ? -1 : ans;
    }

    private int reverse(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMirrorPairDistance(vector<int>& nums) {
        int n = nums.size();
        int ans = n + 1;
        unordered_map<int, int> pos;
        auto reverse = [](int x) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        for (int i = 0; i < n; ++i) {
            if (pos.contains(nums[i])) {
                ans = min(ans, i - pos[nums[i]]);
            }
            pos[reverse(nums[i])] = i;
        }
        return ans > n ? -1 : ans;
    }
};
```

#### Go

```go
func minMirrorPairDistance(nums []int) int {
	n := len(nums)
	pos := map[int]int{}
	ans := n + 1
	reverse := func(x int) int {
		y := 0
		for ; x > 0; x /= 10 {
			y = y*10 + x%10
		}
		return y
	}
	for i, x := range nums {
		if j, ok := pos[x]; ok {
			ans = min(ans, i-j)
		}
		pos[reverse(x)] = i
	}
	if ans > n {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minMirrorPairDistance(nums: number[]): number {
    const n = nums.length;
    const pos = new Map<number, number>();
    let ans = n + 1;
    const reverse = (x: number) => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    for (let i = 0; i < n; ++i) {
        if (pos.has(nums[i])) {
            const j = pos.get(nums[i])!;
            ans = Math.min(ans, i - j);
        }
        pos.set(reverse(nums[i]), i);
    }
    return ans > n ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
