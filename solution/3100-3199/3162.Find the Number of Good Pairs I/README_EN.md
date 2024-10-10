---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3162.Find%20the%20Number%20of%20Good%20Pairs%20I/README_EN.md
rating: 1168
source: Weekly Contest 399 Q1
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3162. Find the Number of Good Pairs I](https://leetcode.com/problems/find-the-number-of-good-pairs-i)

[中文文档](/solution/3100-3199/3162.Find%20the%20Number%20of%20Good%20Pairs%20I/README.md)

## Description

<!-- description:start -->

<p>You are given 2 integer arrays <code>nums1</code> and <code>nums2</code> of lengths <code>n</code> and <code>m</code> respectively. You are also given a <strong>positive</strong> integer <code>k</code>.</p>

<p>A pair <code>(i, j)</code> is called <strong>good</strong> if <code>nums1[i]</code> is divisible by <code>nums2[j] * k</code> (<code>0 &lt;= i &lt;= n - 1</code>, <code>0 &lt;= j &lt;= m - 1</code>).</p>

<p>Return the total number of <strong>good</strong> pairs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,3,4], nums2 = [1,3,4], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>
The 5 good pairs are <code>(0, 0)</code>, <code>(1, 0)</code>, <code>(1, 1)</code>, <code>(2, 0)</code>, and <code>(2, 2)</code>.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,2,4,12], nums2 = [2,4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The 2 good pairs are <code>(3, 0)</code> and <code>(3, 1)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 50</code></li>
	<li><code>1 &lt;= nums1[i], nums2[j] &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brute Force Enumeration

We directly enumerate all digit pairs $(x, y)$ and check whether $x \bmod (y \times k) = 0$. If it satisfies the condition, increment the answer by one.

After the enumeration is complete, return the answer.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the lengths of arrays $\textit{nums1}$ and $\textit{nums2}$, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPairs(self, nums1: List[int], nums2: List[int], k: int) -> int:
        return sum(x % (y * k) == 0 for x in nums1 for y in nums2)
```

#### Java

```java
class Solution {
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int ans = 0;
        for (int x : nums1) {
            for (int y : nums2) {
                if (x % (y * k) == 0) {
                    ++ans;
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
    int numberOfPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        int ans = 0;
        for (int x : nums1) {
            for (int y : nums2) {
                if (x % (y * k) == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfPairs(nums1 []int, nums2 []int, k int) (ans int) {
	for _, x := range nums1 {
		for _, y := range nums2 {
			if x%(y*k) == 0 {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfPairs(nums1: number[], nums2: number[], k: number): number {
    let ans = 0;
    for (const x of nums1) {
        for (const y of nums2) {
            if (x % (y * k) === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Hash Table + Enumerate Multiples

We use a hash table `cnt1` to record the occurrence times of each number divided by $k$ in array `nums1`, and a hash table `cnt2` to record the occurrence times of each number in array `nums2`.

Next, we enumerate each number $x$ in array `nums2`. For each number $x$, we enumerate its multiples $y$, where the range of $y$ is $[x, \textit{mx}]$, where `mx` is the maximum key value in `cnt1`. Then we count the sum of `cnt1[y]`, denoted as $s$. Finally, we add $s \times v$ to the answer, where $v$ is `cnt2[x]`.

The time complexity is $O(n + m + (M / k) \times \log m)$, and the space complexity is $O(n + m)$. Where $n$ and $m$ are the lengths of arrays `nums1` and `nums2` respectively, and $M$ is the maximum value in array `nums1`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPairs(self, nums1: List[int], nums2: List[int], k: int) -> int:
        cnt1 = Counter(x // k for x in nums1 if x % k == 0)
        if not cnt1:
            return 0
        cnt2 = Counter(nums2)
        ans = 0
        mx = max(cnt1)
        for x, v in cnt2.items():
            s = sum(cnt1[y] for y in range(x, mx + 1, x))
            ans += s * v
        return ans
```

#### Java

```java
class Solution {
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> cnt1 = new HashMap<>();
        for (int x : nums1) {
            if (x % k == 0) {
                cnt1.merge(x / k, 1, Integer::sum);
            }
        }
        if (cnt1.isEmpty()) {
            return 0;
        }
        Map<Integer, Integer> cnt2 = new HashMap<>();
        for (int x : nums2) {
            cnt2.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        int mx = Collections.max(cnt1.keySet());
        for (var e : cnt2.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            int s = 0;
            for (int y = x; y <= mx; y += x) {
                s += cnt1.getOrDefault(y, 0);
            }
            ans += s * v;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        unordered_map<int, int> cnt1;
        for (int x : nums1) {
            if (x % k == 0) {
                cnt1[x / k]++;
            }
        }
        if (cnt1.empty()) {
            return 0;
        }
        unordered_map<int, int> cnt2;
        for (int x : nums2) {
            ++cnt2[x];
        }
        int mx = 0;
        for (auto& [x, _] : cnt1) {
            mx = max(mx, x);
        }
        int ans = 0;
        for (auto& [x, v] : cnt2) {
            int s = 0;
            for (int y = x; y <= mx; y += x) {
                s += cnt1[y];
            }
            ans += s * v;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfPairs(nums1 []int, nums2 []int, k int) (ans int) {
	cnt1 := map[int]int{}
	for _, x := range nums1 {
		if x%k == 0 {
			cnt1[x/k]++
		}
	}
	if len(cnt1) == 0 {
		return 0
	}
	cnt2 := map[int]int{}
	for _, x := range nums2 {
		cnt2[x]++
	}
	mx := 0
	for x := range cnt1 {
		mx = max(mx, x)
	}
	for x, v := range cnt2 {
		s := 0
		for y := x; y <= mx; y += x {
			s += cnt1[y]
		}
		ans += s * v
	}
	return
}
```

#### TypeScript

```ts
function numberOfPairs(nums1: number[], nums2: number[], k: number): number {
    const cnt1: Map<number, number> = new Map();
    for (const x of nums1) {
        if (x % k === 0) {
            cnt1.set((x / k) | 0, (cnt1.get((x / k) | 0) || 0) + 1);
        }
    }
    if (cnt1.size === 0) {
        return 0;
    }
    const cnt2: Map<number, number> = new Map();
    for (const x of nums2) {
        cnt2.set(x, (cnt2.get(x) || 0) + 1);
    }
    const mx = Math.max(...cnt1.keys());
    let ans = 0;
    for (const [x, v] of cnt2) {
        let s = 0;
        for (let y = x; y <= mx; y += x) {
            s += cnt1.get(y) || 0;
        }
        ans += s * v;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
