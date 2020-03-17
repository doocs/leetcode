# [733. 图像渲染](https://leetcode-cn.com/problems/flood-fill)

## 题目描述
<!-- 这里写题目描述 -->
<p>有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。</p>

<p>给你一个坐标&nbsp;<code>(sr, sc)</code>&nbsp;表示图像渲染开始的像素值（行 ，列）和一个新的颜色值&nbsp;<code>newColor</code>，让你重新上色这幅图像。</p>

<p>为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，&hellip;&hellip;，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。</p>

<p>最后返回经过上色渲染后的图像。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
<strong>输出:</strong> [[2,2,2],[2,2,0],[2,0,1]]
<strong>解析:</strong> 
在图像的正中间，(坐标(sr,sc)=(1,1)),
在路径上所有符合条件的像素点的颜色都被更改成2。
注意，右下角的像素没有更改为2，
因为它不是在上下左右四个方向上与初始点相连的像素点。
</pre>

<p><strong>注意:</strong></p>

<ul>
	<li><code>image</code> 和&nbsp;<code>image[0]</code>&nbsp;的长度在范围&nbsp;<code>[1, 50]</code> 内。</li>
	<li>给出的初始点将满足&nbsp;<code>0 &lt;= sr &lt; image.length</code> 和&nbsp;<code>0 &lt;= sc &lt; image[0].length</code>。</li>
	<li><code>image[i][j]</code> 和&nbsp;<code>newColor</code>&nbsp;表示的颜色值在范围&nbsp;<code>[0, 65535]</code>内。</li>
</ul>



## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### ...
```

```
