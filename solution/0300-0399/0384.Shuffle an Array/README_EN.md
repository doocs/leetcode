# [384. Shuffle an Array](https://leetcode.com/problems/shuffle-an-array)

[中文文档](/solution/0300-0399/0384.Shuffle%20an%20Array/README.md)

## Description

<p>Given an integer array <code>nums</code>, design an algorithm to randomly shuffle the array. All permutations of the array should be <strong>equally likely</strong> as a result of the shuffling.</p>

<p>Implement the <code>Solution</code> class:</p>

<ul>
	<li><code>Solution(int[] nums)</code> Initializes the object with the integer array <code>nums</code>.</li>
	<li><code>int[] reset()</code> Resets the array to its original configuration and returns it.</li>
	<li><code>int[] shuffle()</code> Returns a random shuffling of the array.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Solution&quot;, &quot;shuffle&quot;, &quot;reset&quot;, &quot;shuffle&quot;]
[[[1, 2, 3]], [], [], []]
<strong>Output</strong>
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

<strong>Explanation</strong>
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
                       // Any permutation of [1,2,3] must be equally likely to be returned.
                       // Example: return [3, 1, 2]
solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li>All the elements of <code>nums</code> are <strong>unique</strong>.</li>
	<li>At most <code>10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>reset</code> and <code>shuffle</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def __init__(self, nums: List[int]):
        self.nums = nums
        self.original = nums.copy()

    def reset(self) -> List[int]:
        self.nums = self.original.copy()
        return self.nums

    def shuffle(self) -> List[int]:
        for i in range(len(self.nums)):
            j = random.randrange(i, len(self.nums))
            self.nums[i], self.nums[j] = self.nums[j], self.nums[i]
        return self.nums


# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.reset()
# param_2 = obj.shuffle()
```

### **Java**

```java
class Solution {
    private int[] nums;
    private int[] original;
    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.original = Arrays.copyOf(nums, nums.length);
        this.rand = new Random();
    }

    public int[] reset() {
        nums = Arrays.copyOf(original, original.length);
        return nums;
    }

    public int[] shuffle() {
        for (int i = 0; i < nums.length; ++i) {
            swap(i, i + rand.nextInt(nums.length - i));
        }
        return nums;
    }

    private void swap(int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
```

### **C++**

```cpp
class Solution {
public:
    vector<int> nums;
    vector<int> original;

    Solution(vector<int>& nums) {
        this->nums = nums;
        this->original.resize(nums.size());
        copy(nums.begin(), nums.end(), original.begin());
    }

    vector<int> reset() {
        copy(original.begin(), original.end(), nums.begin());
        return nums;
    }

    vector<int> shuffle() {
        for (int i = 0; i < nums.size(); ++i) {
            int j = i + rand() % (nums.size() - i);
            swap(nums[i], nums[j]);
        }
        return nums;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * vector<int> param_1 = obj->reset();
 * vector<int> param_2 = obj->shuffle();
 */
```

### **Go**

```go
type Solution struct {
	nums, original []int
}

func Constructor(nums []int) Solution {
	return Solution{nums, append([]int(nil), nums...)}
}

func (this *Solution) Reset() []int {
	copy(this.nums, this.original)
	return this.nums
}

func (this *Solution) Shuffle() []int {
	n := len(this.nums)
	for i := range this.nums {
		j := i + rand.Intn(n-i)
		this.nums[i], this.nums[j] = this.nums[j], this.nums[i]
	}
	return this.nums
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(nums);
 * param_1 := obj.Reset();
 * param_2 := obj.Shuffle();
 */
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 */
const Solution = function (nums) {
    this.nums = nums || [];
};

/**
 * Resets the array to its original configuration and return it.
 * @return {number[]}
 */
Solution.prototype.reset = function () {
    return this.nums;
};

/**
 * Returns a random shuffling of the array.
 * @return {number[]}
 */
Solution.prototype.shuffle = function () {
    let a = this.nums.slice();
    for (let i = 0; i < a.length; i++) {
        let rand = Math.floor(Math.random() * (a.length - i)) + i;
        let tmp = a[i];
        a[i] = a[rand];
        a[rand] = tmp;
    }
    return a;
};

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = Object.create(Solution).createNew(nums)
 * var param_1 = obj.reset()
 * var param_2 = obj.shuffle()
 */
```

### **TypeScript**

```ts
class Solution {
    private nums: number[];

    constructor(nums: number[]) {
        this.nums = nums;
    }

    reset(): number[] {
        return this.nums;
    }

    shuffle(): number[] {
        const n = this.nums.length;
        const res = [...this.nums];
        for (let i = 0; i < n; i++) {
            const j = Math.floor(Math.random() * n);
            [res[i], res[j]] = [res[j], res[i]];
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = new Solution(nums)
 * var param_1 = obj.reset()
 * var param_2 = obj.shuffle()
 */
```

### **Rust**

```rust
use rand::Rng;
struct Solution {
    nums: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl Solution {
    fn new(nums: Vec<i32>) -> Self {
        Self { nums }
    }

    fn reset(&self) -> Vec<i32> {
        self.nums.clone()
    }

    fn shuffle(&mut self) -> Vec<i32> {
        let n = self.nums.len();
        let mut res = self.nums.clone();
        for i in 0..n {
            let j = rand::thread_rng().gen_range(0, n);
            res.swap(i, j);
        }
        res
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * let obj = Solution::new(nums);
 * let ret_1: Vec<i32> = obj.reset();
 * let ret_2: Vec<i32> = obj.shuffle();
 */
```

### **...**

```

```

<!-- tabs:end -->
