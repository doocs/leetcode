# [3093. Longest Common Suffix Queries](https://leetcode.com/problems/longest-common-suffix-queries)

[中文文档](/solution/3000-3099/3093.Longest%20Common%20Suffix%20Queries/README.md)

<!-- tags: -->

## Description

<p>You are given two arrays of strings <code>wordsContainer</code> and <code>wordsQuery</code>.</p>

<p>For each <code>wordsQuery[i]</code>, you need to find a string from <code>wordsContainer</code> that has the <strong>longest common suffix</strong> with <code>wordsQuery[i]</code>. If there are two or more strings in <code>wordsContainer</code> that share the longest common suffix, find the string that is the <strong>smallest</strong> in length. If there are two or more such strings that have the <strong>same</strong> smallest length, find the one that occurred <strong>earlier</strong> in <code>wordsContainer</code>.</p>

<p>Return <em>an array of integers </em><code>ans</code><em>, where </em><code>ans[i]</code><em> is the index of the string in </em><code>wordsContainer</code><em> that has the <strong>longest common suffix</strong> with </em><code>wordsQuery[i]</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">wordsContainer = [&quot;abcd&quot;,&quot;bcd&quot;,&quot;xbcd&quot;], wordsQuery = [&quot;cd&quot;,&quot;bcd&quot;,&quot;xyz&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s look at each <code>wordsQuery[i]</code> separately:</p>

<ul>
	<li>For <code>wordsQuery[0] = &quot;cd&quot;</code>, strings from <code>wordsContainer</code> that share the longest common suffix <code>&quot;cd&quot;</code> are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.</li>
	<li>For <code>wordsQuery[1] = &quot;bcd&quot;</code>, strings from <code>wordsContainer</code> that share the longest common suffix <code>&quot;bcd&quot;</code> are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.</li>
	<li>For <code>wordsQuery[2] = &quot;xyz&quot;</code>, there is no string from <code>wordsContainer</code> that shares a common suffix. Hence the longest common suffix is <code>&quot;&quot;</code>, that is shared with strings at index 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">wordsContainer = [&quot;abcdefgh&quot;,&quot;poiuygh&quot;,&quot;ghghgh&quot;], wordsQuery = [&quot;gh&quot;,&quot;acbfgh&quot;,&quot;acbfegh&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,0,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s look at each <code>wordsQuery[i]</code> separately:</p>

<ul>
	<li>For <code>wordsQuery[0] = &quot;gh&quot;</code>, strings from <code>wordsContainer</code> that share the longest common suffix <code>&quot;gh&quot;</code> are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.</li>
	<li>For <code>wordsQuery[1] = &quot;acbfgh&quot;</code>, only the string at index 0 shares the longest common suffix <code>&quot;fgh&quot;</code>. Hence it is the answer, even though the string at index 2 is shorter.</li>
	<li>For <code>wordsQuery[2] = &quot;acbfegh&quot;</code>, strings from <code>wordsContainer</code> that share the longest common suffix <code>&quot;gh&quot;</code> are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= wordsContainer.length, wordsQuery.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsContainer[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>1 &lt;= wordsQuery[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>wordsContainer[i]</code> consists only of lowercase English letters.</li>
	<li><code>wordsQuery[i]</code> consists only of lowercase English letters.</li>
	<li>Sum of <code>wordsContainer[i].length</code> is at most <code>5 * 10<sup>5</sup></code>.</li>
	<li>Sum of <code>wordsQuery[i].length</code> is at most <code>5 * 10<sup>5</sup></code>.</li>
</ul>

## Solutions

### Solution 1

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
