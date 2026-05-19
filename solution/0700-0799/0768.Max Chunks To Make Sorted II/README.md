---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0768.Max%20Chunks%20To%20Make%20Sorted%20II/README.md
tags:
    - 栈
    - 贪心
    - 数组
    - 排序
    - 单调栈
---

<!-- problem:start -->

# [768. 最多能完成排序的块 II](https://leetcode.cn/problems/max-chunks-to-make-sorted-ii)

[English Version](/solution/0700-0799/0768.Max%20Chunks%20To%20Make%20Sorted%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>arr</code> 。</p>

<p>将 <code>arr</code> 分割成若干 <strong>块</strong> ，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。</p>

<p>返回能将数组分成的最多块数？</p>
&nbsp;

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [5,4,3,2,1]
<strong>输出：</strong>1
<strong>解释：</strong>
将数组分成2块或者更多块，都无法得到所需的结果。 
例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。 
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,1,3,4,4]
<strong>输出：</strong>4
<strong>解释：</strong>
可以把它分成两块，例如 [2, 1], [3, 4, 4]。 
然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 2000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

根据题目，我们可以发现，从左到右，每个分块都有一个最大值，并且这些分块的最大值呈单调递增（非严格递增）。我们可以用一个栈来存储这些分块的最大值。最后得到的栈的大小，也就是题目所求的最多能完成排序的块。

时间复杂度 $O(n)$，其中 $n$ 表示 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

```ts
function maxChunksToSorted(arr: number[]): number {
    const stk: number[] = [];
    for (let v of arr) {
        if (stk.length === 0 || v >= stk[stk.length - 1]) {
            stk.push(v);
        } else {
            let mx = stk.pop()!;
            while (stk.length > 0 && stk[stk.length - 1] > v) {
                stk.pop();
            }
            stk.push(mx);
        }
    }
    return stk.length;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_chunks_to_sorted(arr: Vec<i32>) -> i32 {
        let mut stk = Vec::new();
        for &v in arr.iter() {
            if stk.is_empty() || v >= *stk.last().unwrap() {
                stk.push(v);
            } else {
                let mut mx = stk.pop().unwrap();
                while let Some(&top) = stk.last() {
                    if top > v {
                        stk.pop();
                    } else {
                        break;
                    }
                }
                stk.push(mx);
            }
        }
        stk.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：前缀最大值 + 后缀最小值
我们希望将原始长度为 $n$ 的数组划分为若干个区间，使得每个区间分别排序后，整体数组仍然有序。

考虑相邻两个区间：

* 左侧区间 `left_chunk`
* 右侧区间 `right_chunk`

若满足：

`max(left_chunk)` <= `min(right_chunk)`

则说明：
* 左侧区间中的任意元素都不会大于右侧区间中的任意元素
* 因此两个区间分别排序后，可以直接拼接成一个有序数组

于是，对于每个满足：

$$
1 \le i < n
$$

的索引 $i$，我们检查：

$$
\max(arr[:i]) \le \min(arr[i:])
$$

是否成立。

若成立，则说明索引 $i$ 可以作为一个合法分割点。

---

为了快速计算上述条件，我们预处理：

* `prefix_maxs[j]`表示：

$$
\max(arr[:j + 1])
$$

即前缀最大值。

* `suffix_min[j]`表示：

$$
\min(arr[j:])
$$

即后缀最小值。

接下来：

1. 从左到右遍历数组，计算所有前缀最大值
2. 从右到左遍历数组，计算所有后缀最小值
3. 对于每个索引 $i$，若：

`prefix_maxs[i - 1]` <= `suffix_min[i]`

成立，则说明：

* 左侧所有元素均不大于右侧所有元素
* 因此可以在索引 $i$ 处分割数组

最终统计所有合法分割点数量即可。

注意：

即使数组严格递减，整个数组本身仍然可以视为一个合法区间。

因此数组能分成的最多块数为 所有合法分割点数量 + $1$。

时间复杂度为 $O(n)$，空间复杂度为 $O(n)$，其中 $n$ 表示 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxChunksToSorted(self, arr: list[int]) -> int:
        prefix_maxs = []  # Max of each arr[:i + 1] where 0 <= i < len(arr).

        for num in arr:
            if not prefix_maxs:
                prefix_maxs.append(num)
                continue

            prefix_maxs.append(max(num, prefix_maxs[-1]))

        max_chunks = 1  # Base case.
        suffix_min = arr[-1]  # Min of arr[i:] where 0 <= i < len(arr).

        for idx in range(len(arr) - 1, 0, -1):
            if arr[idx] < suffix_min:
                suffix_min = arr[idx]

            if prefix_maxs[idx - 1] <= suffix_min:
                max_chunks += 1

        return max_chunks
```

#### C++

```cpp
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        vector<int> prefixMaxs; // Max of each arr[:i + 1]. 0 <= i < arr.size().

        for (const auto& num : arr) {
            if (prefixMaxs.empty()) {
                prefixMaxs.push_back(num);
                continue;
            }

            prefixMaxs.push_back(max(prefixMaxs.back(), num));
        }

        int maxChunks = 1; // Base case.
        int suffixMin = arr.back(); // Min of arr[i:]. 0 <= i < arr.size().

        for (int idx = arr.size() - 1; idx >= 1; idx--) {
            if (arr[idx] < suffixMin)
                suffixMin = arr[idx];

            if (prefixMaxs[idx - 1] <= suffixMin)
                maxChunks++;
        }

        return maxChunks;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
