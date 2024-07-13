---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3211.Generate%20Binary%20Strings%20Without%20Adjacent%20Zeros/README.md
tags:
    - 位运算
    - 递归
    - 字符串
---

<!-- problem:start -->

# [3211. 生成不含相邻零的二进制字符串](https://leetcode.cn/problems/generate-binary-strings-without-adjacent-zeros)

[English Version](/solution/3200-3299/3211.Generate%20Binary%20Strings%20Without%20Adjacent%20Zeros/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>n</code>。</p>

<p>如果一个二进制字符串 <code>x</code> 的所有长度为 2 的<span data-keyword="substring-nonempty">子字符串</span>中包含 <strong>至少</strong> 一个 <code>"1"</code>，则称 <code>x</code> 是一个<strong> 有效</strong> 字符串。</p>

<p>返回所有长度为 <code>n</code> 的<strong> 有效</strong> 字符串，可以以任意顺序排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">["010","011","101","110","111"]</span></p>

<p><strong>解释：</strong></p>

<p>长度为 3 的有效字符串有：<code>"010"</code>、<code>"011"</code>、<code>"101"</code>、<code>"110"</code> 和 <code>"111"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">["0","1"]</span></p>

<p><strong>解释：</strong></p>

<p>长度为 1 的有效字符串有：<code>"0"</code> 和 <code>"1"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 18</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们可以枚举长度为 $n$ 的二进制字符串的每个位置 $i$，然后对于每个位置 $i$，我们可以枚举其可以取的值 $j$，如果 $j$ 为 $0$，那么我们需要判断其前一个位置是否为 $1$，如果为 $1$，则可以继续递归下去，否则不合法，如果 $j$ 为 $1$，则直接递归下去。

时间复杂度 $O(n \times 2^n)$，其中 $n$ 为字符串长度。忽略答案数组的空间消耗，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validStrings(self, n: int) -> List[str]:
        def dfs(i: int):
            if i >= n:
                ans.append("".join(t))
                return
            for j in range(2):
                if (j == 0 and (i == 0 or t[i - 1] == "1")) or j == 1:
                    t.append(str(j))
                    dfs(i + 1)
                    t.pop()

        ans = []
        t = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private StringBuilder t = new StringBuilder();
    private int n;

    public List<String> validStrings(int n) {
        this.n = n;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= n) {
            ans.add(t.toString());
            return;
        }
        for (int j = 0; j < 2; ++j) {
            if ((j == 0 && (i == 0 || t.charAt(i - 1) == '1')) || j == 1) {
                t.append(j);
                dfs(i + 1);
                t.deleteCharAt(t.length() - 1);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> validStrings(int n) {
        vector<string> ans;
        string t;
        auto dfs = [&](auto&& dfs, int i) {
            if (i >= n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < 2; ++j) {
                if ((j == 0 && (i == 0 || t[i - 1] == '1')) || j == 1) {
                    t.push_back('0' + j);
                    dfs(dfs, i + 1);
                    t.pop_back();
                }
            }
        };
        dfs(dfs, 0);
        return ans;
    }
};
```

#### Go

```go
func validStrings(n int) (ans []string) {
	t := []byte{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans = append(ans, string(t))
			return
		}
		for j := 0; j < 2; j++ {
			if (j == 0 && (i == 0 || t[i-1] == '1')) || j == 1 {
				t = append(t, byte('0'+j))
				dfs(i + 1)
				t = t[:len(t)-1]
			}
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function validStrings(n: number): string[] {
    const ans: string[] = [];
    const t: string[] = [];
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < 2; ++j) {
            if ((j == 0 && (i == 0 || t[i - 1] == '1')) || j == 1) {
                t.push(j.toString());
                dfs(i + 1);
                t.pop();
            }
        }
    };
    dfs(0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
