# [528. Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight)

[中文文档](/solution/0500-0599/0528.Random%20Pick%20with%20Weight/README.md)

## Description

<p>You are given an array of positive integers <code>w</code> where <code>w[i]</code> describes the weight of <code>i</code><sup><code>th</code>&nbsp;</sup>index (0-indexed).</p>

<p>We need to call the function&nbsp;<code>pickIndex()</code> which <strong>randomly</strong> returns an integer in the range <code>[0, w.length - 1]</code>.&nbsp;<code>pickIndex()</code>&nbsp;should return the integer&nbsp;proportional to its weight in the <code>w</code> array. For example, for <code>w = [1, 3]</code>, the probability of picking the index <code>0</code> is <code>1 / (1 + 3)&nbsp;= 0.25</code> (i.e 25%)&nbsp;while the probability of picking the index <code>1</code> is <code>3 / (1 + 3)&nbsp;= 0.75</code> (i.e 75%).</p>

<p>More formally, the probability of picking index <code>i</code> is <code>w[i] / sum(w)</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Solution&quot;,&quot;pickIndex&quot;]
[[[1]],[]]
<strong>Output</strong>
[null,0]

<strong>Explanation</strong>
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Solution&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;]
[[[1,3]],[],[],[],[],[]]
<strong>Output</strong>
[null,1,1,1,1,0]

<strong>Explanation</strong>
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It&#39;s returning the second element (index = 1) that has probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It&#39;s returning the first element (index = 0) that has probability of 1/4.

Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
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
	<li><code>1 &lt;= w.length &lt;= 10000</code></li>
	<li><code>1 &lt;= w[i] &lt;= 10^5</code></li>
	<li><code>pickIndex</code>&nbsp;will be called at most <code>10000</code> times.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:

    def __init__(self, w: List[int]):
        n = len(w)
        self.presum = [0] * (n + 1)
        for i in range(n):
            self.presum[i + 1] = self.presum[i] + w[i]

    def pickIndex(self) -> int:
        n = len(self.presum)
        x = random.randint(1, self.presum[-1])
        left, right = 0, n - 2
        while left < right:
            mid = (left + right) >> 1
            if self.presum[mid + 1] >= x:
                right = mid
            else:
                left = mid + 1
        return left

# Your Solution object will be instantiated and called as such:
# obj = Solution(w)
# param_1 = obj.pickIndex()
```

### **Java**

```java
class Solution {
    private int[] presum;

    public Solution(int[] w) {
        int n = w.length;
        presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + w[i];
        }
    }

    public int pickIndex() {
        int n = presum.length;
        int x = (int) (Math.random() * presum[n - 1]) + 1;
        int left = 0, right = n - 2;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (presum[mid + 1] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
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
    vector<int> presum;

    Solution(vector<int>& w) {
        int n = w.size();
        presum.resize(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + w[i];
    }

    int pickIndex() {
        int n = presum.size();
        int x = rand() % presum[n - 1] + 1;
        int left = 0, right = n - 2;
        while (left < right)
        {
            int mid = left + right >> 1;
            if (presum[mid + 1] >= x) right = mid;
            else left = mid + 1;
        }
        return left;
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
	presum []int
}

func Constructor(w []int) Solution {
	n := len(w)
	pre := make([]int, n+1)
	for i := 0; i < n; i++ {
		pre[i+1] = pre[i] + w[i]
	}
	return Solution{pre}
}

func (this *Solution) PickIndex() int {
	n := len(this.presum)
	x := rand.Intn(this.presum[n-1]) + 1
	left, right := 0, n-2
	for left < right {
		mid := (left + right) >> 1
		if this.presum[mid+1] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(w);
 * param_1 := obj.PickIndex();
 */
```

### **...**

```

```

<!-- tabs:end -->
