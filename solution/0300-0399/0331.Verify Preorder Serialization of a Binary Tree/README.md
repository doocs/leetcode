---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0331.Verify%20Preorder%20Serialization%20of%20a%20Binary%20Tree/README.md
tags:
    - 栈
    - 树
    - 字符串
    - 二叉树
---

<!-- problem:start -->

# [331. 验证二叉树的前序序列化](https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree)

[English Version](/solution/0300-0399/0331.Verify%20Preorder%20Serialization%20of%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>序列化二叉树的一种方法是使用 <strong>前序遍历 </strong>。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 <code>#</code>。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0331.Verify%20Preorder%20Serialization%20of%20a%20Binary%20Tree/images/pre-tree.jpg" /></p>

<p>例如，上面的二叉树可以被序列化为字符串 <code>"9,3,4,#,#,1,#,#,2,#,6,#,#"</code>，其中 <code>#</code> 代表一个空节点。</p>

<p>给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。</p>

<p><strong>保证</strong> 每个以逗号分隔的字符或为一个整数或为一个表示 <code>null</code> 指针的 <code>'#'</code> 。</p>

<p>你可以认为输入格式总是有效的</p>

<ul>
	<li>例如它永远不会包含两个连续的逗号，比如&nbsp;<code>"1,,3"</code> 。</li>
</ul>

<p><strong>注意：</strong>不允许重建树。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>preorder = <code>"9,3,4,#,#,1,#,#,2,#,6,#,#"</code>
<strong>输出: </strong><code>true</code></pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入: </strong>preorder = <code>"1,#"</code>
<strong>输出: </strong><code>false</code>
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>preorder = <code>"9,#,#,1"</code>
<strong>输出: </strong><code>false</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 10<sup>4</sup></code></li>
	<li><code>preorder</code>&nbsp;由以逗号&nbsp;<code>“，”</code> 分隔的 <code>[0,100]</code> 范围内的整数和 <code>“#”</code> 组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈

我们将字符串 `preorder` 按逗号分割成数组，然后遍历数组，如果遇到了连续两个 `'#'`，并且第三个元素不是 `'#'`，那么就将这三个元素替换成一个 `'#'`，这个过程一直持续到数组遍历结束。

最后，判断数组长度是否为 $1$，且数组唯一的元素是否为 `'#'` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `preorder` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def isValidSerialization(self, preorder: str) -> bool:
        stk = []
        for c in preorder.split(","):
            stk.append(c)
            while len(stk) > 2 and stk[-1] == stk[-2] == "#" and stk[-3] != "#":
                stk = stk[:-3]
                stk.append("#")
        return len(stk) == 1 and stk[0] == "#"
```

```java
class Solution {
    public boolean isValidSerialization(String preorder) {
        List<String> stk = new ArrayList<>();
        for (String s : preorder.split(",")) {
            stk.add(s);
            while (stk.size() >= 3 && stk.get(stk.size() - 1).equals("#")
                && stk.get(stk.size() - 2).equals("#") && !stk.get(stk.size() - 3).equals("#")) {
                stk.remove(stk.size() - 1);
                stk.remove(stk.size() - 1);
                stk.remove(stk.size() - 1);
                stk.add("#");
            }
        }
        return stk.size() == 1 && stk.get(0).equals("#");
    }
}
```

```cpp
class Solution {
public:
    bool isValidSerialization(string preorder) {
        vector<string> stk;
        stringstream ss(preorder);
        string s;
        while (getline(ss, s, ',')) {
            stk.push_back(s);
            while (stk.size() >= 3 && stk[stk.size() - 1] == "#" && stk[stk.size() - 2] == "#" && stk[stk.size() - 3] != "#") {
                stk.pop_back();
                stk.pop_back();
                stk.pop_back();
                stk.push_back("#");
            }
        }
        return stk.size() == 1 && stk[0] == "#";
    }
};
```

```go
func isValidSerialization(preorder string) bool {
	stk := []string{}
	for _, s := range strings.Split(preorder, ",") {
		stk = append(stk, s)
		for len(stk) >= 3 && stk[len(stk)-1] == "#" && stk[len(stk)-2] == "#" && stk[len(stk)-3] != "#" {
			stk = stk[:len(stk)-3]
			stk = append(stk, "#")
		}
	}
	return len(stk) == 1 && stk[0] == "#"
}
```

```ts
function isValidSerialization(preorder: string): boolean {
    const stk: string[] = [];
    for (const s of preorder.split(',')) {
        stk.push(s);
        while (stk.length >= 3 && stk.at(-1) === '#' && stk.at(-2) === '#' && stk.at(-3) !== '#') {
            stk.splice(-3, 3, '#');
        }
    }
    return stk.length === 1 && stk[0] === '#';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
