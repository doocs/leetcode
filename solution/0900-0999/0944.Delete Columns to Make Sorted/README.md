# [944. 删列造序](https://leetcode.cn/problems/delete-columns-to-make-sorted)

[English Version](/solution/0900-0999/0944.Delete%20Columns%20to%20Make%20Sorted/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你由 <code>n</code> 个小写字母字符串组成的数组 <code>strs</code>，其中每个字符串长度相等。</p>

<p>这些字符串可以每个一行，排成一个网格。例如，<code>strs = ["abc", "bce", "cae"]</code> 可以排列为：</p>

<pre>
abc
bce
cae</pre>

<p>你需要找出并删除 <strong>不是按字典序升序排列的</strong> 列。在上面的例子（下标从 0 开始）中，列 0（<code>'a'</code>, <code>'b'</code>, <code>'c'</code>）和列 2（<code>'c'</code>, <code>'e'</code>, <code>'e'</code>）都是按升序排列的，而列 1（<code>'b'</code>, <code>'c'</code>, <code>'a'</code>）不是，所以要删除列 1 。</p>

<p>返回你需要删除的列数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["cba","daf","ghi"]
<strong>输出：</strong>1
<strong>解释：</strong>网格示意如下：
  cba
  daf
  ghi
列 0 和列 2 按升序排列，但列 1 不是，所以只需要删除列 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["a","b"]
<strong>输出：</strong>0
<strong>解释：</strong>网格示意如下：
  a
  b
只有列 0 这一列，且已经按升序排列，所以不用删除任何列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>strs = ["zyx","wvu","tsr"]
<strong>输出：</strong>3
<strong>解释：</strong>网格示意如下：
  zyx
  wvu
  tsr
所有 3 列都是非升序排列的，所以都要删除。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == strs.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>1 <= strs[i].length <= 1000</code></li>
	<li><code>strs[i]</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        m, n = len(strs[0]), len(strs)
        ans = 0
        for j in range(m):
            for i in range(1, n):
                if strs[i][j] < strs[i - 1][j]:
                    ans += 1
                    break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs[0].length(), n = strs.length;
        int ans = 0;
        for (int j = 0; j < m; ++j) {
            for (int i = 1; i < n; ++i) {
                if (strs[i].charAt(j) < strs[i - 1].charAt(j)) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs.size();
        int m = strs[0].size();
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                if (strs[j][i] > strs[j + 1][i]) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn min_deletion_size(strs: Vec<String>) -> i32 {
        let n = strs.len();
        let m = strs[0].len();
        let mut res = 0;
        for i in 0..m {
            for j in 1..n {
                if strs[j - 1].as_bytes()[i] > strs[j].as_bytes()[i] {
                    res += 1;
                    break;
                }
            }
        }
        res
    }
}
```

### **Go**

```go
func minDeletionSize(strs []string) int {
	m, n := len(strs[0]), len(strs)
	ans := 0
	for j := 0; j < m; j++ {
		for i := 1; i < n; i++ {
			if strs[i][j] < strs[i-1][j] {
				ans++
				break
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
