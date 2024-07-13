---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0215.Kth%20Largest%20Element%20in%20an%20Array/README_EN.md
tags:
    - Array
    - Divide and Conquer
    - Quickselect
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array)

[中文文档](/solution/0200-0299/0215.Kth%20Largest%20Element%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the</em> <code>k<sup>th</sup></code> <em>largest element in the array</em>.</p>

<p>Note that it is the <code>k<sup>th</sup></code> largest element in the sorted order, not the <code>k<sup>th</sup></code> distinct element.</p>

<p>Can you solve it without sorting?</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,1,5,6,4], k = 2
<strong>Output:</strong> 5
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3,1,2,4,5,5,6], k = 4
<strong>Output:</strong> 4
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Quick Select

Quick Select is an algorithm for finding the $k^{th}$ largest or smallest element in an unsorted array. Its basic idea is to select a pivot element each time, dividing the array into two parts: one part contains elements smaller than the pivot, and the other part contains elements larger than the pivot. Then, based on the position of the pivot, it decides whether to continue the search on the left or right side until the $k^{th}$ largest element is found.

The time complexity is $O(n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $\text{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        def quick_sort(l: int, r: int) -> int:
            if l == r:
                return nums[l]
            i, j = l - 1, r + 1
            x = nums[(l + r) >> 1]
            while i < j:
                while 1:
                    i += 1
                    if nums[i] >= x:
                        break
                while 1:
                    j -= 1
                    if nums[j] <= x:
                        break
                if i < j:
                    nums[i], nums[j] = nums[j], nums[i]
            if j < k:
                return quick_sort(j + 1, r)
            return quick_sort(l, j)

        n = len(nums)
        k = n - k
        return quick_sort(0, n - 1)
```

#### Java

```java
class Solution {
    private int[] nums;
    private int k;

    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        this.k = nums.length - k;
        return quickSort(0, nums.length - 1);
    }

    private int quickSort(int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int i = l - 1, j = r + 1;
        int x = nums[(l + r) >>> 1];
        while (i < j) {
            while (nums[++i] < x) {
            }
            while (nums[--j] > x) {
            }
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        if (j < k) {
            return quickSort(j + 1, r);
        }
        return quickSort(l, j);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        int n = nums.size();
        k = n - k;
        auto quickSort = [&](auto&& quickSort, int l, int r) -> int {
            if (l == r) {
                return nums[l];
            }
            int i = l - 1, j = r + 1;
            int x = nums[(l + r) >> 1];
            while (i < j) {
                while (nums[++i] < x) {
                }
                while (nums[--j] > x) {
                }
                if (i < j) {
                    swap(nums[i], nums[j]);
                }
            }
            if (j < k) {
                return quickSort(quickSort, j + 1, r);
            }
            return quickSort(quickSort, l, j);
        };
        return quickSort(quickSort, 0, n - 1);
    }
};
```

#### Go

```go
func findKthLargest(nums []int, k int) int {
	k = len(nums) - k
	var quickSort func(l, r int) int
	quickSort = func(l, r int) int {
		if l == r {
			return nums[l]
		}
		i, j := l-1, r+1
		x := nums[(l+r)>>1]
		for i < j {
			for {
				i++
				if nums[i] >= x {
					break
				}
			}
			for {
				j--
				if nums[j] <= x {
					break
				}
			}
			if i < j {
				nums[i], nums[j] = nums[j], nums[i]
			}
		}
		if j < k {
			return quickSort(j+1, r)
		}
		return quickSort(l, j)
	}
	return quickSort(0, len(nums)-1)
}
```

#### TypeScript

```ts
function findKthLargest(nums: number[], k: number): number {
    const n = nums.length;
    k = n - k;
    const quickSort = (l: number, r: number): number => {
        if (l === r) {
            return nums[l];
        }
        let [i, j] = [l - 1, r + 1];
        const x = nums[(l + r) >> 1];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) {
                [nums[i], nums[j]] = [nums[j], nums[i]];
            }
        }
        if (j < k) {
            return quickSort(j + 1, r);
        }
        return quickSort(l, j);
    };
    return quickSort(0, n - 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn find_kth_largest(mut nums: Vec<i32>, k: i32) -> i32 {
        let len = nums.len();
        let k = len - k as usize;
        Self::quick_sort(&mut nums, 0, len - 1, k)
    }

    fn quick_sort(nums: &mut Vec<i32>, l: usize, r: usize, k: usize) -> i32 {
        if l == r {
            return nums[l];
        }

        let (mut i, mut j) = (l as isize - 1, r as isize + 1);
        let x = nums[(l + r) / 2];

        while i < j {
            i += 1;
            while nums[i as usize] < x {
                i += 1;
            }

            j -= 1;
            while nums[j as usize] > x {
                j -= 1;
            }

            if i < j {
                nums.swap(i as usize, j as usize);
            }
        }

        let j = j as usize;
        if j < k {
            Self::quick_sort(nums, j + 1, r, k)
        } else {
            Self::quick_sort(nums, l, j, k)
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Priority Queue (Min Heap)

We can maintain a min heap $\text{minQ}$ of size $k$, and then iterate through the array $\text{nums}$, adding each element to the min heap. When the size of the min heap exceeds $k$, we pop the top element of the heap. This way, the final $k$ elements in the min heap are the $k$ largest elements in the array, and the top element of the heap is the $k^{th}$ largest element.

The time complexity is $O(n\log k)$, and the space complexity is $O(k)$. Here, $n$ is the length of the array $\text{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        return nlargest(k, nums)[-1]
```

#### Java

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        for (int x : nums) {
            minQ.offer(x);
            if (minQ.size() > k) {
                minQ.poll();
            }
        }
        return minQ.peek();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        priority_queue<int, vector<int>, greater<int>> minQ;
        for (int x : nums) {
            minQ.push(x);
            if (minQ.size() > k) {
                minQ.pop();
            }
        }
        return minQ.top();
    }
};
```

#### Go

```go
func findKthLargest(nums []int, k int) int {
	minQ := hp{}
	for _, x := range nums {
		heap.Push(&minQ, x)
		if minQ.Len() > k {
			heap.Pop(&minQ)
		}
	}
	return minQ.IntSlice[0]
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

#### TypeScript

```ts
function findKthLargest(nums: number[], k: number): number {
    const minQ = new MinPriorityQueue();
    for (const x of nums) {
        minQ.enqueue(x);
        if (minQ.size() > k) {
            minQ.dequeue();
        }
    }
    return minQ.front().element;
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn find_kth_largest(nums: Vec<i32>, k: i32) -> i32 {
        let mut minQ = BinaryHeap::new();
        for &x in nums.iter() {
            minQ.push(-x);
            if minQ.len() > k as usize {
                minQ.pop();
            }
        }
        -minQ.peek().unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Counting Sort

We can use the idea of counting sort, counting the occurrence of each element in the array $\text{nums}$ and recording it in a hash table $\text{cnt}$. Then, we iterate over the elements $i$ from largest to smallest, subtracting the occurrence count $\text{cnt}[i]$ each time, until $k$ is less than or equal to $0$. At this point, the element $i$ is the $k^{th}$ largest element in the array.

The time complexity is $O(n + m)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\text{nums}$, and $m$ is the maximum value among the elements in $\text{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums)
        for i in count(max(cnt), -1):
            k -= cnt[i]
            if k <= 0:
                return i
```

#### Java

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>(nums.length);
        int m = Integer.MIN_VALUE;
        for (int x : nums) {
            m = Math.max(m, x);
            cnt.merge(x, 1, Integer::sum);
        }
        for (int i = m;; --i) {
            k -= cnt.getOrDefault(i, 0);
            if (k <= 0) {
                return i;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int m = INT_MIN;
        for (int x : nums) {
            ++cnt[x];
            m = max(m, x);
        }
        for (int i = m;; --i) {
            k -= cnt[i];
            if (k <= 0) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func findKthLargest(nums []int, k int) int {
	cnt := map[int]int{}
	m := -(1 << 30)
	for _, x := range nums {
		cnt[x]++
		m = max(m, x)
	}
	for i := m; ; i-- {
		k -= cnt[i]
		if k <= 0 {
			return i
		}
	}

}
```

#### TypeScript

```ts
function findKthLargest(nums: number[], k: number): number {
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const m = Math.max(...nums);
    for (let i = m; ; --i) {
        k -= cnt[i] || 0;
        if (k <= 0) {
            return i;
        }
    }
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_kth_largest(nums: Vec<i32>, k: i32) -> i32 {
        let mut cnt = HashMap::new();
        let mut m = i32::MIN;

        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
            if x > m {
                m = x;
            }
        }

        let mut k = k;
        for i in (i32::MIN..=m).rev() {
            if let Some(&count) = cnt.get(&i) {
                k -= count;
                if k <= 0 {
                    return i;
                }
            }
        }

        unreachable!();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
