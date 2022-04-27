# [1894. Find the Student that Will Replace the Chalk](https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk)

[中文文档](/solution/1800-1899/1894.Find%20the%20Student%20that%20Will%20Replace%20the%20Chalk/README.md)

## Description

<p>There are <code>n</code> students in a class numbered from <code>0</code> to <code>n - 1</code>. The teacher will give each student a problem starting with the student number <code>0</code>, then the student number <code>1</code>, and so on until the teacher reaches the student number <code>n - 1</code>. After that, the teacher will restart the process, starting with the student number <code>0</code> again.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>chalk</code> and an integer <code>k</code>. There are initially <code>k</code> pieces of chalk. When the student number <code>i</code> is given a problem to solve, they will use <code>chalk[i]</code> pieces of chalk to solve that problem. However, if the current number of chalk pieces is <strong>strictly less</strong> than <code>chalk[i]</code>, then the student number <code>i</code> will be asked to <strong>replace</strong> the chalk.</p>

<p>Return <em>the <strong>index</strong> of the student that will <strong>replace</strong> the chalk</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> chalk = [5,1,5], k = 22
<strong>Output:</strong> 0
<strong>Explanation: </strong>The students go in turns as follows:
- Student number 0 uses 5 chalk, so k = 17.
- Student number 1 uses 1 chalk, so k = 16.
- Student number 2 uses 5 chalk, so k = 11.
- Student number 0 uses 5 chalk, so k = 6.
- Student number 1 uses 1 chalk, so k = 5.
- Student number 2 uses 5 chalk, so k = 0.
Student number 0 does not have enough chalk, so they will have to replace it.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> chalk = [3,4,1,2], k = 25
<strong>Output:</strong> 1
<strong>Explanation: </strong>The students go in turns as follows:
- Student number 0 uses 3 chalk so k = 22.
- Student number 1 uses 4 chalk so k = 18.
- Student number 2 uses 1 chalk so k = 17.
- Student number 3 uses 2 chalk so k = 15.
- Student number 0 uses 3 chalk so k = 12.
- Student number 1 uses 4 chalk so k = 8.
- Student number 2 uses 1 chalk so k = 7.
- Student number 3 uses 2 chalk so k = 5.
- Student number 0 uses 3 chalk so k = 2.
Student number 1 does not have enough chalk, so they will have to replace it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>chalk.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= chalk[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

PreSum and Binary search.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def chalkReplacer(self, chalk: List[int], k: int) -> int:
        s = list(accumulate(chalk))
        k %= s[-1]
        return bisect_right(s, k)
```

### **Java**

```java
class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + chalk[i];
        }
        k %= preSum[n];
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (preSum[mid + 1] > k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int chalkReplacer(vector<int>& chalk, int k) {
        int n = chalk.size();
        vector<long long> s(n, chalk[0]);
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + chalk[i];
        k %= s[n - 1];
        return upper_bound(s.begin(), s.end(), k) - s.begin();
    }
};
```

### **Go**

```go
func chalkReplacer(chalk []int, k int) int {
	n := len(chalk)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + chalk[i]
	}
	k %= s[n]
	left, right := 0, n-1
	for left < right {
		mid := (left + right) >> 1
		if s[mid+1] > k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **Rust**

```rust
impl Solution {
    pub fn chalk_replacer(chalk: Vec<i32>, k: i32) -> i32 {
        let pre_sum: Vec<i64> = chalk
            .into_iter()
            .map(|x| x as i64)
            .scan(0, |state, x| {
                *state += x;
                Some(*state)
            })
            .collect();

        pre_sum
            .binary_search(&(k as i64 % pre_sum.last().unwrap()))
            .map_or_else(|e| e, |v| v + 1) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
