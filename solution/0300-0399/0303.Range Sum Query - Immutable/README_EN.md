# [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable)

[中文文档](/solution/0300-0399/0303.Range%20Sum%20Query%20-%20Immutable/README.md)

## Description

<p>Given an integer array <code>nums</code>, handle multiple queries of the following type:</p>

<ol>
	<li>Calculate the <strong>sum</strong> of the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> <strong>inclusive</strong> where <code>left &lt;= right</code>.</li>
</ol>

<p>Implement the <code>NumArray</code> class:</p>

<ul>
	<li><code>NumArray(int[] nums)</code> Initializes the object with the integer array <code>nums</code>.</li>
	<li><code>int sumRange(int left, int right)</code> Returns the <strong>sum</strong> of the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> <strong>inclusive</strong> (i.e. <code>nums[left] + nums[left + 1] + ... + nums[right]</code>).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;NumArray&quot;, &quot;sumRange&quot;, &quot;sumRange&quot;, &quot;sumRange&quot;]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
<strong>Output</strong>
[null, 1, -1, -3]

<strong>Explanation</strong>
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; nums.length</code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>sumRange</code>.</li>
</ul>

## Solutions

**Solution 1: Prefix Sum**

We create a prefix sum array $s$ of length $n + 1$, where $s[i]$ represents the prefix sum of the first $i$ elements, that is, $s[i] = \sum_{j=0}^{i-1} nums[j]$. Therefore, the sum of the elements between the indices $[left, right]$ can be expressed as $s[right + 1] - s[left]$.

The time complexity for initializing the prefix sum array $s$ is $O(n)$, and the time complexity for querying is $O(1)$. The space complexity is $O(n)$.

<!-- tabs:start -->

### **Python3**

```python
class NumArray:
    def __init__(self, nums: List[int]):
        self.s = list(accumulate(nums, initial=0))

    def sumRange(self, left: int, right: int) -> int:
        return self.s[right + 1] - self.s[left]


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# param_1 = obj.sumRange(left,right)
```

### **Java**

```java
class NumArray {
    private int[] s;

    public NumArray(int[] nums) {
        int n = nums.length;
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return s[right + 1] - s[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
```

### **C++**

```cpp
class NumArray {
public:
    NumArray(vector<int>& nums) {
        int n = nums.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
    }

    int sumRange(int left, int right) {
        return s[right + 1] - s[left];
    }

private:
    vector<int> s;
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * int param_1 = obj->sumRange(left,right);
 */
```

### **Go**

```go
type NumArray struct {
	s []int
}

func Constructor(nums []int) NumArray {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	return NumArray{s}
}

func (this *NumArray) SumRange(left int, right int) int {
	return this.s[right+1] - this.s[left]
}

/**
 * Your NumArray object will be instantiated and called as such:
 * obj := Constructor(nums);
 * param_1 := obj.SumRange(left,right);
 */
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 */
var NumArray = function (nums) {
    const n = nums.length;
    this.s = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        this.s[i + 1] = this.s[i] + nums[i];
    }
};

/**
 * @param {number} left
 * @param {number} right
 * @return {number}
 */
NumArray.prototype.sumRange = function (left, right) {
    return this.s[right + 1] - this.s[left];
};

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * var param_1 = obj.sumRange(left,right)
 */
```

### **TypeScript**

```ts
class NumArray {
    private s: number[];

    constructor(nums: number[]) {
        const n = nums.length;
        this.s = Array(n + 1).fill(0);
        for (let i = 0; i < n; ++i) {
            this.s[i + 1] = this.s[i] + nums[i];
        }
    }

    sumRange(left: number, right: number): number {
        return this.s[right + 1] - this.s[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * var param_1 = obj.sumRange(left,right)
 */
```

### **Rust**

```rust
struct NumArray {
    s: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumArray {
    fn new(mut nums: Vec<i32>) -> Self {
        let n = nums.len();
        let mut s = vec![0; n + 1];
        for i in 0..n {
            s[i + 1] = s[i] + nums[i];
        }
        Self { s }
    }

    fn sum_range(&self, left: i32, right: i32) -> i32 {
        self.s[(right + 1) as usize] - self.s[left as usize]
    }
}/**
 * Your NumArray object will be instantiated and called as such:
 * let obj = NumArray::new(nums);
 * let ret_1: i32 = obj.sum_range(left, right);
 */
```

### **C**

```c
typedef struct {
    int* s;
} NumArray;

NumArray* numArrayCreate(int* nums, int n) {
    int* s = malloc(sizeof(int) * (n + 1));
    s[0] = 0;
    for (int i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }
    NumArray* obj = malloc(sizeof(NumArray));
    obj->s = s;
    return obj;
}

int numArraySumRange(NumArray* obj, int left, int right) {
    return obj->s[right + 1] - obj->s[left];
}

void numArrayFree(NumArray* obj) {
    free(obj->s);
    free(obj);
}

/**
 * Your NumArray struct will be instantiated and called as such:
 * NumArray* obj = numArrayCreate(nums, numsSize);
 * int param_1 = numArraySumRange(obj, left, right);

 * numArrayFree(obj);
*/
```

### **PHP**

```php
class NumArray {
    /**
     * @param Integer[] $nums
     */
    function __construct($nums) {
        $this->s = [0];
        foreach ($nums as $x) {
            $this->s[] = $this->s[count($this->s) - 1] + $x;
        }
    }

    /**
     * @param Integer $left
     * @param Integer $right
     * @return Integer
     */
    function sumRange($left, $right) {
        return $this->s[$right + 1] - $this->s[$left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * $obj = NumArray($nums);
 * $ret_1 = $obj->sumRange($left, $right);
 */
```

### **...**

```

```

<!-- tabs:end -->
