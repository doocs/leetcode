# [675. 为高尔夫比赛砍树](https://leetcode-cn.com/problems/cut-off-trees-for-golf-event)

[English Version](/solution/0600-0699/0675.Cut%20Off%20Trees%20for%20Golf%20Event/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>你被请来给一个要举办高尔夫比赛的树林砍树. 树林由一个非负的二维数组表示， 在这个数组中：</p>

<ol>
	<li><code>0</code> 表示障碍，无法触碰到.</li>
	<li><code>1</code>&nbsp;表示可以行走的地面.</li>
	<li><code>比 1 大的数</code>&nbsp;表示一颗允许走过的树的高度.</li>
</ol>

<p>每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。</p>

<p>你被要求按照树的高度从低向高砍掉所有的树，每砍过一颗树，树的高度变为 1 。</p>

<p>你将从（0，0）点开始工作，你应该返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。</p>

<p>可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
<strong>输出:</strong> 6
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
<strong>输出:</strong> -1
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入:</strong> 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
<strong>输出:</strong> 6
<strong>解释:</strong> (0,0) 位置的树，你可以直接砍去，不用算步数
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= forest.length &lt;= 50</code></li>
	<li><code>1 &lt;= forest[i].length &lt;= 50</code></li>
	<li><code>0 &lt;= forest[i][j]&nbsp;&lt;= 10^9</code></li>
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