# [769. 最多能完成排序的块](https://leetcode.cn/problems/max-chunks-to-make-sorted)

[English Version](/solution/0700-0799/0769.Max%20Chunks%20To%20Make%20Sorted/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为 <code>n</code> 的整数数组 <code>arr</code> ，它表示在 <code>[0, n - 1]</code> 范围内的整数的排列。</p>

<p>我们将 <code>arr</code> 分割成若干 <strong>块</strong> (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。</p>

<p>返回数组能分成的最多块数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> arr = [4,3,2,1,0]
<strong>输出:</strong> 1
<strong>解释:</strong>
将数组分成2块或者更多块，都无法得到所需的结果。
例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> arr = [1,0,2,3,4]
<strong>输出:</strong> 4
<strong>解释:</strong>
我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
对每个块单独排序后，结果为 [0, 1], [2], [3], [4]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>0 &lt;= arr[i] &lt; n</code></li>
	<li><code>arr</code>&nbsp;中每个元素都 <strong>不同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 一次遍历**

由于 $arr$ 是 $[0,..,n-1]$ 的一个排列，若已遍历过的数中的最大值 $mx$ 与当前遍历到的下标 $i$ 相等，说明可以进行一次分割，累加答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $arr$ 的长度。

**方法二：单调栈**

方法一的解法有一定的局限性，若数组中存在重复元素，就无法得到正确的答案。

根据题目，我们可以发现，从左到右，每个分块都有一个最大值，并且这些分块的最大值呈单调递增。我们可以用一个栈来存储这些分块的最大值。最后得到的栈的大小，也就是题目所求的最多能完成排序的块。

以上这种解法，不仅可以解决本题，也可以解决 [768. 最多能完成排序的块 II](/solution/0700-0799/0768.Max%20Chunks%20To%20Make%20Sorted%20II/README.md) 这道困难题。大家可以自行尝试。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxChunksToSorted(self, arr: List[int]) -> int:
        mx = ans = 0
        for i, v in enumerate(arr):
            mx = max(mx, v)
            if i == mx:
                ans += 1
        return ans
```

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
        int ans = 0, mx = 0;
        for (int i = 0; i < arr.length; ++i) {
            mx = Math.max(mx, arr[i]);
            if (i == mx) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (int v : arr) {
            if (stk.isEmpty() || v >= stk.peek()) {
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
        int ans = 0, mx = 0;
        for (int i = 0; i < arr.size(); ++i) {
            mx = max(mx, arr[i]);
            ans += i == mx;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        stack<int> stk;
        for (int v : arr) {
            if (stk.empty() || v >= stk.top()) {
                stk.push(v);
            } else {
                int mx = stk.top();
                stk.pop();
                while (!stk.empty() && stk.top() > v) {
                    stk.pop();
                }
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
	ans, mx := 0, 0
	for i, v := range arr {
		mx = max(mx, v)
		if i == mx {
			ans++
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

```go
func maxChunksToSorted(arr []int) int {
	stk := []int{}
	for _, v := range arr {
		if len(stk) == 0 || v >= stk[len(stk)-1] {
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

### **C**

```c
#define max(a,b) (((a) > (b)) ? (a) : (b))

int maxChunksToSorted(int *arr, int arrSize) {
    int res = 0;
    int mx = -1;
    for (int i = 0; i < arrSize; i++) {
        mx = max(mx, arr[i]);
        if (mx == i) {
            res++;
        }
    }
    return res;
}
```

### **TypeScript**

```ts
function maxChunksToSorted(arr: number[]): number {
    const n = arr.length;
    let ans = 0;
    let max = 0;
    for (let i = 0; i < n; i++) {
        max = Math.max(arr[i], max);
        if (max == i) {
            ans++;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_chunks_to_sorted(arr: Vec<i32>) -> i32 {
        let mut res = 0;
        let mut max = 0;
        for i in 0..arr.len() {
            max = max.max(arr[i]);
            if max == i as i32 {
                res += 1;
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
