# [1804. 实现 Trie （前缀树） II](https://leetcode-cn.com/problems/implement-trie-ii-prefix-tree)

[English Version](/solution/1800-1899/1804.Implement%20Trie%20II%20%28Prefix%20Tree%29/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>前缀树（<strong><a href="https://en.wikipedia.org/wiki/Trie" target="_blank">trie</a></strong> ，发音为 "try"）是一个树状的数据结构，用于高效地存储和检索一系列字符串的前缀。前缀树有许多应用，如自动补全和拼写检查。</p>

<p>实现前缀树 Trie 类：</p>

<ul>
	<li><code>Trie()</code> 初始化前缀树对象。</li>
	<li><code>void insert(String word)</code> 将字符串 <code>word</code> 插入前缀树中。</li>
	<li><code>int countWordsEqualTo(String word)</code> 返回前缀树中字符串 <code>word</code> 的实例个数。</li>
	<li><code>int countWordsStartingWith(String prefix)</code> 返回前缀树中以 <code>prefix</code> 为前缀的字符串个数。</li>
	<li><code>void erase(String word)</code> 从前缀树中移除字符串 <code>word</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><b>输入</b>
["Trie", "insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsStartingWith"]
[[], ["apple"], ["apple"], ["apple"], ["app"], ["apple"], ["apple"], ["app"], ["apple"], ["app"]]
<b>输出</b>
[null, null, null, 2, 2, null, 1, 1, null, 0]

<b>解释</b>
Trie trie = new Trie();
trie.insert("apple");               // 插入 "apple"。
trie.insert("apple");               // 插入另一个 "apple"。
trie.countWordsEqualTo("apple");    // 有两个 "apple" 实例，所以返回 2。
trie.countWordsStartingWith("app"); // "app" 是 "apple" 的前缀，所以返回 2。
trie.erase("apple");                // 移除一个 "apple"。
trie.countWordsEqualTo("apple");    // 现在只有一个 "apple" 实例，所以返回 1。
trie.countWordsStartingWith("app"); // 返回 1
trie.erase("apple");                // 移除 "apple"。现在前缀树是空的。
trie.countWordsStartingWith("app"); // 返回 0
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> 和 <code>prefix</code> 只包含小写英文字母。</li>
	<li><code>insert</code>、 <code>countWordsEqualTo</code>、 <code>countWordsStartingWith</code> 和 <code>erase</code> <strong>总共</strong>调用最多 <code>3 * 10<sup>4</sup></code> 次。</li>
	<li>保证每次调用 <code>erase</code> 时，字符串 <code>word</code> 总是存在于前缀树中。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
