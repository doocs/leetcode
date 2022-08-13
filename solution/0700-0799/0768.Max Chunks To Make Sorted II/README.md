# [768. 最多能完成排序的块 II](https://leetcode.cn/problems/max-chunks-to-make-sorted-ii)

[English Version](/solution/0700-0799/0768.Max%20Chunks%20To%20Make%20Sorted%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><em>这个问题和&ldquo;最多能完成排序的块&rdquo;相似，但给定数组中的元素可以重复，输入数组最大长度为<code>2000</code>，其中的元素最大为<code>10**8</code>。</em></p>

<p><code>arr</code>是一个可能包含<strong>重复元素</strong>的整数数组，我们将这个数组分割成几个&ldquo;块&rdquo;，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。</p>

<p>我们最多能将数组分成多少块？</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> arr = [5,4,3,2,1]
<strong>输出:</strong> 1
<strong>解释:</strong>
将数组分成2块或者更多块，都无法得到所需的结果。
例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。 
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> arr = [2,1,3,4,4]
<strong>输出:</strong> 4
<strong>解释:</strong>
我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。 
</pre>

<p><strong>注意:</strong></p>

<ul>
	<li><code>arr</code>的长度在<code>[1, 2000]</code>之间。</li>
	<li><code>arr[i]</code>的大小在<code>[0, 10**8]</code>之间。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈**

根据题目，我们可以发现，从左到右，每个分块都有一个最大值，并且这些分块的最大值呈单调递增（非严格递增）。我们可以用一个栈来存储这些分块的最大值。最后得到的栈的大小，也就是题目所求的最多能完成排序的块。

时间复杂度 $O(n)$，其中 $n$ 表示 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
