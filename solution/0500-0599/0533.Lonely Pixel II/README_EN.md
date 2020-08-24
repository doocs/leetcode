# [533. Lonely Pixel II](https://leetcode.com/problems/lonely-pixel-ii)

[中文文档](/solution/0500-0599/0533.Lonely%20Pixel%20II/README.md)

## Description
<p>Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row <b>R</b> and column <b>C</b> that align with all the following rules:</p>

<ol>
<li> Row R and column C both contain exactly N black pixels.</li>
<li> For all rows that have a black pixel at column C, they should be exactly the same as row R</li>
</ol>

<p>The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively. </p>

<p><b>Example:</b><br />
<pre>
<b>Input:</b>                                            
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

N = 3
<b>Output:</b> 6
<b>Explanation:</b> All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
        0    1    2    3    4    5         column index                                            
0    [['W', <b>'B'</b>, 'W', <b>'B'</b>, 'B', 'W'],    
1     ['W', <b>'B'</b>, 'W', <b>'B'</b>, 'B', 'W'],    
2     ['W', <b>'B'</b>, 'W', <b>'B'</b>, 'B', 'W'],    
3     ['W', 'W', 'B', 'W', 'B', 'W']]    
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.

</pre>
</p>

<p><b>Note:</b><br>
<ol>
<li>The range of width and height of the input 2D array is [1,200].</li>
</ol>
</p>


## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->