---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0989.Add%20to%20Array-Form%20of%20Integer/README_EN.md
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [989. Add to Array-Form of Integer](https://leetcode.com/problems/add-to-array-form-of-integer)

[中文文档](/solution/0900-0999/0989.Add%20to%20Array-Form%20of%20Integer/README.md)

## Description

<!-- description:start -->

<p>The <strong>array-form</strong> of an integer <code>num</code> is an array representing its digits in left to right order.</p>

<ul>
	<li>For example, for <code>num = 1321</code>, the array form is <code>[1,3,2,1]</code>.</li>
</ul>

<p>Given <code>num</code>, the <strong>array-form</strong> of an integer, and an integer <code>k</code>, return <em>the <strong>array-form</strong> of the integer</em> <code>num + k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = [1,2,0,0], k = 34
<strong>Output:</strong> [1,2,3,4]
<strong>Explanation:</strong> 1200 + 34 = 1234
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = [2,7,4], k = 181
<strong>Output:</strong> [4,5,5]
<strong>Explanation:</strong> 274 + 181 = 455
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = [2,1,5], k = 806
<strong>Output:</strong> [1,0,2,1]
<strong>Explanation:</strong> 215 + 806 = 1021
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= num[i] &lt;= 9</code></li>
	<li><code>num</code> does not contain any leading zeros except for the zero itself.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can start from the last digit of the array and add each digit of the array to $k$. Then, divide $k$ by $10$, and use the remainder as the current digit's value, with the quotient as the carry. Continue this process until the array is fully traversed and $k = 0$. Finally, reverse the answer array.

The time complexity is $O(n)$, where $n$ is the length of $\textit{num}$. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

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
