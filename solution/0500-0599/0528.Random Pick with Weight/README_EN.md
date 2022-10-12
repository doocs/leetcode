# [528. Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight)

[中文文档](/solution/0500-0599/0528.Random%20Pick%20with%20Weight/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of positive integers <code>w</code> where <code>w[i]</code> describes the <strong>weight</strong> of the <code>i<sup>th</sup></code> index.</p>

<p>You need to implement the function <code>pickIndex()</code>, which <strong>randomly</strong> picks an index in the range <code>[0, w.length - 1]</code> (<strong>inclusive</strong>) and returns it. The <strong>probability</strong> of picking an index <code>i</code> is <code>w[i] / sum(w)</code>.</p>

<ul>
	<li>For example, if <code>w = [1, 3]</code>, the probability of picking index <code>0</code> is <code>1 / (1 + 3) = 0.25</code> (i.e., <code>25%</code>), and the probability of picking index <code>1</code> is <code>3 / (1 + 3) = 0.75</code> (i.e., <code>75%</code>).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Solution&quot;,&quot;pickIndex&quot;]
[[[1]],[]]
<strong>Output</strong>
[null,0]

<strong>Explanation</strong>
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Solution&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;]
[[[1,3]],[],[],[],[],[]]
<strong>Output</strong>
[null,1,1,1,1,0]

<strong>Explanation</strong>
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

Since this is a randomization problem, multiple answers are allowed.
All of the following outputs can be considered correct:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= w.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= w[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>pickIndex</code> will be called at most <code>10<sup>4</sup></code> times.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def __init__(self, w: List[int]):
        self.s = [0]
        for c in w:
            self.s.append(self.s[-1] + c)

    def pickIndex(self) -> int:
        x = random.randint(1, self.s[-1])
        left, right = 1, len(self.s) - 1
        while left < right:
            mid = (left + right) >> 1
            if self.s[mid] >= x:
                right = mid
            else:
                left = mid + 1
        return left - 1


# Your Solution object will be instantiated and called as such:
# obj = Solution(w)
# param_1 = obj.pickIndex()
```

### **Java**

```java
class Solution {
    private int[] s;
    private Random random = new Random();

    public Solution(int[] w) {
        int n = w.length;
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + w[i];
        }
    }

    public int pickIndex() {
        int x = 1 + random.nextInt(s[s.length - 1]);
        int left = 1, right = s.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
```

### **C++**

```cpp
class Solution {
public:
    vector<int> s;

    Solution(vector<int>& w) {
        int n = w.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + w[i];
    }

    int pickIndex() {
        int n = s.size();
        int x = 1 + rand() % s[n - 1];
        int left = 1, right = n - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (s[mid] >= x)
                right = mid;
            else
                left = mid + 1;
        }
        return left - 1;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(w);
 * int param_1 = obj->pickIndex();
 */
```

### **Go**

```go
type Solution struct {
	s []int
}

func Constructor(w []int) Solution {
	n := len(w)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + w[i]
	}
	return Solution{s}
}

func (this *Solution) PickIndex() int {
	n := len(this.s)
	x := 1 + rand.Intn(this.s[n-1])
	left, right := 1, n-1
	for left < right {
		mid := (left + right) >> 1
		if this.s[mid] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left - 1
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(w);
 * param_1 := obj.PickIndex();
 */
```

### **JavaScript**

```js
/**
 * @param {number[]} w
 */
var Solution = function (w) {
    const n = w.length;
    this.s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        this.s[i + 1] = this.s[i] + w[i];
    }
};

/**
 * @return {number}
 */
Solution.prototype.pickIndex = function () {
    const n = this.s.length;
    const x = 1 + Math.floor(Math.random() * this.s[n - 1]);
    let left = 1,
        right = n - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (this.s[mid] >= x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left - 1;
};

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = new Solution(w)
 * var param_1 = obj.pickIndex()
 */
```

### **Rust**

```rust
use rand::{thread_rng, Rng};

struct Solution {
    sum: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl Solution {
    fn new(w: Vec<i32>) -> Self {
        let n = w.len();
        let mut sum = vec![0; n + 1];
        for i in 1..=n {
            sum[i] = sum[i - 1] + w[i - 1];
        }
        Self { sum }
    }

    fn pick_index(&self) -> i32 {
        let x = thread_rng().gen_range(1, self.sum.last().unwrap() + 1);
        let (mut left, mut right) = (1, self.sum.len() - 1);
        while left < right {
            let mid = (left + right) >> 1;
            if self.sum[mid] < x {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        (left - 1) as i32
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * let obj = Solution::new(w);
 * let ret_1: i32 = obj.pick_index();
 */
```

### **...**

```

```

<!-- tabs:end -->
