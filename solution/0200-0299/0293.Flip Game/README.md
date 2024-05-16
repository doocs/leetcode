---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0293.Flip%20Game/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [293. 翻转游戏 🔒](https://leetcode.cn/problems/flip-game)

[English Version](/solution/0200-0299/0293.Flip%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：</p>

<p>给你一个字符串 <code>currentState</code> ，其中只含 <code>'+'</code> 和 <code>'-'</code> 。你和朋友轮流将 <strong>连续 </strong>的两个 <code>"++"</code> 反转成 <code>"--"</code> 。当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。</p>

<p>计算并返回 <strong>一次有效操作</strong> 后，字符串 <code>currentState</code> 所有的可能状态，返回结果可以按 <strong>任意顺序</strong> 排列。如果不存在可能的有效操作，请返回一个空列表 <code>[]</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>currentState = "++++"
<strong>输出：</strong>["--++","+--+","++--"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>currentState = "+"
<strong>输出：</strong>[]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= currentState.length <= 500</code></li>
	<li><code>currentState[i]</code> 不是 <code>'+'</code> 就是 <code>'-'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历 + 模拟

我们遍历字符串，如果当前字符和下一个字符都是 `+`，那么我们就将这两个字符变成 `-`，然后将结果加入到结果数组中，再将这两个字符变回 `+`。

遍历结束后，返回结果数组即可。

时间复杂度 $O(n^2)$，其中 $n$ 是字符串长度。忽略答案数组的空间复杂度，空间复杂度 $O(n)$ 或 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def generatePossibleNextMoves(self, currentState: str) -> List[str]:
        s = list(currentState)
        ans = []
        for i, (a, b) in enumerate(pairwise(s)):
            if a == b == "+":
                s[i] = s[i + 1] = "-"
                ans.append("".join(s))
                s[i] = s[i + 1] = "+"
        return ans
```

```java
class Solution {
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> ans = new ArrayList<>();
        char[] s = currentState.toCharArray();
        for (int i = 0; i < s.length - 1; ++i) {
            if (s[i] == '+' && s[i + 1] == '+') {
                s[i] = '-';
                s[i + 1] = '-';
                ans.add(new String(s));
                s[i] = '+';
                s[i + 1] = '+';
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> generatePossibleNextMoves(string s) {
        vector<string> ans;
        for (int i = 0; i < s.size() - 1; ++i) {
            if (s[i] == '+' && s[i + 1] == '+') {
                s[i] = s[i + 1] = '-';
                ans.emplace_back(s);
                s[i] = s[i + 1] = '+';
            }
        }
        return ans;
    }
};
```

```go
func generatePossibleNextMoves(currentState string) (ans []string) {
	s := []byte(currentState)
	for i := 0; i < len(s)-1; i++ {
		if s[i] == '+' && s[i+1] == '+' {
			s[i], s[i+1] = '-', '-'
			ans = append(ans, string(s))
			s[i], s[i+1] = '+', '+'
		}
	}
	return
}
```

```ts
function generatePossibleNextMoves(currentState: string): string[] {
    const s = currentState.split('');
    const ans: string[] = [];
    for (let i = 0; i < s.length - 1; ++i) {
        if (s[i] === '+' && s[i + 1] === '+') {
            s[i] = s[i + 1] = '-';
            ans.push(s.join(''));
            s[i] = s[i + 1] = '+';
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
