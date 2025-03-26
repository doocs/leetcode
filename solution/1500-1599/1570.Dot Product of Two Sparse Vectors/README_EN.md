---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1570.Dot%20Product%20of%20Two%20Sparse%20Vectors/README_EN.md
tags:
    - Design
    - Array
    - Hash Table
    - Two Pointers
---

<!-- problem:start -->

# [1570. Dot Product of Two Sparse Vectors 🔒](https://leetcode.com/problems/dot-product-of-two-sparse-vectors)

[中文文档](/solution/1500-1599/1570.Dot%20Product%20of%20Two%20Sparse%20Vectors/README.md)

## Description

<!-- description:start -->

<p>Given two sparse vectors, compute their dot product.</p>

<p>Implement class <code>SparseVector</code>:</p>

<ul data-indent="0" data-stringify-type="unordered-list">
	<li><code>SparseVector(nums)</code>&nbsp;Initializes the object with the vector <code>nums</code></li>
	<li><code>dotProduct(vec)</code>&nbsp;Compute the dot product between the instance of <em>SparseVector</em> and <code>vec</code></li>
</ul>

<p>A <strong>sparse vector</strong> is a vector that has mostly zero values, you should store the sparse vector&nbsp;<strong>efficiently </strong>and compute the dot product between two <em>SparseVector</em>.</p>

<p><strong>Follow up:&nbsp;</strong>What if only one of the vectors is sparse?</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
<strong>Output:</strong> 8
<strong>Explanation:</strong> v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i]&nbsp;&lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map

We use a hash map $d$ to store non-zero elements, where the key is the index, and the value is the corresponding value. We iterate through $\textit{nums}$, and if $\textit{nums}[i]$ is not $0$, we add $(i, \textit{nums}[i])$ to the hash map $d$.

When calculating the dot product, we iterate through the hash map with fewer non-zero elements and check if the other hash map contains the corresponding key. If it exists, we multiply the corresponding values and add them to the result.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class SparseVector:
    def __init__(self, nums: List[int]):
        self.d = {i: v for i, v in enumerate(nums) if v}

    # Return the dotProduct of two sparse vectors
    def dotProduct(self, vec: "SparseVector") -> int:
        a, b = self.d, vec.d
        if len(b) < len(a):
            a, b = b, a
        return sum(v * b.get(i, 0) for i, v in a.items())


# Your SparseVector object will be instantiated and called as such:
# v1 = SparseVector(nums1)
# v2 = SparseVector(nums2)
# ans = v1.dotProduct(v2)
```

#### Java

```java
class SparseVector {
    public Map<Integer, Integer> d = new HashMap<>(128);

    SparseVector(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                d.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        var a = d;
        var b = vec.d;
        if (b.size() < a.size()) {
            var t = a;
            a = b;
            b = t;
        }
        int ans = 0;
        for (var e : a.entrySet()) {
            int i = e.getKey(), v = e.getValue();
            ans += v * b.getOrDefault(i, 0);
        }
        return ans;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
```

#### C++

```cpp
class SparseVector {
public:
    unordered_map<int, int> d;

    SparseVector(vector<int>& nums) {
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i]) {
                d[i] = nums[i];
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    int dotProduct(SparseVector& vec) {
        auto a = d;
        auto b = vec.d;
        if (a.size() > b.size()) {
            swap(a, b);
        }
        int ans = 0;
        for (auto& [i, v] : a) {
            if (b.count(i)) {
                ans += v * b[i];
            }
        }
        return ans;
    }
};

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1(nums1);
// SparseVector v2(nums2);
// int ans = v1.dotProduct(v2);
```

#### Go

```go
type SparseVector struct {
	d map[int]int
}

func Constructor(nums []int) SparseVector {
	d := map[int]int{}
	for i, x := range nums {
		if x != 0 {
			d[i] = x
		}
	}
	return SparseVector{d}
}

// Return the dotProduct of two sparse vectors
func (this *SparseVector) dotProduct(vec SparseVector) (ans int) {
	a, b := this.d, vec.d
	if len(a) > len(b) {
		a, b = b, a
	}
	for i, x := range a {
		if y, has := b[i]; has {
			ans += x * y
		}
	}
	return
}

/**
 * Your SparseVector object will be instantiated and called as such:
 * v1 := Constructor(nums1);
 * v2 := Constructor(nums2);
 * ans := v1.dotProduct(v2);
 */
```

#### TypeScript

```ts
class SparseVector {
    d: Map<number, number>;

    constructor(nums: number[]) {
        this.d = new Map();
        for (let i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                this.d.set(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    dotProduct(vec: SparseVector): number {
        let a = this.d;
        let b = vec.d;
        if (a.size > b.size) {
            [a, b] = [b, a];
        }
        let ans = 0;
        for (const [i, x] of a) {
            if (b.has(i)) {
                ans += x * b.get(i)!;
            }
        }
        return ans;
    }
}

/**
 * Your SparseVector object will be instantiated and called as such:
 * var v1 = new SparseVector(nums1)
 * var v2 = new SparseVector(nums1)
 * var ans = v1.dotProduct(v2)
 */
```

#### Rust

```rust
use std::collections::HashMap;

#[derive(Clone)]
struct SparseVector {
    d: HashMap<usize, i32>,
}

impl SparseVector {
    fn new(nums: Vec<i32>) -> Self {
        let mut d = HashMap::new();
        for (i, &x) in nums.iter().enumerate() {
            if x != 0 {
                d.insert(i, x);
            }
        }
        SparseVector { d }
    }

    fn dot_product(&self, vec: SparseVector) -> i32 {
        let (a, b) = (&self.d, &vec.d);
        let mut ans = 0;

        if a.len() > b.len() {
            return vec.dot_product(self.clone());
        }

        for (&i, &x) in a.iter() {
            if let Some(&y) = b.get(&i) {
                ans += x * y;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
