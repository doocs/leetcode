---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3690.Split%20and%20Merge%20Array%20Transformation/README_EN.md
---

<!-- problem:start -->

# [3690. Split and Merge Array Transformation](https://leetcode.com/problems/split-and-merge-array-transformation)

[中文文档](/solution/3600-3699/3690.Split%20and%20Merge%20Array%20Transformation/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code>, each of length <code>n</code>. You may perform the following <strong>split-and-merge operation</strong> on <code>nums1</code> any number of times:</p>

<ol>
	<li>Choose a subarray <code>nums1[L..R]</code>.</li>
	<li>Remove that subarray, leaving the prefix <code>nums1[0..L-1]</code> (empty if <code>L = 0</code>) and the suffix <code>nums1[R+1..n-1]</code> (empty if <code>R = n - 1</code>).</li>
	<li>Re-insert the removed subarray (in its original order) at <strong>any</strong> position in the remaining array (i.e., between any two elements, at the very start, or at the very end).</li>
</ol>

<p>Return the <strong>minimum</strong> number of <strong>split-and-merge operations</strong> needed to transform <code>nums1</code> into <code>nums2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [3,1,2], nums2 = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Split out the subarray <code>[3]</code> (<code>L = 0</code>, <code>R = 0</code>); the remaining array is <code>[1,2]</code>.</li>
	<li>Insert <code>[3]</code> at the end; the array becomes <code>[1,2,3]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = </span>[1,1,2,3,4,5]<span class="example-io">, nums2 = </span>[5,4,3,2,1,1]</p>

<p><strong>Output: </strong>3</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>[1,1,2]</code> at indices <code>0 - 2</code>; remaining is <code>[3,4,5]</code>; insert <code>[1,1,2]</code> at position <code>2</code>, resulting in <code>[3,4,1,1,2,5]</code>.</li>
	<li>Remove <code>[4,1,1]</code> at indices <code>1 - 3</code>; remaining is <code>[3,2,5]</code>; insert <code>[4,1,1]</code> at position <code>3</code>, resulting in <code>[3,2,5,4,1,1]</code>.</li>
	<li>Remove <code>[3,2]</code> at indices <code>0 - 1</code>; remaining is <code>[5,4,1,1]</code>; insert <code>[3,2]</code> at position <code>2</code>, resulting in <code>[5,4,3,2,1,1]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums1.length == nums2.length &lt;= 6</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2</code> is a <strong>permutation</strong> of <code>nums1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

We can use Breadth-First Search (BFS) to solve this problem. Since the array length is at most 6, we can enumerate all possible split and merge operations to find the minimum number of operations.

First, we define a queue $\textit{q}$ to store the current array states, and use a set $\textit{vis}$ to record the visited array states to avoid duplicate computations. Initially, the queue contains only the array $\textit{nums1}$.

Then, we perform the following steps:

1. Remove the current array state $\textit{cur}$ from the queue.
2. If $\textit{cur}$ equals the target array $\textit{nums2}$, return the current number of operations.
3. Otherwise, enumerate all possible split positions $(l, r)$, remove the subarray $\textit{cur}[l..r]$ to obtain the remaining array $\textit{remain}$ and the subarray $\textit{sub}$.
4. Insert the subarray $\textit{sub}$ into all possible positions of the remaining array $\textit{remain}$ to generate new array states $\textit{nxt}$.
5. If a new array state $\textit{nxt}$ has not been visited, add it to the queue and the visited set.
6. Repeat the above steps until the target array is found or the queue is empty.

The time complexity is $O(n! \times n^4)$, and the space complexity is $O(n! \times n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSplitMerge(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        target = tuple(nums2)
        start = tuple(nums1)

        q = [start]
        vis = set()
        vis.add(start)

        for ans in count(0):
            t = q
            q = []
            for cur in t:
                if cur == target:
                    return ans
                for l in range(n):
                    for r in range(l, n):
                        remain = list(cur[:l]) + list(cur[r + 1 :])
                        sub = cur[l : r + 1]
                        for i in range(len(remain) + 1):
                            nxt = tuple(remain[:i] + list(sub) + remain[i:])
                            if nxt not in vis:
                                vis.add(nxt)
                                q.append(nxt)
```

#### Java

```java
class Solution {
    public int minSplitMerge(int[] nums1, int[] nums2) {
        int n = nums1.length;
        List<Integer> target = toList(nums2);
        List<Integer> start = toList(nums1);
        List<List<Integer>> q = List.of(start);
        Set<List<Integer>> vis = new HashSet<>();
        vis.add(start);
        for (int ans = 0;; ++ans) {
            var t = q;
            q = new ArrayList<>();
            for (var cur : t) {
                if (cur.equals(target)) {
                    return ans;
                }
                for (int l = 0; l < n; ++l) {
                    for (int r = l; r < n; ++r) {
                        List<Integer> remain = new ArrayList<>();
                        for (int i = 0; i < l; ++i) {
                            remain.add(cur.get(i));
                        }
                        for (int i = r + 1; i < n; ++i) {
                            remain.add(cur.get(i));
                        }
                        List<Integer> sub = cur.subList(l, r + 1);
                        for (int i = 0; i <= remain.size(); ++i) {
                            List<Integer> nxt = new ArrayList<>();
                            for (int j = 0; j < i; ++j) {
                                nxt.add(remain.get(j));
                            }
                            for (int x : sub) {
                                nxt.add(x);
                            }
                            for (int j = i; j < remain.size(); ++j) {
                                nxt.add(remain.get(j));
                            }
                            if (vis.add(nxt)) {
                                q.add(nxt);
                            }
                        }
                    }
                }
            }
        }
    }

    private List<Integer> toList(int[] arr) {
        List<Integer> res = new ArrayList<>(arr.length);
        for (int x : arr) {
            res.add(x);
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSplitMerge(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> target = nums2;
        vector<vector<int>> q{nums1};
        set<vector<int>> vis;
        vis.insert(nums1);

        for (int ans = 0;; ++ans) {
            vector<vector<int>> t = q;
            q.clear();
            for (auto& cur : t) {
                if (cur == target) {
                    return ans;
                }
                for (int l = 0; l < n; ++l) {
                    for (int r = l; r < n; ++r) {
                        vector<int> remain;
                        remain.insert(remain.end(), cur.begin(), cur.begin() + l);
                        remain.insert(remain.end(), cur.begin() + r + 1, cur.end());
                        vector<int> sub(cur.begin() + l, cur.begin() + r + 1);
                        for (int i = 0; i <= (int) remain.size(); ++i) {
                            vector<int> nxt;
                            nxt.insert(nxt.end(), remain.begin(), remain.begin() + i);
                            nxt.insert(nxt.end(), sub.begin(), sub.end());
                            nxt.insert(nxt.end(), remain.begin() + i, remain.end());

                            if (!vis.count(nxt)) {
                                vis.insert(nxt);
                                q.push_back(nxt);
                            }
                        }
                    }
                }
            }
        }
    }
};
```

#### Go

```go
func minSplitMerge(nums1 []int, nums2 []int) int {
	n := len(nums1)

	toArr := func(nums []int) [6]int {
		var t [6]int
		for i, x := range nums {
			t[i] = x
		}
		return t
	}

	start := toArr(nums1)
	target := toArr(nums2)

	vis := map[[6]int]bool{start: true}
	q := [][6]int{start}

	for ans := 0; ; ans++ {
		nq := [][6]int{}
		for _, cur := range q {
			if cur == target {
				return ans
			}
			for l := 0; l < n; l++ {
				for r := l; r < n; r++ {
					remain := []int{}
					for i := 0; i < l; i++ {
						remain = append(remain, cur[i])
					}
					for i := r + 1; i < n; i++ {
						remain = append(remain, cur[i])
					}

					sub := []int{}
					for i := l; i <= r; i++ {
						sub = append(sub, cur[i])
					}

					for pos := 0; pos <= len(remain); pos++ {
						nxtSlice := []int{}
						nxtSlice = append(nxtSlice, remain[:pos]...)
						nxtSlice = append(nxtSlice, sub...)
						nxtSlice = append(nxtSlice, remain[pos:]...)

						nxt := toArr(nxtSlice)
						if !vis[nxt] {
							vis[nxt] = true
							nq = append(nq, nxt)
						}
					}
				}
			}
		}
		q = nq
	}
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
