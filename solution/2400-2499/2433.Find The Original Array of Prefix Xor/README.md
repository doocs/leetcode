# [2433. 找出前缀异或的原始数组](https://leetcode.cn/problems/find-the-original-array-of-prefix-xor)

[English Version](/solution/2400-2499/2433.Find%20The%20Original%20Array%20of%20Prefix%20Xor/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的 <strong>整数</strong> 数组 <code>pref</code> 。找出并返回满足下述条件且长度为 <code>n</code> 的数组<em> </em><code>arr</code> ：</p>

<ul>
	<li><code>pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]</code>.</li>
</ul>

<p>注意 <code>^</code> 表示 <strong>按位异或</strong>（bitwise-xor）运算。</p>

<p>可以证明答案是 <strong>唯一</strong> 的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>pref = [5,2,0,3,1]
<strong>输出：</strong>[5,7,2,3,2]
<strong>解释：</strong>从数组 [5,7,2,3,2] 可以得到如下结果：
- pref[0] = 5
- pref[1] = 5 ^ 7 = 2
- pref[2] = 5 ^ 7 ^ 2 = 0
- pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3
- pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>pref = [13]
<strong>输出：</strong>[13]
<strong>解释：</strong>pref[0] = arr[0] = 13
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pref.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= pref[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

根据题意，我们有式子一：

$$
pref[i]=arr[0] \oplus arr[1] \oplus \cdots \oplus arr[i]
$$

所以，也就有式子二：

$$
pref[i-1]=arr[0] \oplus arr[1] \oplus \cdots \oplus arr[i-1]
$$

我们将式子一二进行异或运算，得到：

$$
pref[i] \oplus pref[i-1]=arr[i]
$$

即答案数组的每一项都是前缀异或数组的相邻两项进行异或运算得到的。

时间复杂度 $O(n)$，忽略答案的空间消耗，空间复杂度 $O(1)$。其中 $n$ 为前缀异或数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findArray(self, pref: List[int]) -> List[int]:
        return [a ^ b for a, b in pairwise([0] + pref)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] ans = new int[n];
        ans[0] = pref[0];
        for (int i = 1; i < n; ++i) {
            ans[i] = pref[i - 1] ^ pref[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findArray(vector<int>& pref) {
        int n = pref.size();
        vector<int> ans = {pref[0]};
        for (int i = 1; i < n; ++i) {
            ans.push_back(pref[i - 1] ^ pref[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func findArray(pref []int) []int {
	n := len(pref)
	ans := []int{pref[0]}
	for i := 1; i < n; i++ {
		ans = append(ans, pref[i-1]^pref[i])
	}
	return ans
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *findArray(int *pref, int prefSize, int *returnSize) {
    int *res = (int *) malloc(sizeof(int) * prefSize);
    res[0] = pref[0];
    for (int i = 1; i < prefSize; i++) {
        res[i] = pref[i - 1] ^ pref[i];
    }
    *returnSize = prefSize;
    return res;
}
```

### **TypeScript**

```ts
function findArray(pref: number[]): number[] {
    let ans = pref.slice();
    for (let i = 1; i < pref.length; i++) {
        ans[i] = pref[i - 1] ^ pref[i];
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_array(pref: Vec<i32>) -> Vec<i32> {
        let n = pref.len();
        let mut res = vec![0; n];
        res[0] = pref[0];
        for i in 1..n {
            res[i] = pref[i] ^ pref[i - 1];
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
