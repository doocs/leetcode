# [1769. Minimum Number of Operations to Move All Balls to Each Box](https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box)

[中文文档](/solution/1700-1799/1769.Minimum%20Number%20of%20Operations%20to%20Move%20All%20Balls%20to%20Each%20Box/README.md)

## Description

<p>You have <code>n</code> boxes. You are given a binary string <code>boxes</code> of length <code>n</code>, where <code>boxes[i]</code> is <code>&#39;0&#39;</code> if the <code>i<sup>th</sup></code> box is <strong>empty</strong>, and <code>&#39;1&#39;</code> if it contains <strong>one</strong> ball.</p>

<p>In one operation, you can move <strong>one</strong> ball from a box to an adjacent box. Box <code>i</code> is adjacent to box <code>j</code> if <code>abs(i - j) == 1</code>. Note that after doing so, there may be more than one ball in some boxes.</p>

<p>Return an array <code>answer</code> of size <code>n</code>, where <code>answer[i]</code> is the <strong>minimum</strong> number of operations needed to move all the balls to the <code>i<sup>th</sup></code> box.</p>

<p>Each <code>answer[i]</code> is calculated considering the <strong>initial</strong> state of the boxes.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> boxes = &quot;110&quot;
<strong>Output:</strong> [1,1,3]
<strong>Explanation:</strong> The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> boxes = &quot;001011&quot;
<strong>Output:</strong> [11,8,5,4,3,4]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == boxes.length</code></li>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>boxes[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, boxes: str) -> List[int]:
        n = len(boxes)
        left = [0] * n
        right = [0] * n
        cnt = 0
        for i in range(1, n):
            if boxes[i - 1] == '1':
                cnt += 1
            left[i] = left[i - 1] + cnt
        cnt = 0
        for i in range(n - 2, -1, -1):
            if boxes[i + 1] == '1':
                cnt += 1
            right[i] = right[i + 1] + cnt
        return [a + b for a, b in zip(left, right)]
```

```python
class Solution:
    def minOperations(self, boxes: str) -> List[int]:
        n = len(boxes)
        ans = [0] * n
        cnt = 0
        for i in range(1, n):
            if boxes[i - 1] == '1':
                cnt += 1
            ans[i] = ans[i - 1] + cnt
        cnt = s = 0
        for i in range(n - 2, -1, -1):
            if boxes[i + 1] == '1':
                cnt += 1
            s += cnt
            ans[i] += s
        return ans
```

### **Java**

```java
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1, cnt = 0; i < n; ++i) {
            if (boxes.charAt(i - 1) == '1') {
                ++cnt;
            }
            left[i] = left[i - 1] + cnt;
        }
        for (int i = n - 2, cnt = 0; i >= 0; --i) {
            if (boxes.charAt(i + 1) == '1') {
                ++cnt;
            }
            right[i] = right[i + 1] + cnt;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = left[i] + right[i];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];
        for (int i = 1, cnt = 0; i < n; ++i) {
            if (boxes.charAt(i - 1) == '1') {
                ++cnt;
            }
            ans[i] = ans[i - 1] + cnt;
        }
        for (int i = n - 2, cnt = 0, s = 0; i >= 0; --i) {
            if (boxes.charAt(i + 1) == '1') {
                ++cnt;
            }
            s += cnt;
            ans[i] += s;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> minOperations(string boxes) {
        int n = boxes.size();
        int left[n];
        int right[n];
        memset(left, 0, sizeof left);
        memset(right, 0, sizeof right);
        for (int i = 1, cnt = 0; i < n; ++i) {
            cnt += boxes[i - 1] == '1';
            left[i] = left[i - 1] + cnt;
        }
        for (int i = n - 2, cnt = 0; ~i; --i) {
            cnt += boxes[i + 1] == '1';
            right[i] = right[i + 1] + cnt;
        }
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) ans[i] = left[i] + right[i];
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> minOperations(string boxes) {
        int n = boxes.size();
        vector<int> ans(n);
        for (int i = 1, cnt = 0; i < n; ++i) {
            cnt += boxes[i - 1] == '1';
            ans[i] = ans[i - 1] + cnt;
        }
        for (int i = n - 2, cnt = 0, s = 0; ~i; --i) {
            cnt += boxes[i + 1] == '1';
            s += cnt;
            ans[i] += s;
        }
        return ans;
    }
};
```

### **Go**

```go
func minOperations(boxes string) []int {
	n := len(boxes)
	left := make([]int, n)
	right := make([]int, n)
	for i, cnt := 1, 0; i < n; i++ {
		if boxes[i-1] == '1' {
			cnt++
		}
		left[i] = left[i-1] + cnt
	}
	for i, cnt := n-2, 0; i >= 0; i-- {
		if boxes[i+1] == '1' {
			cnt++
		}
		right[i] = right[i+1] + cnt
	}
	ans := make([]int, n)
	for i := range ans {
		ans[i] = left[i] + right[i]
	}
	return ans
}
```

```go
func minOperations(boxes string) []int {
	n := len(boxes)
	ans := make([]int, n)
	for i, cnt := 1, 0; i < n; i++ {
		if boxes[i-1] == '1' {
			cnt++
		}
		ans[i] = ans[i-1] + cnt
	}
	for i, cnt, s := n-2, 0, 0; i >= 0; i-- {
		if boxes[i+1] == '1' {
			cnt++
		}
		s += cnt
		ans[i] += s
	}
	return ans
}
```

### **TypeScript**

```ts
function minOperations(boxes: string): number[] {
    const n = boxes.length;
    const left = new Array(n).fill(0);
    const right = new Array(n).fill(0);
    for (let i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] == '1') {
            count++;
        }
        left[i] = left[i - 1] + count;
    }
    for (let i = n - 2, count = 0; i >= 0; i--) {
        if (boxes[i + 1] == '1') {
            count++;
        }
        right[i] = right[i + 1] + count;
    }
    return left.map((v, i) => v + right[i]);
}
```

```ts
function minOperations(boxes: string): number[] {
    const n = boxes.length;
    const ans = new Array(n).fill(0);
    for (let i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] === '1') {
            count++;
        }
        ans[i] = ans[i - 1] + count;
    }
    for (let i = n - 2, count = 0, sum = 0; i >= 0; i--) {
        if (boxes[i + 1] === '1') {
            count++;
        }
        sum += count;
        ans[i] += sum;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_operations(boxes: String) -> Vec<i32> {
        let s = boxes.as_bytes();
        let n = s.len();
        let mut left = vec![0; n];
        let mut right = vec![0; n];
        let mut count = 0;
        for i in 1..n {
            if s[i - 1] == b'1' {
                count += 1;
            }
            left[i] = left[i - 1] + count;
        }
        count = 0;
        for i in (0..n - 1).rev() {
            if s[i + 1] == b'1' {
                count += 1;
            }
            right[i] = right[i + 1] + count;
        }
        (0..n).into_iter().map(|i| left[i] + right[i]).collect()
    }
}
```

```rust
impl Solution {
    pub fn min_operations(boxes: String) -> Vec<i32> {
        let s = boxes.as_bytes();
        let n = s.len();
        let mut ans = vec![0; n];
        let mut count = 0;
        for i in 1..n {
            if s[i - 1] == b'1' {
                count += 1;
            }
            ans[i] = ans[i - 1] + count;
        }
        let mut sum = 0;
        count = 0;
        for i in (0..n - 1).rev() {
            if s[i + 1] == b'1' {
                count += 1;
            }
            sum += count;
            ans[i] += sum;
        }
        ans
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *minOperations(char *boxes, int *returnSize) {
    int n = strlen(boxes);
    int *left = malloc(sizeof(int) * n);
    int *right = malloc(sizeof(int) * n);
    memset(left, 0, sizeof(int) * n);
    memset(right, 0, sizeof(int) * n);
    for (int i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] == '1') {
            count++;
        }
        left[i] = left[i - 1] + count;
    }
    for (int i = n - 2, count = 0; i >= 0; i--) {
        if (boxes[i + 1] == '1') {
            count++;
        }
        right[i] = right[i + 1] + count;
    }
    int *ans = malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++) {
        ans[i] = left[i] + right[i];
    }
    free(left);
    free(right);
    *returnSize = n;
    return ans;
}
```

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *minOperations(char *boxes, int *returnSize) {
    int n = strlen(boxes);
    int *ans = malloc(sizeof(int) * n);
    memset(ans, 0, sizeof(int) * n);
    for (int i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] == '1') {
            count++;
        }
        ans[i] = ans[i - 1] + count;
    }
    for (int i = n - 2, count = 0, sum = 0; i >= 0; i--) {
        if (boxes[i + 1] == '1') {
            count++;
        }
        sum += count;
        ans[i] += sum;
    }
    *returnSize = n;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
