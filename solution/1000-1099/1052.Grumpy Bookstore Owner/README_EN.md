# [1052. Grumpy Bookstore Owner](https://leetcode.com/problems/grumpy-bookstore-owner)

[中文文档](/solution/1000-1099/1052.Grumpy%20Bookstore%20Owner/README.md)

## Description

<p>There is a bookstore owner that has a store open for <code>n</code> minutes. Every minute, some number of customers enter the store. You are given an integer array <code>customers</code> of length <code>n</code> where <code>customers[i]</code> is the number of the customer that enters the store at the start of the <code>i<sup>th</sup></code> minute and all those customers leave after the end of that minute.</p>

<p>On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where <code>grumpy[i]</code> is <code>1</code> if the bookstore owner is grumpy during the <code>i<sup>th</sup></code> minute, and is <code>0</code> otherwise.</p>

<p>When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.</p>

<p>The bookstore owner knows a secret technique to keep themselves not grumpy for <code>minutes</code> consecutive minutes, but can only use it once.</p>

<p>Return <em>the maximum number of customers that can be satisfied throughout the day</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
<strong>Output:</strong> 16
<strong>Explanation:</strong> The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> customers = [1], grumpy = [0], minutes = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == customers.length == grumpy.length</code></li>
	<li><code>1 &lt;= minutes &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= customers[i] &lt;= 1000</code></li>
	<li><code>grumpy[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSatisfied(
        self, customers: List[int], grumpy: List[int], minutes: int
    ) -> int:
        s = sum(a * b for a, b in zip(customers, grumpy))
        cs = sum(customers)
        t = ans = 0
        for i, (a, b) in enumerate(zip(customers, grumpy), 1):
            t += a * b
            if (j := i - minutes) >= 0:
                ans = max(ans, cs - (s - t))
                t -= customers[j] * grumpy[j]
        return ans
```

### **Java**

```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int s = 0, cs = 0;
        int n = customers.length;
        for (int i = 0; i < n; ++i) {
            s += customers[i] * grumpy[i];
            cs += customers[i];
        }
        int t = 0, ans = 0;
        for (int i = 0; i < n; ++i) {
            t += customers[i] * grumpy[i];
            int j = i - minutes + 1;
            if (j >= 0) {
                ans = Math.max(ans, cs - (s - t));
                t -= customers[j] * grumpy[j];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSatisfied(vector<int>& customers, vector<int>& grumpy, int minutes) {
        int s = 0, cs = 0;
        int n = customers.size();
        for (int i = 0; i < n; ++i) {
            s += customers[i] * grumpy[i];
            cs += customers[i];
        }
        int t = 0, ans = 0;
        for (int i = 0; i < n; ++i) {
            t += customers[i] * grumpy[i];
            int j = i - minutes + 1;
            if (j >= 0) {
                ans = max(ans, cs - (s - t));
                t -= customers[j] * grumpy[j];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSatisfied(customers []int, grumpy []int, minutes int) int {
	s, cs := 0, 0
	for i, c := range customers {
		s += c * grumpy[i]
		cs += c
	}
	t, ans := 0, 0
	for i, c := range customers {
		t += c * grumpy[i]
		j := i - minutes + 1
		if j >= 0 {
			ans = max(ans, cs-(s-t))
			t -= customers[j] * grumpy[j]
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_satisfied(customers: Vec<i32>, grumpy: Vec<i32>, minutes: i32) -> i32 {
        let k = minutes as usize;
        let n = customers.len();

        let mut sum = 0;
        for i in 0..k {
            if grumpy[i] == 1 {
                sum += customers[i];
            }
        }
        let mut max = sum;
        for i in k..n {
            if grumpy[i - k] == 1 {
                sum -= customers[i - k];
            }
            if grumpy[i] == 1 {
                sum += customers[i];
            }
            max = max.max(sum);
        }

        sum = 0;
        for i in 0..n {
            if grumpy[i] == 0 {
                sum += customers[i];
            }
        }
        sum + max
    }
}
```

### **...**

```

```

<!-- tabs:end -->
