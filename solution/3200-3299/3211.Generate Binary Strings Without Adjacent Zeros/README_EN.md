---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3211.Generate%20Binary%20Strings%20Without%20Adjacent%20Zeros/README_EN.md
rating: 1352
source: Weekly Contest 405 Q2
tags:
    - Bit Manipulation
    - Recursion
    - String
---

<!-- problem:start -->

# [3211. Generate Binary Strings Without Adjacent Zeros](https://leetcode.com/problems/generate-binary-strings-without-adjacent-zeros)

[中文文档](/solution/3200-3299/3211.Generate%20Binary%20Strings%20Without%20Adjacent%20Zeros/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code>.</p>

<p>A binary string <code>x</code> is <strong>valid</strong> if all <span data-keyword="substring-nonempty">substrings</span> of <code>x</code> of length 2 contain <strong>at least</strong> one <code>&quot;1&quot;</code>.</p>

<p>Return all <strong>valid</strong> strings with length <code>n</code><strong>, </strong>in <em>any</em> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;010&quot;,&quot;011&quot;,&quot;101&quot;,&quot;110&quot;,&quot;111&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid strings of length 3 are: <code>&quot;010&quot;</code>, <code>&quot;011&quot;</code>, <code>&quot;101&quot;</code>, <code>&quot;110&quot;</code>, and <code>&quot;111&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;0&quot;,&quot;1&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid strings of length 1 are: <code>&quot;0&quot;</code> and <code>&quot;1&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 18</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We can enumerate each position $i$ of a binary string of length $n$, and for each position $i$, we can enumerate the possible value $j$ it can take. If $j$ is $0$, then we need to check if its previous position is $1$. If it is $1$, we can continue to recurse further; otherwise, it is invalid. If $j$ is $1$, then we directly recurse further.

The time complexity is $O(n \times 2^n)$, where $n$ is the length of the string. Ignoring the space consumption of the answer array, the space complexity is $O(n)$.

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
