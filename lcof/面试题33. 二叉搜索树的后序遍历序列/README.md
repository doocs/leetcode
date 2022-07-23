# [面试题 33. 二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>。假设输入的数组的任意两个数字都互不相同。</p>

<p>&nbsp;</p>

<p>参考以下这颗二叉搜索树：</p>

<pre>     5
    / \
   2   6
  / \
 1   3</pre>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>[1,6,3,2,5]
<strong>输出: </strong>false</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入: </strong>[1,3,2,6,5]
<strong>输出: </strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>数组长度 &lt;= 1000</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二叉搜索树的后序遍历序列是 `[左子树, 右子树, 根结点]`，且左子树结点值均小于根结点，右子树结点值均大于根结点，递归判断即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        def dfs(postorder):
            if not postorder:
                return True
            v = postorder[-1]
            i = 0
            while i < len(postorder) and postorder[i] < v:
                i += 1
            if any(x < v for x in postorder[i:]):
                return False
            return dfs(postorder[:i]) and dfs(postorder[i:-1])

        return dfs(postorder)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length < 2) {
            return true;
        }
        return dfs(postorder, 0, postorder.length);
    }

    private boolean dfs(int[] postorder, int i, int n) {
        if (n <= 0) {
            return true;
        }
        int v = postorder[i + n - 1];
        int j = i;
        while (j < i + n && postorder[j] < v) {
            ++j;
        }
        for (int k = j; k < i + n; ++k) {
            if (postorder[k] < v) {
                return false;
            }
        }
        return dfs(postorder, i, j - i) && dfs(postorder, j, n + i - j - 1);
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} postorder
 * @return {boolean}
 */
var verifyPostorder = function (postorder) {
    if (postorder.length < 2) return true;
    function dfs(i, n) {
        if (n <= 0) return true;
        const v = postorder[i + n - 1];
        let j = i;
        while (j < i + n && postorder[j] < v) ++j;
        for (let k = j; k < i + n; ++k) {
            if (postorder[k] < v) {
                return false;
            }
        }
        return dfs(i, j - i) && dfs(j, n + i - j - 1);
    }
    return dfs(0, postorder.length);
};
```

### **Go**

```go
func verifyPostorder(postorder []int) bool {
	if len(postorder) < 2 {
		return true
	}
	var dfs func(i, n int) bool
	dfs = func(i, n int) bool {
		if n <= 0 {
			return true
		}
		v := postorder[i+n-1]
		j := i
		for j < i+n && postorder[j] < v {
			j++
		}
		for k := j; k < i+n; k++ {
			if postorder[k] < v {
				return false
			}
		}
		return dfs(i, j-i) && dfs(j, n+i-j-1)
	}
	return dfs(0, len(postorder))
}
```

### **C++**

```cpp
class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        if (postorder.size() < 2) return true;
        return dfs(postorder, 0, postorder.size());
    }

    bool dfs(vector<int>& postorder, int i, int n) {
        if (n <= 0) return 1;
        int v = postorder[i + n - 1];
        int j = i;
        while (j < i + n && postorder[j] < v) ++j;
        for (int k = j; k < i + n; ++k)
            if (postorder[k] < v)
                return 0;
        return dfs(postorder, i, j - i) && dfs(postorder, j, n + i - j - 1);
    }
};
```

### **TypeScript**

```ts
function verifyPostorder(postorder: number[]): boolean {
    const dfs = (start: number, end: number, maxVal: number) => {
        if (start > end) {
            return true;
        }
        const rootVal = postorder[end];
        for (let i = end; i >= start; i--) {
            const val = postorder[i];
            if (val > maxVal) {
                return false;
            }
            if (val < rootVal) {
                return dfs(start, i, rootVal) && dfs(i + 1, end - 1, maxVal);
            }
        }
        return dfs(start, end - 1, maxVal);
    };
    const n = postorder.length;
    return dfs(0, n - 1, Infinity);
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(start: usize, end: usize, max_val: i32, postorder: &Vec<i32>) -> bool {
        if start >= end {
            return true;
        }
        let root_val = postorder[end - 1];
        for i in (start..end).rev() {
            let val = postorder[i];
            if val > max_val {
                return false;
            }
            if val < root_val {
                return Self::dfs(start, i, root_val, postorder)
                    && Self::dfs(i + 1, end - 1, max_val, postorder);
            }
        }
        Self::dfs(start, end - 1, max_val, postorder)
    }

    pub fn verify_postorder(postorder: Vec<i32>) -> bool {
        Self::dfs(0, postorder.len(), i32::MAX, &postorder)
    }
}
```

### **C#**

```cs
public class Solution {
    public bool VerifyPostorder(int[] postorder) {
        if (postorder.Length == 0) {
            return true;
        }
        var root = postorder[^1];
        int n = postorder.Length, i = 0;
        while (i < n && postorder[i] < root) {
            i += 1;
        }
        for (int j = i; j < n - 1; j++) {
            if (postorder[j] < root) {
                return false;
            }
        }
        return VerifyPostorder(postorder[..i]) && VerifyPostorder(postorder[i..^1]);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
