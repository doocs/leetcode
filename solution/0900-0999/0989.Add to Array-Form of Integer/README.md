# [989. 数组形式的整数加法](https://leetcode.cn/problems/add-to-array-form-of-integer)

[English Version](/solution/0900-0999/0989.Add%20to%20Array-Form%20of%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

数组从尾到头遍历，分别与 `k` 中的每一位相加，进位保存在 `carry` 中，不进位和则添加到结果列表中。最后逆序结果列表即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def addToArrayForm(self, num: List[int], k: int) -> List[int]:
        i, carry = len(num) - 1, 0
        ans = []
        while i >= 0 or k or carry:
            carry += (0 if i < 0 else num[i]) + (k % 10)
            carry, v = divmod(carry, 10)
            ans.append(v)
            k //= 10
            i -= 1
        return ans[::-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        int i = num.length - 1, carry = 0;
        LinkedList<Integer> ans = new LinkedList<>();
        while (i >= 0 || k > 0 || carry > 0) {
            carry += (i < 0 ? 0 : num[i--]) + k % 10;
            ans.addFirst(carry % 10);
            carry /= 10;
            k /= 10;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function addToArrayForm(num: number[], k: number): number[] {
    let arr2 = [...String(k)].map(Number);
    let ans = [];
    let sum = 0;
    while (num.length || arr2.length || sum) {
        let a = num.pop() || 0,
            b = arr2.pop() || 0;
        sum += a + b;
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans;
}
```

```ts
function addToArrayForm(num: number[], k: number): number[] {
    const n = num.length;
    const res = [];
    let sum = 0;
    for (let i = 0; i < n || sum !== 0 || k !== 0; i++) {
        sum += num[n - i - 1] ?? 0;
        sum += k % 10;
        res.push(sum % 10);
        k = Math.floor(k / 10);
        sum = Math.floor(sum / 10);
    }
    return res.reverse();
}
```

### **Rust**

```rust
impl Solution {
    pub fn add_to_array_form(num: Vec<i32>, mut k: i32) -> Vec<i32> {
        let n = num.len();
        let mut res = vec![];
        let mut i = 0;
        let mut sum = 0;
        while i < n || sum != 0 || k != 0 {
            sum += num.get(n - i - 1).unwrap_or(&0);
            sum += k % 10;
            res.push(sum % 10);

            i += 1;
            k /= 10;
            sum /= 10;
        }
        res.reverse();
        res
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> addToArrayForm(vector<int>& num, int k) {
        int i = num.size() - 1, carry = 0;
        vector<int> ans;
        for (; i >= 0 || k || carry; --i) {
            carry += (i < 0 ? 0 : num[i]) + k % 10;
            ans.push_back(carry % 10);
            carry /= 10;
            k /= 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

### **Go**

```go
func addToArrayForm(num []int, k int) []int {
	i, carry := len(num)-1, 0
	ans := []int{}
	for ; i >= 0 || k > 0 || carry > 0; i-- {
		if i >= 0 {
			carry += num[i]
		}
		carry += k % 10
		ans = append(ans, carry%10)
		carry /= 10
		k /= 10
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
