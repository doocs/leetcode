# [768. Max Chunks To Make Sorted II](https://leetcode.com/problems/max-chunks-to-make-sorted-ii)

[中文文档](/solution/0700-0799/0768.Max%20Chunks%20To%20Make%20Sorted%20II/README.md)

## Description

<p>You are given an integer array <code>arr</code>.</p>

<p>We split <code>arr</code> into some number of <strong>chunks</strong> (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.</p>

<p>Return <em>the largest number of chunks we can make to sort the array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [5,4,3,2,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
Splitting into two or more chunks will not return the required result.
For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn&#39;t sorted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,1,3,4,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
We can split into two chunks, such as [2, 1], [3, 4, 4].
However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 2000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxChunksToSorted(self, arr: List[int]) -> int:
        stk = []
        for v in arr:
            if not stk or v >= stk[-1]:
                stk.append(v)
            else:
                mx = stk.pop()
                while stk and stk[-1] > v:
                    stk.pop()
                stk.append(mx)
        return len(stk)
```

### **Java**

```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (int v : arr) {
            if (stk.isEmpty() || stk.peek() <= v) {
                stk.push(v);
            } else {
                int mx = stk.pop();
                while (!stk.isEmpty() && stk.peek() > v) {
                    stk.pop();
                }
                stk.push(mx);
            }
        }
        return stk.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        stack<int> stk;
        for (int& v : arr) {
            if (stk.empty() || stk.top() <= v)
                stk.push(v);
            else {
                int mx = stk.top();
                stk.pop();
                while (!stk.empty() && stk.top() > v) stk.pop();
                stk.push(mx);
            }
        }
        return stk.size();
    }
};
```

### **Go**

```go
func maxChunksToSorted(arr []int) int {
	var stk []int
	for _, v := range arr {
		if len(stk) == 0 || stk[len(stk)-1] <= v {
			stk = append(stk, v)
		} else {
			mx := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			for len(stk) > 0 && stk[len(stk)-1] > v {
				stk = stk[:len(stk)-1]
			}
			stk = append(stk, mx)
		}
	}
	return len(stk)
}
```

### **TypeScript**

```ts
function maxChunksToSorted(arr: number[]): number {
    const stack = [];
    for (const num of arr) {
        if (stack.length !== 0 && num < stack[stack.length - 1]) {
            const max = stack.pop();
            while (stack.length !== 0 && num < stack[stack.length - 1]) {
                stack.pop();
            }
            stack.push(max);
        } else {
            stack.push(num);
        }
    }
    return stack.length;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_chunks_to_sorted(arr: Vec<i32>) -> i32 {
        let mut stack = vec![];
        for num in arr.iter() {
            if !stack.is_empty() && num < stack.last().unwrap() {
                let max = stack.pop().unwrap();
                while !stack.is_empty() && num < stack.last().unwrap() {
                    stack.pop();
                }
                stack.push(max)
            } else {
                stack.push(*num);
            }
        }
        stack.len() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
