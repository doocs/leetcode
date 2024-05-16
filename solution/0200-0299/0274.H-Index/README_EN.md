---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0274.H-Index/README_EN.md
tags:
    - Array
    - Counting Sort
    - Sorting
---

<!-- problem:start -->

# [274. H-Index](https://leetcode.com/problems/h-index)

[中文文档](/solution/0200-0299/0274.H-Index/README.md)

## Description

<p>Given an array of integers <code>citations</code> where <code>citations[i]</code> is the number of citations a researcher received for their <code>i<sup>th</sup></code> paper, return <em>the researcher&#39;s h-index</em>.</p>

<p>According to the <a href="https://en.wikipedia.org/wiki/H-index" target="_blank">definition of h-index on Wikipedia</a>: The h-index is defined as the maximum value of <code>h</code> such that the given researcher has published at least <code>h</code> papers that have each been cited at least <code>h</code> times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> citations = [3,0,6,1,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> citations = [1,3,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == citations.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>0 &lt;= citations[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We can sort the array `citations` in descending order. Then we enumerate the value $h$ from large to small, if there is an $h$ value satisfying $citations[h-1] \geq h$, it means that there are at least $h$ papers that have been cited at least $h$ times, just return $h$ directly. If we cannot find such an $h$ value, it means that all the papers have not been cited, return $0$.

Time complexity $O(n \times \log n)$, space complexity $O(\log n)$. Here $n$ is the length of the array `citations`.

<!-- tabs:start -->

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        citations.sort(reverse=True)
        for h in range(len(citations), 0, -1):
            if citations[h - 1] >= h:
                return h
        return 0
```

```java
class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int h = n; h > 0; --h) {
            if (citations[n - h] >= h) {
                return h;
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    int hIndex(vector<int>& citations) {
        sort(citations.rbegin(), citations.rend());
        for (int h = citations.size(); h; --h) {
            if (citations[h - 1] >= h) {
                return h;
            }
        }
        return 0;
    }
};
```

```go
func hIndex(citations []int) int {
	sort.Ints(citations)
	n := len(citations)
	for h := n; h > 0; h-- {
		if citations[n-h] >= h {
			return h
		}
	}
	return 0
}
```

```ts
function hIndex(citations: number[]): number {
    citations.sort((a, b) => b - a);
    for (let h = citations.length; h; --h) {
        if (citations[h - 1] >= h) {
            return h;
        }
    }
    return 0;
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn h_index(citations: Vec<i32>) -> i32 {
        let mut citations = citations;
        citations.sort_by(|&lhs, &rhs| { rhs.cmp(&lhs) });

        let n = citations.len();

        for i in (1..=n).rev() {
            if citations[i - 1] >= (i as i32) {
                return i as i32;
            }
        }

        0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Counting + Sum

We can use an array $cnt$ of length $n+1$, where $cnt[i]$ represents the number of papers with the reference count of $i$. We traverse the array `citations` and treat the papers with the reference count greater than $n$ as papers with a reference count of $n$. Then we use the reference count as the index and add $1$ to the corresponding element of $cnt$ for each paper. In this way, we have counted the number of papers for each reference count.

Then we enumerate the value $h$ from large to small, and add the element value of $cnt$ with the index of $h$ to the variable $s$, where $s$ represents the number of papers with a reference count greater than or equal to $h$. If $s \geq h$, it means that at least $h$ papers have been cited at least $h$ times, just return $h$ directly.

Time complexity $O(n)$, space complexity $O(n)$. Here $n$ is the length of the array `citations`.

<!-- tabs:start -->

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        cnt = [0] * (n + 1)
        for x in citations:
            cnt[min(x, n)] += 1
        s = 0
        for h in range(n, -1, -1):
            s += cnt[h]
            if s >= h:
                return h
```

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] cnt = new int[n + 1];
        for (int x : citations) {
            ++cnt[Math.min(x, n)];
        }
        for (int h = n, s = 0;; --h) {
            s += cnt[h];
            if (s >= h) {
                return h;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    int hIndex(vector<int>& citations) {
        int n = citations.size();
        int cnt[n + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int x : citations) {
            ++cnt[min(x, n)];
        }
        for (int h = n, s = 0;; --h) {
            s += cnt[h];
            if (s >= h) {
                return h;
            }
        }
    }
};
```

```go
func hIndex(citations []int) int {
	n := len(citations)
	cnt := make([]int, n+1)
	for _, x := range citations {
		cnt[min(x, n)]++
	}
	for h, s := n, 0; ; h-- {
		s += cnt[h]
		if s >= h {
			return h
		}
	}
}
```

```ts
function hIndex(citations: number[]): number {
    const n: number = citations.length;
    const cnt: number[] = new Array(n + 1).fill(0);
    for (const x of citations) {
        ++cnt[Math.min(x, n)];
    }
    for (let h = n, s = 0; ; --h) {
        s += cnt[h];
        if (s >= h) {
            return h;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Binary Search

We notice that if there is a $h$ value that satisfies at least $h$ papers are cited at least $h$ times, then for any $h'<h$, at least $h'$ papers are cited at least $h'$ times. Therefore, we can use the binary search method to find the largest $h$ such that at least $h$ papers are cited at least $h$ times.

We define the left boundary of binary search $l=0$ and the right boundary $r=n$. Each time we take $mid = \lfloor \frac{l + r + 1}{2} \rfloor$, where $\lfloor x \rfloor$ represents floor $x$. Then we count the number of elements in array `citations` that are greater than or equal to $mid$, and denote it as $s$. If $s \geq mid$, it means that at least $mid$ papers are cited at least $mid$ times. In this case, we change the left boundary $l$ to $mid$. Otherwise, we change the right boundary $r$ to $mid-1$. When the left boundary $l$ is equal to the right boundary $r$, we find the largest $h$ value, which is $l$ or $r$.

Time complexity $O(n \times \log n)$, where $n$ is the length of array `citations`. Space complexity $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        l, r = 0, len(citations)
        while l < r:
            mid = (l + r + 1) >> 1
            if sum(x >= mid for x in citations) >= mid:
                l = mid
            else:
                r = mid - 1
        return l
```

```java
class Solution {
    public int hIndex(int[] citations) {
        int l = 0, r = citations.length;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            int s = 0;
            for (int x : citations) {
                if (x >= mid) {
                    ++s;
                }
            }
            if (s >= mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int hIndex(vector<int>& citations) {
        int l = 0, r = citations.size();
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            int s = 0;
            for (int x : citations) {
                if (x >= mid) {
                    ++s;
                }
            }
            if (s >= mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

```go
func hIndex(citations []int) int {
	l, r := 0, len(citations)
	for l < r {
		mid := (l + r + 1) >> 1
		s := 0
		for _, x := range citations {
			if x >= mid {
				s++
			}
		}
		if s >= mid {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

```ts
function hIndex(citations: number[]): number {
    let l = 0;
    let r = citations.length;
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        let s = 0;
        for (const x of citations) {
            if (x >= mid) {
                ++s;
            }
        }
        if (s >= mid) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
