# [3093. 最长公共后缀查询](https://leetcode.cn/problems/longest-common-suffix-queries)

[English Version](/solution/3000-3099/3093.Longest%20Common%20Suffix%20Queries/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串数组&nbsp;<code>wordsContainer</code> 和&nbsp;<code>wordsQuery</code>&nbsp;。</p>

<p>对于每个&nbsp;<code>wordsQuery[i]</code>&nbsp;，你需要从&nbsp;<code>wordsContainer</code>&nbsp;中找到一个与&nbsp;<code>wordsQuery[i]</code>&nbsp;有&nbsp;<strong>最长公共后缀</strong>&nbsp;的字符串。如果 <code>wordsContainer</code>&nbsp;中有两个或者更多字符串有最长公共后缀，那么答案为长度<strong>&nbsp;最短</strong>&nbsp;的。如果有超过两个字符串有&nbsp;<strong>相同</strong>&nbsp;最短长度，那么答案为它们在&nbsp;<code>wordsContainer</code>&nbsp;中出现&nbsp;<strong>更早</strong>&nbsp;的一个。</p>

<p>请你返回一个整数数组<em>&nbsp;</em><code>ans</code>&nbsp;，其中<em>&nbsp;</em><code>ans[i]</code>是<em>&nbsp;</em><code>wordsContainer</code>中与&nbsp;<code>wordsQuery[i]</code>&nbsp;有&nbsp;<strong>最长公共后缀</strong>&nbsp;字符串的下标。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]</span></p>

<p><span class="example-io"><b>输出：</b>[1,1,1]</span></p>

<p><strong>解释：</strong></p>

<p>我们分别来看每一个&nbsp;<code>wordsQuery[i]</code>&nbsp;：</p>

<ul>
	<li>对于&nbsp;<code>wordsQuery[0] = "cd"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中有最长公共后缀&nbsp;<code>"cd"</code>&nbsp;的字符串下标分别为&nbsp;0 ，1 和&nbsp;2 。这些字符串中，答案是下标为 1 的字符串，因为它的长度为 3 ，是最短的字符串。</li>
	<li>对于&nbsp;<code>wordsQuery[1] = "bcd"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中有最长公共后缀&nbsp;<code>"bcd"</code>&nbsp;的字符串下标分别为 0 ，1 和 2 。这些字符串中，答案是下标为 1 的字符串，因为它的长度为 3 ，是最短的字符串。</li>
	<li>对于&nbsp;<code>wordsQuery[2] = "xyz"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中没有字符串跟它有公共后缀，所以最长公共后缀为&nbsp;<code>""</code>&nbsp;，下标为&nbsp;0 ，1 和 2 的字符串都得到这一公共后缀。这些字符串中，&nbsp;答案是下标为 1 的字符串，因为它的长度为 3 ，是最短的字符串。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]</span></p>

<p><span class="example-io"><b>输出：</b>[2,0,2]</span></p>

<p><strong>解释：</strong></p>

<p>我们分别来看每一个&nbsp;<code>wordsQuery[i]</code>&nbsp;：</p>

<ul>
	<li>对于&nbsp;<code>wordsQuery[0] = "gh"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中有最长公共后缀&nbsp;<code>"gh"</code>&nbsp;的字符串下标分别为 0 ，1 和 2 。这些字符串中，答案是下标为 2 的字符串，因为它的长度为 6 ，是最短的字符串。</li>
	<li>对于&nbsp;<code>wordsQuery[1] = "acbfgh"</code>&nbsp;，只有下标为 0 的字符串有最长公共后缀&nbsp;<code>"fgh"</code>&nbsp;。所以尽管下标为 2 的字符串是最短的字符串，但答案是 0 。</li>
	<li>对于&nbsp;<code>wordsQuery[2] = "acbfegh"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中有最长公共后缀&nbsp;<code>"gh"</code>&nbsp;的字符串下标分别为 0 ，1 和 2 。这些字符串中，答案是下标为 2 的字符串，因为它的长度为 6 ，是最短的字符串。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= wordsContainer.length, wordsQuery.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsContainer[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>1 &lt;= wordsQuery[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>wordsContainer[i]</code>&nbsp;只包含小写英文字母。</li>
	<li><code>wordsQuery[i]</code>&nbsp;只包含小写英文字母。</li>
	<li><code>wordsContainer[i].length</code>&nbsp;的和至多为&nbsp;<code>5 * 10<sup>5</sup></code>&nbsp;。</li>
	<li><code>wordsQuery[i].length</code>&nbsp;的和至多为&nbsp;<code>5 * 10<sup>5</sup></code>&nbsp;。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Trie:
    __slots__ = ("children", "length", "idx")

    def __init__(self, length=inf, idx=inf):
        self.children = [None] * 26
        self.length = length
        self.idx = idx

    def insert(self, w: str, i: int):
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if not node.children[idx]:
                node.children[idx] = Trie()
            node = node.children[idx]
            if node.length > len(w):
                node.length = len(w)
                node.idx = i
            elif node.length == len(w):
                node.idx = min(node.idx, i)

    def query(self, w: str):
        node = self
        ans = node.idx
        for c in w:
            idx = ord(c) - ord("a")
            if not node.children[idx]:
                break
            node = node.children[idx]
            ans = node.idx
        return ans


class Solution:
    def stringIndices(
        self, wordsContainer: List[str], wordsQuery: List[str]
    ) -> List[int]:
        k = 0
        for i, w in enumerate(wordsContainer):
            if len(w) < len(wordsContainer[k]):
                k = i
        trie = Trie(len(wordsContainer[k]), k)
        for i, w in enumerate(wordsContainer):
            trie.insert(w[::-1], i)
        ans = []
        for i, w in enumerate(wordsQuery):
            ans.append(trie.query(w[::-1]))
        return ans
```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
