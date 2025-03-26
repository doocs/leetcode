---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1570.Dot%20Product%20of%20Two%20Sparse%20Vectors/README.md
tags:
    - 设计
    - 数组
    - 哈希表
    - 双指针
---

<!-- problem:start -->

# [1570. 两个稀疏向量的点积 🔒](https://leetcode.cn/problems/dot-product-of-two-sparse-vectors)

[English Version](/solution/1500-1599/1570.Dot%20Product%20of%20Two%20Sparse%20Vectors/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个稀疏向量，计算它们的点积（数量积）。</p>

<p>实现类 <code>SparseVector</code>：</p>

<ul>
	<li><code>SparseVector(nums)</code> 以向量 <code>nums</code> 初始化对象。</li>
	<li><code>dotProduct(vec)</code> 计算此向量与 <code>vec</code> 的点积。</li>
</ul>

<p><strong>稀疏向量</strong> 是指绝大多数分量为 0 的向量。你需要 <strong>高效</strong> 地存储这个向量，并计算两个稀疏向量的点积。</p>

<p><strong>进阶：</strong>当其中只有一个向量是稀疏向量时，你该如何解决此问题？</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
<strong>输出：</strong>8
<strong>解释：</strong>v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
<strong>输出：</strong>0
<strong>解释：</strong>v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
<strong>输出：</strong>6
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 <= n <= 10^5</code></li>
	<li><code>0 <= nums1[i], nums2[i] <= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用哈希表 $d$ 来存储非零元素，其中键为下标，值为对应的值。我们遍历 $\textit{nums}$，如果 $\textit{nums}[i]$ 不为 $0$，我们就将 $(i, \textit{nums}[i])$ 加入到哈希表 $d$ 中。

在计算点积时，我们遍历非零元素较少的哈希表，并判断另一个哈希表中是否存在对应的键，如果存在就将对应的值相乘并累加到答案中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

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
