---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3141.Maximum%20Hamming%20Distances/README_EN.md
---

# [3141. Maximum Hamming Distances 🔒](https://leetcode.com/problems/maximum-hamming-distances)

[中文文档](/solution/3100-3199/3141.Maximum%20Hamming%20Distances/README.md)

## Description

<p>Given an array <code>nums</code> and an integer <code>m</code>, with each element <code>nums[i]</code> satisfying <code>0 &lt;= nums[i] &lt; 2<sup>m</sup></code>, return an array <code>answer</code>. The <code>answer</code> array should be of the same length as <code>nums</code>, where each element <code>answer[i]</code> represents the <em>maximum</em> <strong>Hamming distance </strong>between <code>nums[i]</code> and any other element <code>nums[j]</code> in the array.</p>

<p>The <strong>Hamming distance</strong> between two binary integers is defined as the number of positions at which the corresponding bits differ (add leading zeroes if needed).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,12,9,11], m = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary representation of <code>nums = [1001,1100,1001,1011]</code>.</p>

<p>The maximum hamming distances for each index are:</p>

<ul>
	<li><code>nums[0]</code>: 1001 and 1100 have a distance of 2.</li>
	<li><code>nums[1]</code>: 1100 and 1011 have a distance of 3.</li>
	<li><code>nums[2]</code>: 1001 and 1100 have a distance of 2.</li>
	<li><code>nums[3]</code>: 1011 and 1100 have a distance of 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,6,10], m = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,3,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary representation of <code>nums = [0011,0100,0110,1010]</code>.</p>

<p>The maximum hamming distances for each index are:</p>

<ul>
	<li><code>nums[0]</code>: 0011 and 0100 have a distance of 3.</li>
	<li><code>nums[1]</code>: 0100 and 0011 have a distance of 3.</li>
	<li><code>nums[2]</code>: 0110 and 1010 have a distance of 2.</li>
	<li><code>nums[3]</code>: 1010 and 0100 have a distance of 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= 17</code></li>
	<li><code>2 &lt;= nums.length &lt;= 2<sup>m</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>m</sup></code></li>
</ul>

## Solutions

### Solution 1: Reverse Thinking + BFS

The problem requires us to find the maximum Hamming distance between each element and other elements in the array. We can think in reverse: for each element, we take its complement and find the minimum Hamming distance to other elements in the array. Then, the maximum Hamming distance we are looking for is $m$ minus this minimum Hamming distance.

We can use Breadth-First Search (BFS) to find the minimum Hamming distance from each complemented element to other elements.

The specific steps are as follows:

1. Initialize an array $\text{dist}$ with a length of $2^m$ to record the minimum Hamming distance from each complemented element to other elements. Initially, all are set to $-1$.
2. Traverse the array $\text{nums}$, set the complement of each element to $0$, and add it to the queue $\text{q}$.
3. Starting from $k = 1$, continuously traverse the queue $\text{q}$. Each time, take out the elements in the queue, perform $m$ complement operations on them, add the complemented elements to the queue $\text{t}$, and set the minimum Hamming distance to the original element to $k$.
4. Repeat step 3 until the queue is empty.

Finally, we traverse the array $\text{nums}$, take the complement of each element as the index, and take out the corresponding minimum Hamming distance from the $\text{dist}$ array. Then, $m$ minus this value is the maximum Hamming distance we are looking for.

The time complexity is $O(2^m)$, and the space complexity is $O(2^m)$, where $m$ is the integer given in the problem.

<!-- tabs:start -->

```python
class Solution:
    def maxHammingDistances(self, nums: List[int], m: int) -> List[int]:
        dist = [-1] * (1 << m)
        for x in nums:
            dist[x] = 0
        q = nums
        k = 1
        while q:
            t = []
            for x in q:
                for i in range(m):
                    y = x ^ (1 << i)
                    if dist[y] == -1:
                        t.append(y)
                        dist[y] = k
            q = t
            k += 1
        return [m - dist[x ^ ((1 << m) - 1)] for x in nums]
```

```java
class Solution {
    public int[] maxHammingDistances(int[] nums, int m) {
        int[] dist = new int[1 << m];
        Arrays.fill(dist, -1);
        Deque<Integer> q = new ArrayDeque<>();
        for (int x : nums) {
            dist[x] = 0;
            q.offer(x);
        }
        for (int k = 1; !q.isEmpty(); ++k) {
            for (int t = q.size(); t > 0; --t) {
                int x = q.poll();
                for (int i = 0; i < m; ++i) {
                    int y = x ^ (1 << i);
                    if (dist[y] == -1) {
                        q.offer(y);
                        dist[y] = k;
                    }
                }
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = m - dist[nums[i] ^ ((1 << m) - 1)];
        }
        return nums;
    }
}
```

```cpp
class Solution {
public:
    vector<int> maxHammingDistances(vector<int>& nums, int m) {
        int dist[1 << m];
        memset(dist, -1, sizeof(dist));
        queue<int> q;
        for (int x : nums) {
            dist[x] = 0;
            q.push(x);
        }
        for (int k = 1; q.size(); ++k) {
            for (int t = q.size(); t; --t) {
                int x = q.front();
                q.pop();
                for (int i = 0; i < m; ++i) {
                    int y = x ^ (1 << i);
                    if (dist[y] == -1) {
                        dist[y] = k;
                        q.push(y);
                    }
                }
            }
        }
        for (int& x : nums) {
            x = m - dist[x ^ ((1 << m) - 1)];
        }
        return nums;
    }
};
```

```go
func maxHammingDistances(nums []int, m int) []int {
	dist := make([]int, 1<<m)
	for i := range dist {
		dist[i] = -1
	}
	q := []int{}
	for _, x := range nums {
		dist[x] = 0
		q = append(q, x)
	}
	for k := 1; len(q) > 0; k++ {
		t := []int{}
		for _, x := range q {
			for i := 0; i < m; i++ {
				y := x ^ (1 << i)
				if dist[y] == -1 {
					dist[y] = k
					t = append(t, y)
				}
			}
		}
		q = t
	}
	for i, x := range nums {
		nums[i] = m - dist[x^(1<<m-1)]
	}
	return nums
}
```

```ts
function maxHammingDistances(nums: number[], m: number): number[] {
    const dist: number[] = Array.from({ length: 1 << m }, () => -1);
    const q: number[] = [];
    for (const x of nums) {
        dist[x] = 0;
        q.push(x);
    }
    for (let k = 1; q.length; ++k) {
        const t: number[] = [];
        for (const x of q) {
            for (let i = 0; i < m; ++i) {
                const y = x ^ (1 << i);
                if (dist[y] === -1) {
                    dist[y] = k;
                    t.push(y);
                }
            }
        }
        q.splice(0, q.length, ...t);
    }
    for (let i = 0; i < nums.length; ++i) {
        nums[i] = m - dist[nums[i] ^ ((1 << m) - 1)];
    }
    return nums;
}
```

<!-- tabs:end -->

<!-- end -->
