---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2200.Find%20All%20K-Distant%20Indices%20in%20an%20Array/README.md
rating: 1266
source: 第 284 场周赛 Q1
tags:
    - 数组
    - 双指针
---

<!-- problem:start -->

# [2200. 找出数组中的所有 K 近邻下标](https://leetcode.cn/problems/find-all-k-distant-indices-in-an-array)

[English Version](/solution/2200-2299/2200.Find%20All%20K-Distant%20Indices%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 和两个整数 <code>key</code> 和 <code>k</code> 。<strong>K 近邻下标</strong> 是 <code>nums</code> 中的一个下标 <code>i</code> ，并满足至少存在一个下标 <code>j</code> 使得 <code>|i - j| &lt;= k</code> 且 <code>nums[j] == key</code> 。</p>

<p>以列表形式返回按 <strong>递增顺序</strong> 排序的所有 K 近邻下标。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,9,1,3,9,5], key = 9, k = 1
<strong>输出：</strong>[1,2,3,4,5,6]
<strong>解释：</strong>因此，<code>nums[2] == key</code> 且 <code>nums[5] == key</code>。
- 对下标 0 ，<code>|0 - 2| &gt; k</code> 且 <code>|0 - 5| &gt; k</code>，所以不存在 <code>j</code> 使得 <code>|0 - j| &lt;= k</code> 且 <code>nums[j] == key</code>。所以 0 不是一个 K 近邻下标。
- 对下标 1 ，<code>|1 - 2| &lt;= k</code> 且 <code>nums[2] == key</code>，所以 1 是一个 K 近邻下标。
- 对下标 2 ，<code>|2 - 2| &lt;= k</code> 且 <code>nums[2] == key</code>，所以 2 是一个 K 近邻下标。
- 对下标 3 ，<code>|3 - 2| &lt;= k</code> 且 <code>nums[2] == key</code>，所以 3 是一个 K 近邻下标。
- 对下标 4 ，<code>|4 - 5| &lt;= k</code> 且 <code>nums[5] == key</code>，所以 4 是一个 K 近邻下标。
- 对下标 5 ，<code>|5 - 5| &lt;= k</code> 且 <code>nums[5] == key</code>，所以 5 是一个 K 近邻下标。
- 对下标 6 ，<code>|6 - 5| &lt;= k</code> 且 <code>nums[5] == key</code>，所以 6 是一个 K 近邻下标。
因此，按递增顺序返回 [1,2,3,4,5,6] 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2,2,2], key = 2, k = 2
<strong>输出：</strong>[0,1,2,3,4]
<strong>解释：</strong>对 <code>nums</code> 的所有下标 i ，总存在某个下标 j 使得 <code>|i - j| &lt;= k</code> 且 <code>nums[j] == key</code>，所以每个下标都是一个 K 近邻下标。 
因此，返回 [0,1,2,3,4] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>key</code> 是数组 <code>nums</code> 中的一个整数</li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们在 $[0, n)$ 的范围内枚举下标 $i$，对于每个下标 $i$，我们在 $[0, n)$ 的范围内枚举下标 $j$，如果 $|i - j| \leq k$ 且 $nums[j] = key$，那么 $i$ 就是一个 K 近邻下标，我们将 $i$ 加入答案数组中，然后跳出内层循环，枚举下一个下标 $i$。

时间复杂度 $O(n^2)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKDistantIndices(self, nums: List[int], key: int, k: int) -> List[int]:
        ans = []
        n = len(nums)
        for i in range(n):
            if any(abs(i - j) <= k and nums[j] == key for j in range(n)):
                ans.append(i)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (Math.abs(i - j) <= k && nums[j] == key) {
                    ans.add(i);
                    break;
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
    vector<int> findKDistantIndices(vector<int>& nums, int key, int k) {
        int n = nums.size();
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (abs(i - j) <= k && nums[j] == key) {
                    ans.push_back(i);
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findKDistantIndices(nums []int, key int, k int) (ans []int) {
	for i := range nums {
		for j, x := range nums {
			if abs(i-j) <= k && x == key {
				ans = append(ans, i)
				break
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function findKDistantIndices(nums: number[], key: number, k: number): number[] {
    const n = nums.length;
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (Math.abs(i - j) <= k && nums[j] === key) {
                ans.push(i);
                break;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_k_distant_indices(nums: Vec<i32>, key: i32, k: i32) -> Vec<i32> {
        let n = nums.len();
        let mut ans = Vec::new();
        for i in 0..n {
            for j in 0..n {
                if (i as i32 - j as i32).abs() <= k && nums[j] == key {
                    ans.push(i as i32);
                    break;
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：预处理 + 二分查找

我们可以预处理得到所有等于 $key$ 的元素的下标，记录在数组 $idx$ 中。数组 $idx$ 中的所有下标元素是按照升序排列的，

接下来，我们枚举下标 $i$，对于每个下标 $i$，我们可以使用二分查找的方法在数组 $idx$ 中查找 $[i - k, i + k]$ 范围内的元素，如果存在元素，那么 $i$ 就是一个 K 近邻下标，我们将 $i$ 加入答案数组中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKDistantIndices(self, nums: List[int], key: int, k: int) -> List[int]:
        idx = [i for i, x in enumerate(nums) if x == key]
        ans = []
        for i in range(len(nums)):
            l = bisect_left(idx, i - k)
            r = bisect_right(idx, i + k) - 1
            if l <= r:
                ans.append(i)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                idx.add(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int l = Collections.binarySearch(idx, i - k);
            int r = Collections.binarySearch(idx, i + k + 1);
            l = l < 0 ? -l - 1 : l;
            r = r < 0 ? -r - 2 : r - 1;
            if (l <= r) {
                ans.add(i);
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
    vector<int> findKDistantIndices(vector<int>& nums, int key, int k) {
        vector<int> idx;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (nums[i] == key) {
                idx.push_back(i);
            }
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            auto it1 = lower_bound(idx.begin(), idx.end(), i - k);
            auto it2 = upper_bound(idx.begin(), idx.end(), i + k) - 1;
            if (it1 <= it2) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findKDistantIndices(nums []int, key int, k int) (ans []int) {
	idx := []int{}
	for i, x := range nums {
		if x == key {
			idx = append(idx, i)
		}
	}
	for i := range nums {
		l := sort.SearchInts(idx, i-k)
		r := sort.SearchInts(idx, i+k+1) - 1
		if l <= r {
			ans = append(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function findKDistantIndices(nums: number[], key: number, k: number): number[] {
    const n = nums.length;
    const idx: number[] = [];
    for (let i = 0; i < n; i++) {
        if (nums[i] === key) {
            idx.push(i);
        }
    }
    const search = (x: number): number => {
        let [l, r] = [0, idx.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (idx[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        const l = search(i - k);
        const r = search(i + k + 1) - 1;
        if (l <= r) {
            ans.push(i);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_k_distant_indices(nums: Vec<i32>, key: i32, k: i32) -> Vec<i32> {
        let n = nums.len();
        let mut idx = Vec::new();
        for i in 0..n {
            if nums[i] == key {
                idx.push(i as i32);
            }
        }

        let search = |x: i32| -> usize {
            let (mut l, mut r) = (0, idx.len());
            while l < r {
                let mid = (l + r) >> 1;
                if idx[mid] >= x {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            l
        };

        let mut ans = Vec::new();
        for i in 0..n {
            let l = search(i as i32 - k);
            let r = search(i as i32 + k + 1) as i32 - 1;
            if l as i32 <= r {
                ans.push(i as i32);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：双指针

我们枚举下标 $i$，用一个指针 $j$ 指向满足 $j \geq i - k$ 且 $nums[j] = key$ 的最小下标，如果 $j$ 存在且 $j \leq i + k$，那么 $i$ 就是一个 K 近邻下标，我们将 $i$ 加入答案数组中。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKDistantIndices(self, nums: List[int], key: int, k: int) -> List[int]:
        ans = []
        j, n = 0, len(nums)
        for i in range(n):
            while j < i - k or (j < n and nums[j] != key):
                j += 1
            if j < n and j <= (i + k):
                ans.append(i)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < n; ++i) {
            while (j < i - k || (j < n && nums[j] != key)) {
                ++j;
            }
            if (j < n && j <= i + k) {
                ans.add(i);
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
    vector<int> findKDistantIndices(vector<int>& nums, int key, int k) {
        int n = nums.size();
        vector<int> ans;
        for (int i = 0, j = 0; i < n; ++i) {
            while (j < i - k || (j < n && nums[j] != key)) {
                ++j;
            }
            if (j < n && j <= i + k) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findKDistantIndices(nums []int, key int, k int) (ans []int) {
	n := len(nums)
	for i, j := 0, 0; i < n; i++ {
		for j < i-k || (j < n && nums[j] != key) {
			j++
		}
		if j < n && j <= i+k {
			ans = append(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function findKDistantIndices(nums: number[], key: number, k: number): number[] {
    const n = nums.length;
    const ans: number[] = [];
    for (let i = 0, j = 0; i < n; ++i) {
        while (j < i - k || (j < n && nums[j] !== key)) {
            ++j;
        }
        if (j < n && j <= i + k) {
            ans.push(i);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_k_distant_indices(nums: Vec<i32>, key: i32, k: i32) -> Vec<i32> {
        let n = nums.len();
        let mut ans = Vec::new();
        let mut j = 0;
        for i in 0..n {
            while j < i.saturating_sub(k as usize) || (j < n && nums[j] != key) {
                j += 1;
            }
            if j < n && j <= i + k as usize {
                ans.push(i as i32);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
