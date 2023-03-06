# [303. 区域和检索 - 数组不可变](https://leetcode.cn/problems/range-sum-query-immutable)

[English Version](/solution/0300-0399/0303.Range%20Sum%20Query%20-%20Immutable/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 &nbsp;<code>nums</code>，处理以下类型的多个查询:</p>

<ol>
	<li>计算索引&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>&nbsp;（包含 <code>left</code> 和 <code>right</code>）之间的 <code>nums</code> 元素的 <strong>和</strong> ，其中&nbsp;<code>left &lt;= right</code></li>
</ol>

<p>实现 <code>NumArray</code> 类：</p>

<ul>
	<li><code>NumArray(int[] nums)</code> 使用数组 <code>nums</code> 初始化对象</li>
	<li><code>int sumRange(int i, int j)</code> 返回数组 <code>nums</code>&nbsp;中索引&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>&nbsp;之间的元素的 <strong>总和</strong> ，包含&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>&nbsp;两点（也就是&nbsp;<code>nums[left] + nums[left + 1] + ... + nums[right]</code>&nbsp;)</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
<strong>输出：
</strong>[null, 1, -1, -3]

<strong>解释：</strong>
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= nums[i] &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>0 &lt;= i &lt;= j &lt; nums.length</code></li>
	<li>最多调用 <code>10<sup>4</sup></code> 次 <code>sumRange</code><strong> </strong>方法</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和**

前缀和计算公式：`s[i + 1] = s[i] + nums[i]`。

初始化的时间复杂度是 $O(n)$，每次查询的时间复杂度是 $O(1)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    vector<int> s;

    NumArray(vector<int>& nums) {
        int n = nums.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
    }

    int sumRange(int left, int right) {
        return s[right + 1] - s[left];
    }
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
    this.s = new Array(n + 1).fill(0);
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
        this.s = new Array(n + 1).fill(0);
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
    nums: Vec<i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumArray {

    fn new(nums: Vec<i32>) -> Self {
        Self {
            nums
        }
    }

    fn sum_range(&self, left: i32, right: i32) -> i32 {
        let (left, right) = (left as usize, right as usize);
        self.nums[left..=right].iter().sum::<i32>()
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * let obj = NumArray::new(nums);
 * let ret_1: i32 = obj.sum_range(left, right);
 */
```

```rust
struct NumArray {
    sums: Vec<i32>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumArray {

    fn new(mut nums: Vec<i32>) -> Self {
        let n = nums.len();
        let mut sums = vec![0; n + 1];
        for i in 0..n {
            sums[i + 1] = sums[i] + nums[i];
        }
        Self { sums }
    }

    fn sum_range(&self, left: i32, right: i32) -> i32 {
        self.sums[(right + 1) as usize] - self.sums[left as usize]
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * let obj = NumArray::new(nums);
 * let ret_1: i32 = obj.sum_range(left, right);
 */
```

### **C**

```c
typedef struct {
    int *sums;
} NumArray;


NumArray *numArrayCreate(int *nums, int numsSize) {
    int *sums = malloc(sizeof(int) * (numsSize + 1));
    memset(sums, 0, numsSize + 1);
    for (int i = 0; i < numsSize; i++) {
        sums[i + 1] = sums[i] + nums[i];
    }
    NumArray *res = malloc(sizeof(NumArray));
    res->sums = sums;
    return res;
}

int numArraySumRange(NumArray *obj, int left, int right) {
    return obj->sums[right + 1] - obj->sums[left];
}

void numArrayFree(NumArray *obj) {
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
        $this->sum = [0];
        for ($i = 0; $i < count($nums); $i++) {
            array_push($this->sum, $this->sum[$i] + $nums[$i]);
        }
    }
    /**
     * @param Integer $left
     * @param Integer $right
     * @return Integer
     */
    function sumRange($left, $right) {
        return $this->sum[$right + 1] - $this->sum[$left];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
