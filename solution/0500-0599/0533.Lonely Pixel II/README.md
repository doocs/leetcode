# [533. 孤独像素 II](https://leetcode-cn.com/problems/lonely-pixel-ii)

[English Version](/solution/0500-0599/0533.Lonely%20Pixel%20II/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定一幅由黑色像素和白色像素组成的图像， 与一个正整数N, 找到位于某行 <strong>R</strong> 和某列 <strong>C</strong> 中且符合下列规则的黑色像素的数量:</p>

<ol>
	<li>行R 和列C都恰好包括N个黑色像素。</li>
	<li>列C中所有黑色像素所在的行必须和行R完全相同。</li>
</ol>

<p>图像由一个由‘B’和‘W’组成二维字符数组表示, ‘B’和‘W’分别代表黑色像素和白色像素。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>                                            
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

N = 3
<strong>输出:</strong> 6
<strong>解析:</strong> 所有粗体的'B'都是我们所求的像素(第1列和第3列的所有'B').
        0    1    2    3    4    5         列号                                          
0    [['W', <strong>'B'</strong>, 'W', <strong>'B'</strong>, 'B', 'W'],    
1     ['W', <strong>'B'</strong>, 'W', <strong>'B'</strong>, 'B', 'W'],    
2     ['W', <strong>'B'</strong>, 'W', <strong>'B'</strong>, 'B', 'W'],    
3     ['W', 'W', 'B', 'W', 'B', 'W']]    
行号

以R = 0行和C = 1列的'B'为例:
规则 1，R = 0行和C = 1列都恰好有N = 3个黑色像素. 
规则 2，在C = 1列的黑色像素分别位于0，1和2行。它们都和R = 0行完全相同。

</pre>

<p> </p>

<p><strong>注意:</strong></p>

<ol>
	<li>输入二维数组行和列的范围是 [1,200]。</li>
</ol>

<p> </p>



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