# [286. 墙与门](https://leetcode-cn.com/problems/walls-and-gates)

[English Version](/solution/0200-0299/0286.Walls%20and%20Gates/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>你被给定一个 <em>m × n</em> 的二维网格，网格中有以下三种可能的初始化值：</p>

<ol>
	<li><code>-1</code> 表示墙或是障碍物</li>
	<li><code>0</code> 表示一扇门</li>
	<li><code>INF</code> 无限表示一个空的房间。然后，我们用 <code>2<sup>31</sup> - 1 = 2147483647</code> 代表 <code>INF</code>。你可以认为通往门的距离总是小于 <code>2147483647</code> 的。</li>
</ol>

<p>你要给每个空房间位上填上该房间到 <em>最近 </em>门的距离，如果无法到达门，则填 <code>INF</code> 即可。</p>

<p><strong>示例：</strong></p>

<p>给定二维网格：</p>

<pre>INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
</pre>

<p>运行完你的函数后，该网格应该变成：</p>

<pre>  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
</pre>



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