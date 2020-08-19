# [1472. 设计浏览器历史记录](https://leetcode-cn.com/problems/design-browser-history)

[English Version](/solution/1400-1499/1472.Design%20Browser%20History/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>你有一个只支持单个标签页的 <strong>浏览器</strong>&nbsp;，最开始你浏览的网页是&nbsp;<code>homepage</code>&nbsp;，你可以访问其他的网站&nbsp;<code>url</code>&nbsp;，也可以在浏览历史中后退&nbsp;<code>steps</code>&nbsp;步或前进&nbsp;<code>steps</code>&nbsp;步。</p>

<p>请你实现&nbsp;<code>BrowserHistory</code> 类：</p>

<ul>
	<li><code>BrowserHistory(string homepage)</code>&nbsp;，用&nbsp;<code>homepage</code>&nbsp;初始化浏览器类。</li>
	<li><code>void visit(string url)</code>&nbsp;从当前页跳转访问 <code>url</code> 对应的页面&nbsp;&nbsp;。执行此操作会把浏览历史前进的记录全部删除。</li>
	<li><code>string back(int steps)</code>&nbsp;在浏览历史中后退&nbsp;<code>steps</code>&nbsp;步。如果你只能在浏览历史中后退至多&nbsp;<code>x</code> 步且&nbsp;<code>steps &gt; x</code>&nbsp;，那么你只后退&nbsp;<code>x</code>&nbsp;步。请返回后退 <strong>至多</strong> <code>steps</code>&nbsp;步以后的&nbsp;<code>url</code>&nbsp;。</li>
	<li><code>string forward(int steps)</code>&nbsp;在浏览历史中前进&nbsp;<code>steps</code>&nbsp;步。如果你只能在浏览历史中前进至多&nbsp;<code>x</code>&nbsp;步且&nbsp;<code>steps &gt; x</code>&nbsp;，那么你只前进 <code>x</code>&nbsp;步。请返回前进&nbsp;<strong>至多</strong>&nbsp;<code>steps</code>步以后的 <code>url</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
[&quot;BrowserHistory&quot;,&quot;visit&quot;,&quot;visit&quot;,&quot;visit&quot;,&quot;back&quot;,&quot;back&quot;,&quot;forward&quot;,&quot;visit&quot;,&quot;forward&quot;,&quot;back&quot;,&quot;back&quot;]
[[&quot;leetcode.com&quot;],[&quot;google.com&quot;],[&quot;facebook.com&quot;],[&quot;youtube.com&quot;],[1],[1],[1],[&quot;linkedin.com&quot;],[2],[2],[7]]
<strong>输出：</strong>
[null,null,null,null,&quot;facebook.com&quot;,&quot;google.com&quot;,&quot;facebook.com&quot;,null,&quot;linkedin.com&quot;,&quot;google.com&quot;,&quot;leetcode.com&quot;]

<strong>解释：</strong>
BrowserHistory browserHistory = new BrowserHistory(&quot;leetcode.com&quot;);
browserHistory.visit(&quot;google.com&quot;);       // 你原本在浏览 &quot;leetcode.com&quot; 。访问 &quot;google.com&quot;
browserHistory.visit(&quot;facebook.com&quot;);     // 你原本在浏览 &quot;google.com&quot; 。访问 &quot;facebook.com&quot;
browserHistory.visit(&quot;youtube.com&quot;);      // 你原本在浏览 &quot;facebook.com&quot; 。访问 &quot;youtube.com&quot;
browserHistory.back(1);                   // 你原本在浏览 &quot;youtube.com&quot; ，后退到 &quot;facebook.com&quot; 并返回 &quot;facebook.com&quot;
browserHistory.back(1);                   // 你原本在浏览 &quot;facebook.com&quot; ，后退到 &quot;google.com&quot; 并返回 &quot;google.com&quot;
browserHistory.forward(1);                // 你原本在浏览 &quot;google.com&quot; ，前进到 &quot;facebook.com&quot; 并返回 &quot;facebook.com&quot;
browserHistory.visit(&quot;linkedin.com&quot;);     // 你原本在浏览 &quot;facebook.com&quot; 。 访问 &quot;linkedin.com&quot;
browserHistory.forward(2);                // 你原本在浏览 &quot;linkedin.com&quot; ，你无法前进任何步数。
browserHistory.back(2);                   // 你原本在浏览 &quot;linkedin.com&quot; ，后退两步依次先到 &quot;facebook.com&quot; ，然后到 &quot;google.com&quot; ，并返回 &quot;google.com&quot;
browserHistory.back(7);                   // 你原本在浏览 &quot;google.com&quot;， 你只能后退一步到 &quot;leetcode.com&quot; ，并返回 &quot;leetcode.com&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= homepage.length &lt;= 20</code></li>
	<li><code>1 &lt;= url.length &lt;= 20</code></li>
	<li><code>1 &lt;= steps &lt;= 100</code></li>
	<li><code>homepage</code> 和&nbsp;<code>url</code>&nbsp;都只包含&nbsp;&#39;.&#39; 或者小写英文字母。</li>
	<li>最多调用&nbsp;<code>5000</code>&nbsp;次&nbsp;<code>visit</code>，&nbsp;<code>back</code>&nbsp;和&nbsp;<code>forward</code>&nbsp;函数。</li>
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