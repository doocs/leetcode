# [1570. Dot Product of Two Sparse Vectors](https://leetcode.com/problems/dot-product-of-two-sparse-vectors)

[中文文档](/solution/1500-1599/1570.Dot%20Product%20of%20Two%20Sparse%20Vectors/README.md)

## Description

<p>Given two sparse vectors, compute their dot product.</p>

<p>Implement class <code>SparseVector</code>:</p>

<ul data-indent="0" data-stringify-type="unordered-list">
	<li><code>SparseVector(nums)</code>&nbsp;Initializes the object with the vector <code>nums</code></li>
	<li><code>dotProduct(vec)</code>&nbsp;Compute the dot product between the instance of <em>SparseVector</em> and <code>vec</code></li>
</ul>

<p>A <strong>sparse vector</strong> is a vector that has mostly zero values, you should store the sparse vector&nbsp;<strong>efficiently </strong>and compute the dot product between two <em>SparseVector</em>.</p>

<p><strong>Follow up:&nbsp;</strong>What if only one of the vectors is sparse?</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
<strong>Output:</strong> 8
<strong>Explanation:</strong> v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
</pre>

<p><strong>Example 3:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class SparseVector:
    def __init__(self, nums: List[int]):
        self.v = {}
        for i, num in enumerate(nums):
            if num != 0:
                self.v[i] = num

    # Return the dotProduct of two sparse vectors
    def dotProduct(self, vec: 'SparseVector') -> int:
        res = 0
        if len(self.v) > len(vec.v):
            self.v, vec.v = vec.v, self.v
        for i, num in self.v.items():
            if i not in vec.v:
                continue
            res += num * vec.v[i]
        return res


# Your SparseVector object will be instantiated and called as such:
# v1 = SparseVector(nums1)
# v2 = SparseVector(nums2)
# ans = v1.dotProduct(v2)
```

### **Java**

```java
class SparseVector {

    private Map<Integer, Integer> v;

    SparseVector(int[] nums) {
        v = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                v.put(i, nums[i]);
            }
        }
    }

	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int res = 0;
        if (v.size() > vec.v.size()) {
            Map<Integer, Integer> t = v;
            v = vec.v;
            vec.v = t;
        }
        for (Map.Entry<Integer, Integer> entry : v.entrySet()) {
            int i = entry.getKey(), num = entry.getValue();
            res += num * vec.v.getOrDefault(i, 0);
        }
        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
```

### **...**

```

```

<!-- tabs:end -->
