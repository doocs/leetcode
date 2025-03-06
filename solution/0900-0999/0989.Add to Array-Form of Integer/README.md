---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0989.Add%20to%20Array-Form%20of%20Integer/README.md
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [989. 数组形式的整数加法](https://leetcode.cn/problems/add-to-array-form-of-integer)

[English Version](/solution/0900-0999/0989.Add%20to%20Array-Form%20of%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>整数的 <strong>数组形式</strong> &nbsp;<code>num</code>&nbsp;是按照从左到右的顺序表示其数字的数组。</p>

<ul>
	<li>例如，对于 <code>num = 1321</code> ，数组形式是 <code>[1,3,2,1]</code> 。</li>
</ul>

<p>给定 <code>num</code> ，整数的 <strong>数组形式</strong> ，和整数 <code>k</code> ，返回 <em>整数 <code>num + k</code> 的 <strong>数组形式</strong></em> 。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = [1,2,0,0], k = 34
<strong>输出：</strong>[1,2,3,4]
<strong>解释：</strong>1200 + 34 = 1234
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = [2,7,4], k = 181
<strong>输出：</strong>[4,5,5]
<strong>解释：</strong>274 + 181 = 455
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = [2,1,5], k = 806
<strong>输出：</strong>[1,0,2,1]
<strong>解释：</strong>215 + 806 = 1021
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= num[i] &lt;= 9</code></li>
	<li><code>num</code>&nbsp;不包含任何前导零，除了零本身</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以从数组的最后一位开始，将数组的每一位与 $k$ 相加，然后将 $k$ 除以 $10$，并将余数作为当前位的值，将商作为进位。一直循环，直到数组遍历完并且 $k = 0$。最后将答案数组反转即可。

时间复杂度 $O(n)$，其中 $n$ 表示 $\textit{num}$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def addToArrayForm(self, num: List[int], k: int) -> List[int]:
        ans = []
        i = len(num) - 1
        while i >= 0 or k:
            k += 0 if i < 0 else num[i]
            k, x = divmod(k, 10)
            ans.append(x)
            i -= 1
        return ans[::-1]
```

#### Java

```java
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        for (int i = num.length - 1; i >= 0 || k > 0; --i) {
            k += (i >= 0 ? num[i] : 0);
            ans.add(k % 10);
            k /= 10;
        }
        Collections.reverse(ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> addToArrayForm(vector<int>& num, int k) {
        vector<int> ans;
        for (int i = num.size() - 1; i >= 0 || k > 0; --i) {
            k += (i >= 0 ? num[i] : 0);
            ans.push_back(k % 10);
            k /= 10;
        }
        ranges::reverse(ans);
        return ans;
    }
};
```

#### Go

```go
func addToArrayForm(num []int, k int) (ans []int) {
	for i := len(num) - 1; i >= 0 || k > 0; i-- {
		if i >= 0 {
			k += num[i]
		}
		ans = append(ans, k%10)
		k /= 10
	}
	slices.Reverse(ans)
	return
}
```

#### TypeScript

```ts
function addToArrayForm(num: number[], k: number): number[] {
    const ans: number[] = [];
    for (let i = num.length - 1; i >= 0 || k > 0; --i) {
        k += i >= 0 ? num[i] : 0;
        ans.push(k % 10);
        k = Math.floor(k / 10);
    }
    return ans.reverse();
}
```

#### Rust

```rust
impl Solution {
    pub fn add_to_array_form(num: Vec<i32>, k: i32) -> Vec<i32> {
        let mut ans = Vec::new();
        let mut k = k;
        let mut i = num.len() as i32 - 1;

        while i >= 0 || k > 0 {
            if i >= 0 {
                k += num[i as usize];
            }
            ans.push(k % 10);
            k /= 10;
            i -= 1;
        }

        ans.reverse();
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
